package com.alpdogan.CompanyManagementSystem.repository;

import com.alpdogan.CompanyManagementSystem.entity.SoftwareDeveloper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareDeveloperRepository extends CrudRepository<SoftwareDeveloper, Integer> {

    SoftwareDeveloper findSofwareDeveloperById(int id);

}
