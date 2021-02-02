package com.duallab.bus_stop.dao;

import com.duallab.bus_stop.dao.impl.TextFileTimetableDAOImpl;

public class DAOFactory {

    private static DAOFactory instance;

    private final String filePath;

    private TimetableDAO timetableDAO;

    private DAOFactory(String filePath) {
        this.filePath = filePath;
    }

    public static DAOFactory getInstance(String fileName) {
        if (instance == null) {
            instance = new DAOFactory(fileName);
        }
        return instance;
    }

    public TimetableDAO getTimetableDAO() {
        if (timetableDAO == null) {
            timetableDAO = new TextFileTimetableDAOImpl(filePath);
        }

        return timetableDAO;
    }

}
