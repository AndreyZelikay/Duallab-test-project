package com.duallab.bus_stop.service;

import com.duallab.bus_stop.domain.Timetable;
import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;

import java.util.List;

public interface TimetableService {
    Timetable getTimetable();

    List<TimetableEntry> getFilteredByEfficiencyTimetableEntries();

    List<TimetableEntry> getFilteredByEfficiencyTimetableEntriesWithCompanyName(CompanyName companyName);
}
