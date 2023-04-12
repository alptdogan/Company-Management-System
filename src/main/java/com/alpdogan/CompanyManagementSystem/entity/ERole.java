package com.alpdogan.CompanyManagementSystem.entity;

public enum ERole {

    A("Software Architect"),
    B("Backend Developer"),
    F("Frontend Developer");

    private String value;

    ERole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}

