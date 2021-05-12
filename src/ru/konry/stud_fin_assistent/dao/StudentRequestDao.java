package ru.konry.stud_fin_assistent.dao;

import ru.konry.stud_fin_assistent.domains.StudentRequest;
import ru.konry.stud_fin_assistent.exceptions.DaoException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface StudentRequestDao
{
    long saveStudentRequest(StudentRequest sr) throws DaoException;

    List<StudentRequest> getStudentRequests() throws DaoException;
}
