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
import java.util.stream.Stream;

@Service
public class TechCrewService {

    @Autowired
    TechCrewRepository techCrewRepository;

    @Autowired
    ModelMapper modelMapper;

    public String saveTechCrew(SaveTechCrewRequestDto saveTechCrewRequestDto) throws Exception {

        TechCrew techCrew = modelMapper.map(saveTechCrewRequestDto, TechCrew.class);

        /*
        Iterable<TechCrew> techCrews = techCrewRepository.findAll();

        List<TechCrewResponseDto> techCrewResponseDtos = new ArrayList<>();
         */


        if (techCrew.getCrewName().isBlank()) {
            throw new Exception("Tech Crew's Name Cannot Be Empty.");
        }
        // else if (techCrewResponseDtos.contains(techCrew.getCrewName())) { throw new Exception("Specified Name Is Already In Use."); }
        //else if (techCrew.getCrewName().equals(techCrewResponseDtos.stream().distinct().anyMatch(crewName -> crewName.equals(techCrew.getCrewName())))) {}
        else {

            techCrew = techCrewRepository.save(techCrew);

            return techCrew.getCrewName() + " Has Been Created Successfully.";

        }

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

        /*
        if (techCrewResponseDtos.size() >= 0) {

            for (TechCrew techCrew : techCrews) {

                TechCrewResponseDto techCrewResponseDto = modelMapper.map(techCrew, TechCrewResponseDto.class);
                techCrewResponseDtos.add(techCrewResponseDto);

            }

            return techCrewResponseDtos;

        }else {

            throw new Exception("There Is No Crew To Be Listed.");

        }
         */

    }

    public String updateTechCrewById(UpdateTechCrewRequestDto updateTechCrewRequestDto) throws Exception {

        int idTechCrewRequest = updateTechCrewRequestDto.getId();
        String nameTechCrewRequest = updateTechCrewRequestDto.getCrewName();

        Optional<TechCrew> techCrewOptional = techCrewRepository.findById(idTechCrewRequest);

        if (techCrewOptional.isPresent()) {
            TechCrew techCrew = techCrewOptional.get();

            techCrew.setCrewName(nameTechCrewRequest);

            techCrewRepository.save(techCrew);

            return "Tech Crew Updated Successfully.";

        }else {

            throw new Exception("Tech Crew -with the specified ID- Not Found");

        }

    }

    public String deleteTechCrewById(Integer techCrewId) throws Exception {

        Optional<TechCrew> techCrewOptional = techCrewRepository.findById(techCrewId);

        if (techCrewOptional.isPresent()) {

            TechCrew techCrew = techCrewOptional.get();

            techCrewRepository.delete(techCrew);

            return "Tech Crew Deleted.";

        }else {

            throw new Exception("Tech Crew Not Found.");

        }

    }

}
