package com.alpdogan.CompanyManagementSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "crews")
public class TechCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "crew_name")
    private String crewName;

}
