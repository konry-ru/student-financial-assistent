package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.config.Config;
import ru.konry.stud_fin_assistent.domains.Address;
import ru.konry.stud_fin_assistent.domains.Adult;
import ru.konry.stud_fin_assistent.domains.StudentRequest;
import ru.konry.stud_fin_assistent.domains.StudentRequestStatus;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

public class StudentRequestDaoImpl implements StudentRequestDao
{
    public static final String STUDENT_REQUEST = "INSERT INTO st_student_request(" +
            "student_request_time, student_request_state," +
            " h_name, h_surname, h_patronymic, h_birth_data, h_passport_series, h_passport_number, h_passport_date," +
            " h_passport_office_id, h_postal_code, h_street_code, h_building, h_corpus, h_apartment," +
            " w_name, w_surname, w_patronymic, w_birth_data, w_passport_series, w_passport_number, w_passport_date," +
            " w_passport_office_id, w_postal_code, w_street_code, w_building, w_corpus, w_apartment," +
            " certificate_id, register_office_id, marriage_data)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public long saveStudentRequest(StudentRequest sr) throws DaoException {
        long result = -1L;

        try (Connection con = createConnection();
            PreparedStatement stmt = con.prepareStatement(STUDENT_REQUEST, new String[]{"student_request_id"})) {

//          Request Data
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(2, StudentRequestStatus.START.ordinal());

//          Husband and wife
            Adult husband = sr.getHusband();
            Adult wife = sr.getWife();
            setAdultParameters(stmt, husband, 3);
            setAdultParameters(stmt, wife, 16);

//          Marriage
            stmt.setString(29, sr.getMarriageCertificateId());
            stmt.setLong(30, sr.getMarriageOffice().getOfficeId());
            stmt.setDate(31, java.sql.Date.valueOf(sr.getMarriageDate()));

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                result = keys.getLong("student_request_id");
            }
            keys.close();
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private void setAdultParameters (PreparedStatement stmt, Adult adult, int start) throws SQLException {
        stmt.setString(start, adult.getName());
        stmt.setString(start + 1, adult.getSurname());
        stmt.setString(start + 2, adult.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(adult.getBirthData()));
        stmt.setString(start + 4, adult.getPassportSeries());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueData()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        Address husbandAddress = adult.getAdress();
        stmt.setString(start + 8, husbandAddress.getPostalCode());
        stmt.setLong(start + 9, husbandAddress.getStreet().getStreetCode());
        stmt.setString(start + 10, husbandAddress.getBuilding());
        stmt.setString(start + 11, husbandAddress.getCorpus());
        stmt.setString(start + 12, husbandAddress.getApartment());
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
