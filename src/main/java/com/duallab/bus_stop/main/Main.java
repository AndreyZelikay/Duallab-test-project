package com.duallab.bus_stop.main;

import com.duallab.bus_stop.dao.DAOFactory;
import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.domain.enumeration.CompanyName;
import com.duallab.bus_stop.service.ServiceFactory;
import com.duallab.bus_stop.service.impl.TimetableServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance(
                DAOFactory.getInstance(CLIApi.prompt("input filepath"))
        );

        TimetableServiceImpl timetableService = serviceFactory.getTimetableService();

        FileTimetableEntryPrinter timetableEntryPrinter = new FileTimetableEntryPrinter(OUTPUT_FILENAME);

        List<TimetableEntry> poshEntries = timetableService.getFilteredByEfficiencyTimetableEntriesWithCompanyName(CompanyName.POSH)
                .stream()
                .sorted(Comparator.comparing(TimetableEntry::getDepartureTime))
                .collect(Collectors.toList());

        timetableEntryPrinter.printEntries(poshEntries);

        List<TimetableEntry> grottyEntries = timetableService.getFilteredByEfficiencyTimetableEntriesWithCompanyName(CompanyName.GROTTY)
                .stream()
                .sorted(Comparator.comparing(TimetableEntry::getDepartureTime))
                .collect(Collectors.toList());

        timetableEntryPrinter.printEntries(grottyEntries);

        CLIApi.printInfo("your file here: " + timetableEntryPrinter.getFile().getAbsolutePath());
    }
}
