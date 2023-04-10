package com.alpdogan.CompanyManagementSystem.service;

import com.alpdogan.CompanyManagementSystem.dto.request.SaveTechConsultantRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateTechConsultantRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.SoftwareDeveloperResponseDto;
import com.alpdogan.CompanyManagementSystem.dto.response.TechConsultantResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.SoftwareDeveloper;
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

    public TechConsultant findTechConsultantById(int techConsultantId) throws Exception {

        Optional<TechConsultant> techConsultantOptional = techConsultantRepository.findById(techConsultantId);

        if (techConsultantOptional.isPresent()){

            return techConsultantRepository.findTechConsultantById(techConsultantId);

        }else {

            throw new Exception();

        }

    }

    public List<TechConsultantResponseDto> findAllTechConsultants() throws Exception {

        Iterable<TechConsultant> techConsultants = techConsultantRepository.findAll();

        List<TechConsultantResponseDto> techConsultantResponseDtos = new ArrayList<>();

        if (techConsultants.iterator().hasNext()) {

            for (TechConsultant techConsultant : techConsultants) {

                TechConsultantResponseDto softwareDeveloperResponseDto = modelMapper.map(techConsultant, TechConsultantResponseDto.class);

                techConsultantResponseDtos.add(softwareDeveloperResponseDto);

            }

            return techConsultantResponseDtos;

        }else {

            throw new Exception();

        }

    }

    public String saveTechConsultant(SaveTechConsultantRequestDto saveTechConsultantRequestDto) throws Exception {

        String fullNameRequest = saveTechConsultantRequestDto.getFullName();
        int techCrewIdRequest = saveTechConsultantRequestDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        TechConsultant techConsultant = new TechConsultant();

        techConsultant.setFullName(fullNameRequest);
        techConsultant.setTechCrew(techCrew);

        if (techConsultant.getFullName().isBlank()) {

            throw new Exception("Consuşltant's Name Cannot Be Empty.");

        }else {

            techConsultant.getTechCrew().getTechConsultants().add(techConsultant.getId(), techConsultant);

            techConsultantRepository.save(techConsultant);

            return techConsultant.getFullName() + " Has Been Successfully Created and Added to " + techCrew.getCrewName() + " as a Tech Consultant.";

        }

    }

    public String updateTechConsultantById(UpdateTechConsultantRequestDto updateTechConsultantRequestDto) throws Exception {

        int idRequest = updateTechConsultantRequestDto.getId();
        String fullNameRequest = updateTechConsultantRequestDto.getFullName();
        int techCrewIdRequest = updateTechConsultantRequestDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        Optional<TechConsultant> techConsultantOptional = techConsultantRepository.findById(idRequest);
        TechConsultant techConsultant = techConsultantOptional.get();

        if (techConsultantOptional.isPresent()) {

            techConsultant.setFullName(fullNameRequest);
            techConsultant.setTechCrew(techCrew);

            if (techConsultant.getFullName().isBlank()) {

                throw new Exception("Consultant's Name Cannot Be Empty.");

            }else {


                techConsultantRepository.save(techConsultant);

                return "Consultant Updated Successfully.";

            }

        }else {

            throw new Exception("Consultant -with the specified ID- Not Found");

        }
    }

    public String deleteTechConsultantById(Integer techConsultantId) throws Exception {

        Optional<TechConsultant> techConsultantOptional = techConsultantRepository.findById(techConsultantId);
        TechConsultant techConsultant = techConsultantOptional.get();

        if (techConsultantOptional.isPresent()) {

            techConsultant.getTechCrew().getTechConsultants().remove(techConsultant);

            techConsultantRepository.delete(techConsultant);

            return "Tech Consultant Deleted.";

        }else {

            throw new Exception("Cannot Found a Consultant to Delete with the Specified ID.");

        }

    }

}
