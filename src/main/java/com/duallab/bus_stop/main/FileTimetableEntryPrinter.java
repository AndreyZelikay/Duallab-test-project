package com.duallab.bus_stop.main;

import com.duallab.bus_stop.domain.TimetableEntry;
import com.duallab.bus_stop.exception.FileTimetableEntryPrinterException;

import java.io.*;
import java.util.List;

public class FileTimetableEntryPrinter {

    private final File file;
    private final BufferedWriter writer;

    public FileTimetableEntryPrinter(String fileName) {
        this.file = new File(fileName);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file)));
        } catch (FileNotFoundException e) {
            throw new FileTimetableEntryPrinterException(e);
        }
    }

    public void printEntries(List<TimetableEntry> entries) {
        try {
            for (TimetableEntry entry : entries) {
                writer.write(entry.getCompanyName().getSimpleName() + " " + entry.getDepartureTime() + " " + entry.getArrivalTime() + "\n");
                writer.flush();
            }
            writer.write("\n");
        } catch (IOException e) {
            throw new FileTimetableEntryPrinterException(e);
        }
    }

    public File getFile() {
        return file;
    }
}
