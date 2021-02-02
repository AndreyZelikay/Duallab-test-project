package com.duallab.bus_stop.domain;

import java.util.List;

public class Timetable {
    private List<TimetableEntry> entries;

    public List<TimetableEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TimetableEntry> entries) {
        this.entries = entries;
    }
}
