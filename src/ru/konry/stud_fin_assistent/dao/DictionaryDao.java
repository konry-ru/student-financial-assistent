package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.domains.PassportOffice;
import ru.konry.stud_fin_assistent.domains.RegisterOffice;
import ru.konry.stud_fin_assistent.domains.Street;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.sql.Connection;
import java.util.List;

public interface DictionaryDao
{
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffices(String areaId) throws DaoException;
    List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException;
}
