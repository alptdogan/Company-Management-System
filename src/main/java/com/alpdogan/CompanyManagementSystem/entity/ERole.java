package com.alpdogan.CompanyManagementSystem.entity;

public enum ERole {

    ARCHITECT(1),
    BACKEND(2),
    FRONTEND(3);

    private final int value;

    ERole(int value) {
        this.value = value;
    }

    /*
    public int getValue() {
        return value;
    }
     */

}

