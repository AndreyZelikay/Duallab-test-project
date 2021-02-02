package com.duallab.bus_stop.dao.impl.parser;

import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;
import com.duallab.bus_stop.exception.ParserException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class TimetableEntryParserTest {

    @Test
    public void testParseValidInput() {
        String input = "Posh 10:15 11:10";
        TimetableEntry expected = new TimetableEntry(CompanyName.POSH, LocalTime.of(10, 15), LocalTime.of(11, 10));
        TimetableEntry actual = TimetableEntryParser.parse(input);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseInvalidInput() {
        String input = "abc";

        Assert.assertThrows(ParserException.class,() -> TimetableEntryParser.parse(input));
    }
}
