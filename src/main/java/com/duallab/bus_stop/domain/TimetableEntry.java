package com.duallab.bus_stop.domain;

import com.duallab.bus_stop.domain.enumeration.CompanyName;

import java.time.LocalTime;
import java.util.Objects;

public class TimetableEntry {
    private CompanyName companyName;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public TimetableEntry() {}

    public TimetableEntry(CompanyName companyName, LocalTime departureTime, LocalTime arrivalTime) {
        this.companyName = companyName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public void setCompanyName(CompanyName companyName) {
        this.companyName = companyName;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimetableEntry)) return false;
        TimetableEntry that = (TimetableEntry) o;
        return companyName.equals(that.companyName) && arrivalTime.equals(that.arrivalTime) && departureTime.equals(that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, arrivalTime, departureTime);
    }
}
