package com.alpdogan.CompanyManagementSystem.dto.request;

import lombok.Data;

@Data
public class UpdateSoftwareDeveloperRequestDto {

    private int id;

    private String fullName;

    private boolean isBackEndDeveloper;

    private boolean isFrontEndDeveloper;

    private boolean isArchitect;

    private int techCrewId;

}
