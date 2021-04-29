package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.domains.Street;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao
{
    public static final String GET_STREET = "SELECT street_code, street_name FROM st_street WHERE " +
            "UPPER(street_name) LIKE UPPER(?)";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/fin_students", "postgres", "postgres"
        );
    }

    public List<Street> findStreets(String pattern) throws DaoException {

        List<Street> streets = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET);
        ) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Street street = new Street(rs.getLong(1), rs.getString(2));
                streets.add(street);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

    return streets;
    }

    public static void main(String[] args) throws  DaoException {
        List<Street> streets;
        DictionaryDaoImpl dd = new DictionaryDaoImpl();
        streets = dd.findStreets("sec");
        System.out.println(streets);
    }
}

