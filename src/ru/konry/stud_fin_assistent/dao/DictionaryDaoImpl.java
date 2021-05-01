package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.config.Config;
import ru.konry.stud_fin_assistent.domains.CountryArea;
import ru.konry.stud_fin_assistent.domains.PassportOffice;
import ru.konry.stud_fin_assistent.domains.RegisterOffice;
import ru.konry.stud_fin_assistent.domains.Street;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao
{
    public static final String GET_STREET = "SELECT street_code, street_name FROM st_street WHERE " +
            "UPPER(street_name) LIKE UPPER(?)";
    public static final String GET_PASSPORT_OFFICE = "SELECT p_office_id, p_office_name FROM st_passport_office WHERE p_office_area_id = ?";
    public static final String GET_REGISTER_OFFICE = "SELECT * FROM st_register_office WHERE r_office_area_id = ?";
    private static final String GET_AREA = "SELECT * FROM st_country_struct WHERE area_id LIKE ? AND area_id <> ?";


    @Override
    public List<Street> findStreets(String pattern) throws DaoException {

        List<Street> streets = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)
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

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {

        List<PassportOffice> passportOffices = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT_OFFICE)
        ) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PassportOffice passportOffice = new PassportOffice(rs.getLong(1), rs.getString(2));
                passportOffices.add(passportOffice);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return passportOffices;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {

        List<RegisterOffice> registerOffices = new LinkedList<>();

        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_REGISTER_OFFICE)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegisterOffice registerOffice = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
                registerOffices.add(registerOffice);
            }
        }
        catch(SQLException ex) {
            throw new DaoException(ex);
        }
        return registerOffices;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {

        List<CountryArea> areas = new LinkedList<>();

        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_AREA)) {
            stmt.setString(1, buildAreaParam(areaId));
            stmt.setString(2, areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CountryArea area = new CountryArea(rs.getString("area_id"), rs.getString("area_name"));
                areas.add(area);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return areas;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
    }

    private String buildAreaParam (String area_id) throws SQLException {
        if(area_id == null || area_id.trim().isEmpty()) {
            return "__0000000000";
        }
        else if(area_id.endsWith("0000000000")) {
            return area_id.substring(0, 2) + "___0000000";
        }
        else if(area_id.endsWith("0000000")) {
            return area_id.substring(0,5) + "___0000";
        }
        else if(area_id.endsWith("0000")) {
            return area_id.substring(0,8) + "____";
        }
        throw new SQLException("Не допустимый код региона: " + area_id);
    }

    public static void main(String[] args) throws  DaoException {
        List<Street> streets;
        List<PassportOffice> passportOffices;
        List<RegisterOffice> registerOffices;
        List<CountryArea> areas;

        DictionaryDaoImpl dd = new DictionaryDaoImpl();

        streets = dd.findStreets("проспект");
        passportOffices = dd.findPassportOffices("020020010000");
        registerOffices = dd.findRegisterOffices("010020000000");
        areas = dd.findAreas("");

//        for (Street street : streets) {
//            System.out.println(street.getStreetName());
//        }
//        for (PassportOffice passportOffice : passportOffices) {
//            System.out.println(passportOffice.getOfficeId() + " - " + passportOffice.getOfficeName());
//        }
//        for (RegisterOffice ro : registerOffices) {
//            System.out.println(ro.getOfficeId() + " - " + ro.getOfficeName());
//        }
        for (CountryArea area : areas) {
            System.out.println(area.getAreaId() + " : " + area.getAreaName());
        }
    }
}

