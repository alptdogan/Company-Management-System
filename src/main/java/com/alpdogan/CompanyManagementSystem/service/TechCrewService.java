package com.alpdogan.CompanyManagementSystem.service;

import com.alpdogan.CompanyManagementSystem.dto.request.SaveTechCrewRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateTechCrewRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.TechCrewResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.TechCrew;
import com.alpdogan.CompanyManagementSystem.repository.TechCrewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechCrewService {

    @Autowired
    TechCrewRepository techCrewRepository;

    @Autowired
    ModelMapper modelMapper;

    public String saveTechCrew(SaveTechCrewRequestDto saveTechCrewRequestDto) {

        TechCrew techCrew = modelMapper.map(saveTechCrewRequestDto, TechCrew.class);

        techCrew = techCrewRepository.save(techCrew);

        return techCrew.getCrewName() + " Has Been Created Successfully.";

    }

    public TechCrew findTechCrewById(int techCrewId) {

        return techCrewRepository.findTechCrewById(techCrewId);

    }

    public List<TechCrewResponseDto> findAllTechCrews() {

        Iterable<TechCrew> techCrews = techCrewRepository.findAll();

        List<TechCrewResponseDto> techCrewResponseDtos = new ArrayList<>();

        for (TechCrew techCrew : techCrews) {

            TechCrewResponseDto techCrewResponseDto = modelMapper.map(techCrew, TechCrewResponseDto.class);
            techCrewResponseDtos.add(techCrewResponseDto);

        }

        return techCrewResponseDtos;

    }

    public String updateTechCrewById(UpdateTechCrewRequestDto updateTechCrewRequestDto) {

        int idTechCrewRequest = updateTechCrewRequestDto.getId();
        String nameTechCrewRequest = updateTechCrewRequestDto.getCrewName();

        Optional<TechCrew> techCrewOptional = techCrewRepository.findById(idTechCrewRequest);
        TechCrew techCrew = techCrewOptional.get();

        techCrew.setCrewName(nameTechCrewRequest);

        techCrewRepository.save(techCrew);

        return "Changes Saved Successfully.";

    }

    public String deleteTechCrewById(Integer techCrewId) {

        Optional<TechCrew> techCrewOptional = techCrewRepository.findById(techCrewId);
        TechCrew techCrew = techCrewOptional.get();

        techCrewRepository.delete(techCrew);

        return "Tech Crew Deleted.";

    }

}
