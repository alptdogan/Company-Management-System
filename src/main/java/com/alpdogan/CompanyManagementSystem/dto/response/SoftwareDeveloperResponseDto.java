package com.alpdogan.CompanyManagementSystem.dto.response;

import lombok.Data;

@Data
public class SoftwareDeveloperResponseDto {

    private int id;

    private String fullName;

    private boolean isBackEndDeveloper;

    private boolean isFrontEndDeveloper;

    private boolean isArchitect;

    private int techCrewId;

}
