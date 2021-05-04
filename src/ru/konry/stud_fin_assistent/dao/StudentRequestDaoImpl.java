package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.config.Config;
import ru.konry.stud_fin_assistent.domains.Address;
import ru.konry.stud_fin_assistent.domains.Adult;
import ru.konry.stud_fin_assistent.domains.StudentRequest;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.*;

public class StudentRequestDaoImpl implements StudentRequestDao
{
    public static final String STUDENT_REQUEST = "INSERT INTO st_student_request(" +
            "student_request_time, student_request_state," +
            " h_name, h_surname, h_patronymic, h_birth_data, h_passport_series, h_passport_number, h_passport_date," +
            " h_passport_office_id, h_postal_code, h_street_code, h_building, h_corpus, h_apartment," +
            " w_name, w_surname, w_patronymic, w_birth_data, w_passport_series, w_passport_number, w_passport_date," +
            " w_passport_office_id, w_postal_code, w_street_code, w_building, w_corpus, w_apartment," +
            " certificate_id, register_office_id, marriage_data)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public long saveStudentRequest(StudentRequest sr) throws DaoException {
        try(Connection con = createConnection();
            PreparedStatement stmt = con.prepareStatement(STUDENT_REQUEST)){
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(sr.getTimeOfRequest()));
            stmt.setInt(2, sr.getStateOfRequest().ordinal());

            Adult husband = sr.getHusband();
            Address husbandAddress = husband.getAdress();
            stmt.setString(3, husband.getName());
            stmt.setString(4, husband.getSurname());
            stmt.setString(5, husband.getPatronymic());
            stmt.setDate(6, java.sql.Date.valueOf(husband.getBirthData()));
            stmt.setString(7, husband.getPassportSeries());
            stmt.setString(8, husband.getPassportNumber());
            stmt.setDate(9, java.sql.Date.valueOf(husband.getIssueData()));
            stmt.setLong(10, husband.getIssueDepartment().getOfficeId());
            stmt.setString(11, husbandAddress.getPostalCode());
            stmt.setLong(12, husbandAddress.getStreet().getStreetCode());
            stmt.setString(13, husbandAddress.getBuilding());
            stmt.setString(14, husbandAddress.getCorpus());
            stmt.setString(15, husbandAddress.getApartment());

            Adult wife = sr.getWife();
            Address wifeAddress = wife.getAdress();
            stmt.setString(16, wife.getName());
            stmt.setString(17, wife.getSurname());
            stmt.setString(18, wife.getPatronymic());
            stmt.setDate(19, java.sql.Date.valueOf(wife.getBirthData()));
            stmt.setString(20, wife.getPassportSeries());
            stmt.setString(21, wife.getPassportNumber());
            stmt.setDate(22, java.sql.Date.valueOf(wife.getIssueData()));
            stmt.setLong(23, wife.getIssueDepartment().getOfficeId());
            stmt.setString(24, wifeAddress.getPostalCode());
            stmt.setLong(25, wifeAddress.getStreet().getStreetCode());
            stmt.setString(26, wifeAddress.getBuilding());
            stmt.setString(27, wifeAddress.getCorpus());
            stmt.setString(28, wifeAddress.getApartment());

            stmt.setString(29, sr.getMarriageCertificateId());
            stmt.setLong(30, sr.getMarriageOffice().getOfficeId());
            stmt.setDate(31, java.sql.Date.valueOf(sr.getMarriageDate()));

            stmt.executeUpdate();

        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return 0L;
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
