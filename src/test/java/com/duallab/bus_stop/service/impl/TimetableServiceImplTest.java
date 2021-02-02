package com.duallab.bus_stop.service.impl;

import com.duallab.bus_stop.dao.TimetableDAO;
import com.duallab.bus_stop.dao.impl.TextFileTimetableDAOImpl;
import com.duallab.bus_stop.domain.Timetable;
import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;
import com.duallab.bus_stop.service.TimetableService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class TimetableServiceImplTest {

    @Test
    public void getFilteredByEfficiencyTimetableEntries() {
        TimetableDAO timetableDAO = Mockito.mock(TextFileTimetableDAOImpl.class);
        TimetableService timetableService = new TimetableServiceImpl(timetableDAO);

        TimetableEntry serviceLongerThanHour = new TimetableEntry(CompanyName.POSH, LocalTime.now(), LocalTime.now().plusHours(2));
        TimetableEntry poshEntry = new TimetableEntry(CompanyName.POSH, LocalTime.of(14, 10), LocalTime.of(14, 50));
        TimetableEntry grottyEntryWithSameTimeAsPoshEntry = new TimetableEntry(CompanyName.GROTTY, LocalTime.of(14, 10), LocalTime.of(14, 50));
        TimetableEntry lessEfficientEntry = new TimetableEntry(CompanyName.POSH, LocalTime.of(15,10), LocalTime.of(15, 20));
        TimetableEntry moreEfficientEntry = new TimetableEntry(CompanyName.GROTTY, LocalTime.of(15, 10), LocalTime.of(15,15));

        List<TimetableEntry> timetableEntries = Arrays.asList(
                serviceLongerThanHour,
                poshEntry,
                grottyEntryWithSameTimeAsPoshEntry,
                lessEfficientEntry,
                moreEfficientEntry
        );

        Timetable timetable = new Timetable();
        timetable.setEntries(timetableEntries);

        when(timetableDAO.getTimeTable()).thenReturn(timetable);

        List<TimetableEntry> expected = Arrays.asList(
                poshEntry,
                moreEfficientEntry
        );

        List<TimetableEntry> actual = timetableService.getFilteredByEfficiencyTimetableEntries();

        Assert.assertTrue(actual.size() == expected.size() && actual.containsAll(expected));
    }
}
