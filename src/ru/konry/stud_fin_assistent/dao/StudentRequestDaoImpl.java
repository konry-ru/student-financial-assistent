package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.config.Config;
import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentRequestDaoImpl implements StudentRequestDao
{
    private static final String STUDENT_REQUEST = "INSERT INTO st_student_request(" +
            "student_request_time, student_request_state," +
            " h_sur_name, h_given_name, h_patronymic, h_birth_data, h_passport_series, h_passport_number, h_passport_date," +
            " h_passport_office_id, h_postal_code, h_street_code, h_building, h_corpus, h_apartment, h_university_id, h_student_id, " +
            " w_sur_name, w_given_name, w_patronymic, w_birth_data, w_passport_series, w_passport_number, w_passport_date," +
            " w_passport_office_id, w_postal_code, w_street_code, w_building, w_corpus, w_apartment, w_university_id, w_student_id, " +
            " certificate_id, register_office_id, marriage_data)" +
            "VALUES (?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?);";

    private static final String CHILD_REQUEST = "INSERT INTO st_child(" +
            "student_request_id, c_sur_name, c_given_name, c_patronymic, c_birth_data," +
            "c_certificate_number, c_certificate_date, c_register_office_id," +
            "c_postal_code, c_street_code, c_building, c_corpus, c_apartment)" +
            "VALUES (" +
            "?, ?, ?, ?, ?, " +
            "?, ?, ?, " +
            "?, ?, ?, ?, ?" +
            ");";

    public static final String SELECT_REQUESTS = "SELECT sr.*, ro.r_office_area_id, ro.r_office_name, " +
            "p_h.p_office_area_id AS h_p_office_area_id, p_h.p_office_name AS h_p_office_name, " +
            "p_w.p_office_area_id AS w_p_office_area_id, p_w.p_office_name AS w_p_office_name " +
            "FROM st_student_request sr " +
            "INNER JOIN st_register_office ro ON ro.r_office_id = sr.register_office_id " +
            "INNER JOIN st_passport_office p_h ON p_h.p_office_id = sr.h_passport_office_id " +
            "INNER JOIN st_passport_office p_w ON p_w.p_office_id = sr.w_passport_office_id " +
            "WHERE student_request_state = ? ORDER BY student_request_time LIMIT ?;";

    public static final String SELECT_CHILD =
            "SELECT sc.*, so.r_office_area_id, so.r_office_name FROM st_child sc " +
            "INNER JOIN st_register_office so ON so.r_office_id = sc.c_register_office_id " +
            "WHERE sc.student_request_id IN ";

    public static final String SELECT_REQUESTS_FULL = "SELECT sr.*, ro.r_office_area_id, ro.r_office_name, " +
            "p_h.p_office_area_id AS h_p_office_area_id, p_h.p_office_name AS h_p_office_name, " +
            "p_w.p_office_area_id AS w_p_office_area_id, p_w.p_office_name AS w_p_office_name, " +
            "sc.*, ro_c.r_office_area_id, ro_c.r_office_name " +
            "FROM st_student_request sr " +
            "INNER JOIN st_register_office ro ON ro.r_office_id = sr.register_office_id " +
            "INNER JOIN st_passport_office p_h ON p_h.p_office_id = sr.h_passport_office_id " +
            "INNER JOIN st_passport_office p_w ON p_w.p_office_id = sr.w_passport_office_id " +
            "INNER JOIN st_child sc ON sr.student_request_id = sc.student_request_id " +
            "INNER JOIN st_register_office ro_c ON ro_c.r_office_id = sc.c_register_office_id " +
            "WHERE student_request_state = ? ORDER BY sr.student_request_id LIMIT ?;";

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
        Address address = adult.getAddress();
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
        stmt.setString(start, person.getSurName());
        stmt.setString(start + 1, person.getGivenName());
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

    @Override
    public List<StudentRequest> getStudentRequests() throws DaoException {

        return getByOneSelectStudentRequests();
//        return getByTwoSelectStudentRequests();
    }

    private List<StudentRequest> getByOneSelectStudentRequests() throws DaoException {

        List<StudentRequest> result = new LinkedList<>();

        try (Connection con = createConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_REQUESTS_FULL)) {

            int limit = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
            int counter = 0;

            stmt.setInt(1, StudentRequestStatus.START.ordinal());
            stmt.setInt(2, limit);

            ResultSet rs = stmt.executeQuery();

            HashMap<Long, StudentRequest> requestHashMap = new HashMap<>();

            while (rs.next()) {

                StudentRequest sRequest;
                Long srId = rs.getLong("student_request_id");

                if (!requestHashMap.containsKey(srId)) {
                    sRequest = getFullStudentRequest(rs);

                    requestHashMap.put(srId, sRequest);
                    result.add(sRequest);
                }
                sRequest = requestHashMap.get(srId);
                sRequest.addChild(fillChild(rs));

                counter++;
            };

            if (counter >= limit) {
                result.remove(result.size() - 1);
            }

            rs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private List<StudentRequest> getByTwoSelectStudentRequests() throws DaoException {
        List<StudentRequest> result = new LinkedList<>();

        try (Connection con = createConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_REQUESTS)) {

            stmt.setInt(1, StudentRequestStatus.START.ordinal());
            stmt.setInt(2, Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentRequest sRequest = new StudentRequest();

                fillRequestHeader(sRequest, rs);
                fillWedding(sRequest, rs);
                sRequest.setHusband(fillAdult(rs, "h_"));
                sRequest.setWife(fillAdult(rs, "w_"));

                result.add(sRequest);
            }

            findChildren(con, result);

            rs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private StudentRequest getFullStudentRequest(ResultSet rs) throws SQLException {

        StudentRequest sRequest = new StudentRequest();

        fillRequestHeader(sRequest, rs);
        fillWedding(sRequest, rs);
        sRequest.setHusband(fillAdult(rs, "h_"));
        sRequest.setWife(fillAdult(rs, "w_"));

        return sRequest;
    }

    private void fillRequestHeader(StudentRequest sRequest, ResultSet rs) throws SQLException {

        sRequest.setStudentRequestId(rs.getLong("student_request_id"));
        sRequest.setStateOfRequest(StudentRequestStatus.statusFromValue(rs.getInt("student_request_state")));
        sRequest.setTimeOfRequest(rs.getTimestamp("student_request_time").toLocalDateTime());
    }

    private void fillWedding(StudentRequest sRequest, ResultSet rs) throws SQLException {

        sRequest.setMarriageCertificateId(rs.getString("certificate_id"));
        sRequest.setMarriageDate(rs.getDate("marriage_data").toLocalDate());
        long officeId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        sRequest.setMarriageOffice(new RegisterOffice(officeId, areaId, name));
    }

    private Adult fillAdult(ResultSet rs, String pref) throws SQLException {
        Adult adult = new Adult();

        adult.setSurName(rs.getString(pref + "sur_name"));
        adult.setGivenName(rs.getString(pref + "given_name"));
        adult.setPatronymic(rs.getString(pref + "patronymic"));
        adult.setBirthData(rs.getDate(pref + "birth_data").toLocalDate());

        adult.setPassportSeries(rs.getString(pref + "passport_series"));
        adult.setPassportNumber(rs.getString(pref + "passport_number"));
        adult.setIssueData(rs.getDate(pref + "passport_date").toLocalDate());

        long pOfficeId = rs.getLong(pref + "passport_office_id");
        String pOfficeAreaId = rs.getString(pref + "p_office_area_id");
        String pOfficeName = rs.getString(pref + "p_office_name");
        PassportOffice po = new PassportOffice(pOfficeId, pOfficeAreaId, pOfficeName);
        adult.setIssueDepartment(po);

        Address address = new Address();
        address.setPostalCode(rs.getString(pref + "postal_code"));
        Street street = new Street(rs.getLong(pref + "street_code"), "");
        address.setStreet(street);
        address.setBuilding(rs.getString(pref + "building"));
        address.setCorpus(rs.getString(pref + "corpus"));
        address.setApartment(rs.getString(pref + "apartment"));
        adult.setAddress(address);

        University uni = new University(rs.getLong(pref + "university_id"), "");
        adult.setUniversity(uni);

        adult.setStudentId(pref + "student_id");

        return adult;
    }

    private void findChildren(Connection con, List<StudentRequest> result) throws SQLException {
        String param = "(" +
                result.stream().
                        map(req -> String.valueOf(req.getStudentRequestId())).
                        collect(Collectors.joining(",")) +
                ")";
        Map<Long, StudentRequest> maps = result.stream().collect(Collectors
                .toMap(so -> so.getStudentRequestId(), so -> so));

        try ( PreparedStatement stmt = con.prepareStatement(SELECT_CHILD + param)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Child ch = fillChild(rs);
                StudentRequest sr = maps.get(rs.getLong("student_request_id"));
                sr.addChild(ch);
            }
        }
    }

    private Child fillChild(ResultSet rs) throws SQLException {

        String surName = rs.getString("c_sur_name");
        String givenName = rs.getString("c_given_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_birth_data").toLocalDate();

        Child ch = new Child(surName, givenName, patronymic, dateOfBirth);

        ch.setCertificateId(rs.getString("c_certificate_number"));
        ch.setIssueData(rs.getDate("c_certificate_date").toLocalDate());

        Long roId = rs.getLong("c_register_office_id");
        String roAreaId = rs.getString(("r_office_area_id"));
        String roName = rs.getString(("r_office_name"));
        RegisterOffice ro = new RegisterOffice(roId, roAreaId, roName);
        ch.setIssueDepartment(ro);

        Address address = new Address();
        address.setPostalCode(rs.getString("c_postal_code"));
        Street street = new Street(rs.getLong("c_street_code"), "");
        address.setStreet(street);
        address.setBuilding(rs.getString("c_building"));
        address.setCorpus(rs.getString("c_corpus"));
        address.setApartment(rs.getString("c_apartment"));
        ch.setAddress(address);

        return ch;
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
