package com.alpdogan.CompanyManagementSystem.dto.request;

import lombok.Data;

@Data
public class UpdateSoftwareDeveloperRequestDto {

    private int id;

    private String fullName;

    private String role;

    private int techCrewId;

}
