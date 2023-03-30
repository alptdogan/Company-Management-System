package com.alpdogan.CompanyManagementSystem.service;

import com.alpdogan.CompanyManagementSystem.dto.request.SaveTechConsultantRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateTechConsultantRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.TechConsultantResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.TechConsultant;
import com.alpdogan.CompanyManagementSystem.entity.TechCrew;
import com.alpdogan.CompanyManagementSystem.repository.TechConsultantRepository;
import com.alpdogan.CompanyManagementSystem.repository.TechCrewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechConsultantService {

    @Autowired
    TechConsultantRepository techConsultantRepository;

    @Autowired
    TechCrewRepository techCrewRepository;

    @Autowired
    ModelMapper modelMapper;

    public String saveTechConsultant(SaveTechConsultantRequestDto saveTechConsultantRequestDto) {

        String fullNameRequest = saveTechConsultantRequestDto.getFullName();
        int techCrewIdRequest = saveTechConsultantRequestDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        TechConsultant techConsultant = new TechConsultant();

        techConsultant.setFullName(fullNameRequest);
        techConsultant.setTechCrew(techCrew);

        techConsultant.getTechCrew().getTechConsultants().add(techConsultant.getId(), techConsultant);

        techConsultantRepository.save(techConsultant);

        return techConsultant.getFullName() + " Has Been Successfully Created and Added to " + techCrew.getCrewName() + " as a Tech Consultant.";

    }

    public TechConsultant findTechConsultantById(int techConsultantId) {

        return techConsultantRepository.findTechConsultantById(techConsultantId);

    }

    public List<TechConsultantResponseDto> findAllTechConsultants() {

        Iterable<TechConsultant> techConsultants = techConsultantRepository.findAll();

        List<TechConsultantResponseDto> techConsultantResponseDtos = new ArrayList<>();

        for (TechConsultant techConsultant : techConsultants) {

            TechConsultantResponseDto softwareDeveloperResponseDto = modelMapper.map(techConsultants, TechConsultantResponseDto.class);

            techConsultantResponseDtos.add(softwareDeveloperResponseDto);

        }

        return techConsultantResponseDtos;

    }

    public String updateTechConsultantById(UpdateTechConsultantRequestDto updateTechConsultantRequestDto) {

        int idRequest = updateTechConsultantRequestDto.getId();
        String fullNameRequest = updateTechConsultantRequestDto.getFullName();
        int techCrewIdRequest = updateTechConsultantRequestDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        Optional<TechConsultant> techConsultantOptional = techConsultantRepository.findById(idRequest);
        TechConsultant techConsultant = techConsultantOptional.get();

        techConsultant.setFullName(fullNameRequest);
        techConsultant.setTechCrew(techCrew);

        techConsultantRepository.save(techConsultant);

        return "Changes Saved Successfully.";
    }

    public String deleteTechConsultantById(Integer techConsultantId) {

        Optional<TechConsultant> optionalTechConsultant = techConsultantRepository.findById(techConsultantId);
        TechConsultant techConsultant = optionalTechConsultant.get();

        techConsultantRepository.delete(techConsultant);

        return "Tech Consultant Deleted.";

    }

}
