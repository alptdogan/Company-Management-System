package com.alpdogan.CompanyManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "developers")
public class SoftwareDeveloper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "is_back_end_developer")
    private boolean isBackEndDeveloper;

    @Column(name = "is_front_end_developer")
    private boolean isFrontEndDeveloper;

    @Column(name = "is_architect")
    private boolean isArchitect;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private TechCrew techCrew;

}
