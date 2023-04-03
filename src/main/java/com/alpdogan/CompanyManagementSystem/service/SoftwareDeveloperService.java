package com.alpdogan.CompanyManagementSystem.service;

import com.alpdogan.CompanyManagementSystem.dto.request.SaveSoftwareDeveloperRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateSoftwareDeveloperRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.SoftwareDeveloperResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.SoftwareDeveloper;
import com.alpdogan.CompanyManagementSystem.entity.TechCrew;
import com.alpdogan.CompanyManagementSystem.repository.SoftwareDeveloperRepository;
import com.alpdogan.CompanyManagementSystem.repository.TechCrewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoftwareDeveloperService {

    @Autowired
    SoftwareDeveloperRepository softwareDeveloperRepository;

    @Autowired
    TechCrewRepository techCrewRepository;

    @Autowired
    ModelMapper modelMapper;

    public String saveSoftwareDeveloper(SaveSoftwareDeveloperRequestDto saveSoftwareDeveloperRequestDto) {

        String fullNameRequest = saveSoftwareDeveloperRequestDto.getFullName();
        boolean isFrontEndRequest = saveSoftwareDeveloperRequestDto.isFrontEndDeveloper();
        boolean isBackEndRequest = saveSoftwareDeveloperRequestDto.isBackEndDeveloper();
        boolean isArchitectRequest = saveSoftwareDeveloperRequestDto.isArchitect();
        int techCrewIdRequest = saveSoftwareDeveloperRequestDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        SoftwareDeveloper softwareDeveloper = new SoftwareDeveloper();

        softwareDeveloper.setFullName(fullNameRequest);
        softwareDeveloper.setFrontEndDeveloper(isFrontEndRequest);
        softwareDeveloper.setBackEndDeveloper(isBackEndRequest);
        softwareDeveloper.setArchitect(isArchitectRequest);
        softwareDeveloper.setTechCrew(techCrew);

        softwareDeveloper.getTechCrew().getSoftwareDevelopers().add(softwareDeveloper.getId(), softwareDeveloper);

        softwareDeveloperRepository.save(softwareDeveloper);

        return softwareDeveloper.getFullName() + " Has Been Successfully Created and Added to " + techCrew.getCrewName();

    }

    public SoftwareDeveloper findSoftwareDeveloperById(int softwareDeveloperId) {

        return softwareDeveloperRepository.findSofwareDeveloperById(softwareDeveloperId);

    }

    public List<SoftwareDeveloperResponseDto> findAllSoftwareDevelopers() {

        Iterable<SoftwareDeveloper> softwareDevelopers = softwareDeveloperRepository.findAll();

        List<SoftwareDeveloperResponseDto> softwareDeveloperResponseDtos = new ArrayList<>();

        for (SoftwareDeveloper softwareDeveloper : softwareDevelopers) {

            SoftwareDeveloperResponseDto softwareDeveloperResponseDto = modelMapper.map(softwareDeveloper, SoftwareDeveloperResponseDto.class);

            softwareDeveloperResponseDtos.add(softwareDeveloperResponseDto);

        }

        return softwareDeveloperResponseDtos;

    }

    public String updateSoftwareDeveloper(UpdateSoftwareDeveloperRequestDto updateSoftwareDeveloperDto) {

        int idRequest = updateSoftwareDeveloperDto.getId();
        String fullNameRequest = updateSoftwareDeveloperDto.getFullName();
        boolean isFrontEndRequest = updateSoftwareDeveloperDto.isFrontEndDeveloper();
        boolean isBackEndRequest = updateSoftwareDeveloperDto.isBackEndDeveloper();
        boolean isArchitectRequest = updateSoftwareDeveloperDto.isArchitect();
        int techCrewIdRequest = updateSoftwareDeveloperDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        Optional<SoftwareDeveloper> softwareDeveloperOptional = softwareDeveloperRepository.findById(idRequest);
        SoftwareDeveloper softwareDeveloper = softwareDeveloperOptional.get();

        softwareDeveloper.setFullName(fullNameRequest);
        softwareDeveloper.setFrontEndDeveloper(isFrontEndRequest);
        softwareDeveloper.setBackEndDeveloper(isBackEndRequest);
        softwareDeveloper.setArchitect(isArchitectRequest);
        softwareDeveloper.setTechCrew(techCrew);

        softwareDeveloperRepository.save(softwareDeveloper);

        return "Changes Saved Successfully.";
    }

    public String deleteSoftwareDeveloperById(Integer softwareDeveloperId) {

        Optional<SoftwareDeveloper> optionalSoftwareDeveloper = softwareDeveloperRepository.findById(softwareDeveloperId);
        SoftwareDeveloper softwareDeveloper = optionalSoftwareDeveloper.get();

        // bunu yapmalıydım!!!!!!
        softwareDeveloper.getTechCrew().getSoftwareDevelopers().remove(softwareDeveloper);

        softwareDeveloperRepository.delete(softwareDeveloper);

        return "Software Developer Deleted.";

    }

}
