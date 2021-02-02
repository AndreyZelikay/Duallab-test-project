package com.duallab.bus_stop.service.impl;

import com.duallab.bus_stop.dao.TimetableDAO;
import com.duallab.bus_stop.domain.Timetable;
import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;
import com.duallab.bus_stop.service.TimetableService;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class TimetableServiceImpl implements TimetableService {

    private final TimetableDAO timetableDAO;

    public TimetableServiceImpl(TimetableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }

    @Override
    public Timetable getTimetable() {
        return timetableDAO.getTimeTable();
    }

    @Override
    public List<TimetableEntry> getFilteredByEfficiencyTimetableEntriesWithCompanyName(CompanyName companyName) {
        return getFilteredByEfficiencyTimetableEntries().stream()
                .filter(timetableEntry -> timetableEntry.getCompanyName() == companyName)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableEntry> getFilteredByEfficiencyTimetableEntries() {
        Timetable timetable = getTimetable();
        List<TimetableEntry> timetableEntries = timetable.getEntries();

        return timetableEntries.stream()
                .filter(timetableEntry -> !isLongerThanHour(timetableEntry))
                .filter(timetableEntry -> isMostEfficient(timetableEntry, timetable))
                .distinct()
                .collect(Collectors.toList());
    }

    private boolean isLongerThanHour(TimetableEntry timetableEntry) {
        return Duration.between(timetableEntry.getDepartureTime(), timetableEntry.getArrivalTime()).compareTo(Duration.ofHours(1)) > 0;
    }

    private boolean isMostEfficient(TimetableEntry timetableEntry, Timetable timetable) {
        return timetable.getEntries().stream().noneMatch(entryToCompare -> isMoreEfficient(entryToCompare, timetableEntry));
    }

    private boolean isMoreEfficient(TimetableEntry entry1, TimetableEntry entry2) {
        if(entry1.equals(entry2)) {
           return false;
        }

        if(entry1.getDepartureTime().isAfter(entry2.getDepartureTime()) &&
                !entry1.getArrivalTime().isAfter(entry2.getArrivalTime())) {
            return true;
        }

        if(!entry1.getDepartureTime().isBefore(entry2.getDepartureTime()) &&
                entry1.getArrivalTime().isBefore(entry2.getArrivalTime())) {
            return true;
        }

        return entry1.getArrivalTime().equals(entry2.getArrivalTime()) &&
                entry1.getDepartureTime().equals(entry2.getDepartureTime()) &&
                entry1.getCompanyName() == CompanyName.POSH;
    }
}
