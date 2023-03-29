package com.alpdogan.CompanyManagementSystem.repository;

import com.alpdogan.CompanyManagementSystem.entity.TechCrew;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechCrewRepository extends CrudRepository<TechCrew, Integer> {

    TechCrew findTechCrewById(int id);

}
