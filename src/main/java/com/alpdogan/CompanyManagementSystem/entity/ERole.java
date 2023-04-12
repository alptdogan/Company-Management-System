package com.alpdogan.CompanyManagementSystem.entity;

public enum ERole {

    ARCHITECT(1, "Architect"),
    BACKEND(2, "Backend"),
    FRONTEND(3, "Frontend");

    private int value;
    private String value1;

    ERole(int value, String value1) {
        this.value = value;
        this.value1 = value1;
    }

    @Override
    public String toString() {
        return value1;
    }

    /*
    public int getValue() {
        return value;
    }
     */

}

