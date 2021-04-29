package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.domains.Street;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DirectoryDao
{
    private Connection getConnectionc() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/fin_students", "postgres", "postgres"
        );
    }

    public List<Street> findStreets(String pattern) throws ClassNotFoundException, SQLException {

        List<Street> streets = new LinkedList<>();
        final String query = "SELECT street_code, street_name FROM st_street WHERE " +
                "UPPER(street_name) LIKE UPPER('%" + pattern + "%')";

        Class.forName("org.postgresql.Driver");
        Connection con = getConnectionc();
        Statement stmnt = con.createStatement();
        ResultSet rs = stmnt.executeQuery(query);
        while (rs.next()) {
            Street street = new Street(rs.getLong(1), rs.getString(2));
            streets.add(street);
        }
    return streets;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<Street> streets;
        DirectoryDao dd = new DirectoryDao();
        streets = dd.findStreets("sec");
        System.out.println(streets);
    }
}

