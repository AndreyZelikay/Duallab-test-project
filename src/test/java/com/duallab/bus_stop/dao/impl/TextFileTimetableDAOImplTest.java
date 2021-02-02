package com.duallab.bus_stop.dao.impl;

import com.duallab.bus_stop.dao.TimetableDAO;
import com.duallab.bus_stop.domain.Timetable;
import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class TextFileTimetableDAOImplTest {

    private static final String INPUT_FILE_PATH = "src/test/resources/test_input.txt";

    @Test
    public void testGetTimetable() {
        TimetableDAO timetableDAO = new TextFileTimetableDAOImpl(INPUT_FILE_PATH);

        List<TimetableEntry> expectedEntryList = Arrays.asList(
                new TimetableEntry(CompanyName.POSH, LocalTime.of(10,15), LocalTime.of(11, 10)),
                new TimetableEntry(CompanyName.POSH, LocalTime.of(10,10), LocalTime.of(11, 0)),
                new TimetableEntry(CompanyName.GROTTY, LocalTime.of(10,10), LocalTime.of(11, 0)),
                new TimetableEntry(CompanyName.GROTTY, LocalTime.of(16,30), LocalTime.of(18, 45)),
                new TimetableEntry(CompanyName.POSH, LocalTime.of(12,5), LocalTime.of(12, 30)),
                new TimetableEntry(CompanyName.GROTTY, LocalTime.of(12,30), LocalTime.of(13, 25)),
                new TimetableEntry(CompanyName.GROTTY, LocalTime.of(12,45), LocalTime.of(13, 25)),
                new TimetableEntry(CompanyName.POSH, LocalTime.of(17,25), LocalTime.of(18, 1))
        );

        Timetable actual = timetableDAO.getTimeTable();

        Assert.assertTrue(actual.getEntries().size() == expectedEntryList.size() && actual.getEntries().containsAll(expectedEntryList));
    }
}
