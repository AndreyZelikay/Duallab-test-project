package com.duallab.bus_stop.dao.impl.parser;

import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;
import com.duallab.bus_stop.exception.ParserException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimetableEntryParser {

    private TimetableEntryParser(){}

    private static final String TIMETABLE_ENTRY_REGEX = "^(?<companyName>\\w+) (?<departureTime>\\d{2}:\\d{2}) (?<arrivalTime>\\d{2}:\\d{2})$";

    public static TimetableEntry parse(String input) {
        Matcher matcher = Pattern.compile(TIMETABLE_ENTRY_REGEX).matcher(input);

        if(!matcher.matches()) {
            throw new ParserException("invalid signature of: " + input);
        }

        TimetableEntry timetableEntry = new TimetableEntry();
        timetableEntry.setCompanyName(Arrays.stream(CompanyName.values())
                .filter(companyName -> companyName.getSimpleName().equals(matcher.group("companyName")))
                .findAny()
                .orElseThrow(() -> new ParserException("no company with name: " + matcher.group("companyName"))));
        timetableEntry.setArrivalTime(LocalTime.parse(matcher.group("arrivalTime")));
        timetableEntry.setDepartureTime(LocalTime.parse(matcher.group("departureTime")));

        return timetableEntry;
    }
}
