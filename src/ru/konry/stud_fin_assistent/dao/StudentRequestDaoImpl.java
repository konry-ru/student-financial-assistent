package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.config.Config;
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
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public long saveStudentRequest(StudentRequest sr) throws DaoException {
        try(Connection con = createConnection();
            PreparedStatement stmt = con.prepareStatement(STUDENT_REQUEST)){
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(sr.getTimeOfRequest()));
            stmt.setInt(2, sr.getStateOfRequest().ordinal());

            ResultSet rs = stmt.getGeneratedKeys();

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
