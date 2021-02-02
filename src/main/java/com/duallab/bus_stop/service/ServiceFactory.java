package com.duallab.bus_stop.service;

import com.duallab.bus_stop.dao.DAOFactory;
import com.duallab.bus_stop.service.impl.TimetableServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;
    private TimetableServiceImpl timetableService;
    private final DAOFactory daoFactory;

    private ServiceFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static ServiceFactory getInstance(DAOFactory daoFactory) {
        if(instance == null) {
            instance = new ServiceFactory(daoFactory);
        }
        return instance;
    }

    public TimetableServiceImpl getTimetableService() {
        if(timetableService == null) {
            timetableService = new TimetableServiceImpl(daoFactory.getTimetableDAO());
        }

        return timetableService;
    }
}
