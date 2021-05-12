package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.config.Config;
import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentRequestDaoImpl implements StudentRequestDao
{
    private static final String STUDENT_REQUEST = "INSERT INTO st_student_request(" +
            "student_request_time, student_request_state," +
            " h_name, h_surname, h_patronymic, h_birth_data, h_passport_series, h_passport_number, h_passport_date," +
            " h_passport_office_id, h_postal_code, h_street_code, h_building, h_corpus, h_apartment, h_university_id, h_student_id, " +
            " w_name, w_surname, w_patronymic, w_birth_data, w_passport_series, w_passport_number, w_passport_date," +
            " w_passport_office_id, w_postal_code, w_street_code, w_building, w_corpus, w_apartment, w_university_id, w_student_id, " +
            " certificate_id, register_office_id, marriage_data)" +
            "VALUES (?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?);";

    private static final String CHILD_REQUEST = "INSERT INTO st_child(" +
            "student_request_id, c_name, c_surname, c_patronymic, c_birth_data," +
            "c_certificate_number, c_certificate_date, c_register_office_id," +
            "c_postal_code, c_street_code, c_building, c_corpus, c_apartment)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SELECT_REQUEST = "SELECT * FROM st_student_request " +
            "WHERE student_request_state = 0 ORDER BY student_request_time;";

    @Override
    public long saveStudentRequest(StudentRequest sr) throws DaoException {

        long requestId = -1L;

        try (Connection con = createConnection();
            PreparedStatement stmt = con.prepareStatement(STUDENT_REQUEST, new String[]{"student_request_id"})) {

            con.setAutoCommit(false);

            try {
//          Request Data
                stmt.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                stmt.setInt(2, StudentRequestStatus.START.ordinal());

//          Husband and wife
                Adult husband = sr.getHusband();
                Adult wife = sr.getWife();
                setAdultParameters(stmt, husband, 3);
                setAdultParameters(stmt, wife, 18);

//          Marriage
                stmt.setString(33, sr.getMarriageCertificateId());
                stmt.setLong(34, sr.getMarriageOffice().getOfficeId());
                stmt.setDate(35, java.sql.Date.valueOf(sr.getMarriageDate()));

                stmt.executeUpdate();

                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    requestId = keys.getLong("student_request_id");
                }
                keys.close();

                saveChildren(con, sr, requestId);

                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return requestId;
    }

    @Override
    public List<StudentRequest> getStudentRequests() throws DaoException {

        List<StudentRequest> result = new LinkedList<>();

        try (Connection con = createConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_REQUEST)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentRequest sRequest = new StudentRequest();

                setRequestHeader(sRequest, rs);
                setWedding(sRequest, rs);

                result.add(sRequest);
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private void saveChildren(Connection con, StudentRequest sr, long requestId) throws SQLException {
        try(PreparedStatement stmt = con.prepareStatement(CHILD_REQUEST)){
            int counter = 0;
            for (Child child : sr.getChildren()) {
                stmt.setLong(1, requestId);
                setChildParameters(stmt, child, 2);
                stmt.addBatch();
                counter++;
                if(counter > 10000) {
                    stmt.executeBatch();
                    counter = 0;
                }
            }
            stmt.executeBatch();
        }
    }

    private void setAdultParameters (PreparedStatement stmt, Adult adult, int start) throws SQLException {
        setPersonParameters(stmt, adult, start);
        stmt.setString(start + 4, adult.getPassportSeries());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueData()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        Address address = adult.getAdress();
        setAddressParameters(stmt, address, start + 8);
        stmt.setLong(start + 13, adult.getUniversity().getUniversityId());
        stmt.setString(start + 14, adult.getStudentId());
    }

    private void setChildParameters(PreparedStatement stmt, Child child, int start) throws SQLException {
        setPersonParameters(stmt, child, start);
        stmt.setString(6, child.getCertificateId());
        stmt.setDate(7, Date.valueOf(child.getIssueData()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setAddressParameters(stmt, child.getAddress(), 9);
    }

    private void setPersonParameters(PreparedStatement stmt, Person person, int start) throws SQLException {
        stmt.setString(start, person.getName());
        stmt.setString(start + 1, person.getSurname());
        stmt.setString(start + 2, person.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(person.getBirthData()));
    }

    private void setAddressParameters(PreparedStatement stmt, Address address, int start) throws SQLException {
        stmt.setString(start, address.getPostalCode());
        stmt.setLong(start + 1, address.getStreet().getStreetCode());
        stmt.setString(start + 2, address.getBuilding());
        stmt.setString(start + 3, address.getCorpus());
        stmt.setString(start + 4, address.getApartment());
    }

    private void setRequestHeader(StudentRequest sRequest, ResultSet rs) throws SQLException {

        sRequest.setStudentRequestId(rs.getLong("student_request_id"));
        sRequest.setStateOfRequest(StudentRequestStatus.statusFromValue(rs.getInt("student_request_state")));
        sRequest.setTimeOfRequest(rs.getTimestamp("student_request_time").toLocalDateTime());
    }

    private void setWedding(StudentRequest sRequest, ResultSet rs) throws SQLException {

        sRequest.setMarriageCertificateId(rs.getString("certificate_id"));
        sRequest.setMarriageDate(rs.getDate("marriage_data").toLocalDate());
        long rOfficeId = rs.getLong("register_office_id");
        sRequest.setMarriageOffice(new RegisterOffice(rOfficeId, "", ""));
    }

//    TODO create one method for all Connections

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD)
        );
    }
}
