package com.alpdogan.CompanyManagementSystem.dto.request;

import lombok.Data;

@Data
public class SaveSoftwareDeveloperRequestDto {

    private String fullName;

    private String role;

    private int techCrewId;

}
