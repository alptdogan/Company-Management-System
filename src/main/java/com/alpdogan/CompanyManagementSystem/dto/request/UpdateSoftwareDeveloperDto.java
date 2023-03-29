package com.alpdogan.CompanyManagementSystem.dto.request;

import lombok.Data;

@Data
public class UpdateSoftwareDeveloperDto {

    private int id;

    private String fullName;

    private boolean isBackEndDeveloper;

    private boolean isFrontEndDeveloper;

    private boolean isArchitect;

    private int techCrewId;

}
