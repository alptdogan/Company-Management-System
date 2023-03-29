package com.alpdogan.CompanyManagementSystem.repository;

import com.alpdogan.CompanyManagementSystem.entity.TechConsultant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechConsultantRepository extends CrudRepository<TechConsultant, Integer> {

    TechConsultant findTechConsultantById(int id);

}
