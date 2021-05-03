package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.domains.StudentRequest;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

public interface StudentRequestDao
{
    long saveStudentRequest(StudentRequest sr) throws DaoException;
}
