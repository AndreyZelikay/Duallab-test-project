package com.duallab.bus_stop.domain.enumeration;

public enum CompanyName {
    POSH("Posh"), GROTTY("Grotty");

    private final String simpleName;

    CompanyName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
