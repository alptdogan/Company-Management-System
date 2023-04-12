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

    public SoftwareDeveloper findSoftwareDeveloperById(int softwareDeveloperId) throws Exception {

        Optional<SoftwareDeveloper> softwareDeveloperOptional = softwareDeveloperRepository.findById(softwareDeveloperId);

        if (softwareDeveloperOptional.isPresent()){

            return softwareDeveloperRepository.findSofwareDeveloperById(softwareDeveloperId);

        }
        else {

            throw new Exception();

        }

    }

    public List<SoftwareDeveloperResponseDto> findAllSoftwareDevelopers() throws Exception {

        Iterable<SoftwareDeveloper> softwareDevelopers = softwareDeveloperRepository.findAll();

        List<SoftwareDeveloperResponseDto> softwareDeveloperResponseDtos = new ArrayList<>();

        if (softwareDevelopers.iterator().hasNext()) {

            for (SoftwareDeveloper softwareDeveloper : softwareDevelopers) {

                SoftwareDeveloperResponseDto softwareDeveloperResponseDto = modelMapper.map(softwareDeveloper, SoftwareDeveloperResponseDto.class);

                softwareDeveloperResponseDtos.add(softwareDeveloperResponseDto);

            }

            return softwareDeveloperResponseDtos;

        }else {

            throw new Exception();

        }

    }

    public String saveSoftwareDeveloper(SaveSoftwareDeveloperRequestDto saveSoftwareDeveloperRequestDto) throws Exception {

        String fullNameRequest = saveSoftwareDeveloperRequestDto.getFullName();
        /*
        boolean isFrontEndRequest = saveSoftwareDeveloperRequestDto.isFrontEndDeveloper();
        boolean isBackEndRequest = saveSoftwareDeveloperRequestDto.isBackEndDeveloper();
        boolean isArchitectRequest = saveSoftwareDeveloperRequestDto.isArchitect();
         */
        int roleIdRequest = saveSoftwareDeveloperRequestDto.getRoleId();
        int techCrewIdRequest = saveSoftwareDeveloperRequestDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        SoftwareDeveloper softwareDeveloper = new SoftwareDeveloper();

        softwareDeveloper.setFullName(fullNameRequest);
        /*
        softwareDeveloper.setFrontEndDeveloper(isFrontEndRequest);
        softwareDeveloper.setBackEndDeveloper(isBackEndRequest);
        softwareDeveloper.setArchitect(isArchitectRequest);
        */
        softwareDeveloper.setRoles(roleIdRequest); // Burayı nasıl yapmalıyım?
        softwareDeveloper.setTechCrew(techCrew);

        if (softwareDeveloper.getFullName().isBlank()) {

            throw new Exception("Developer's Name Cannot Be Empty.");

        }else {

            softwareDeveloper.getTechCrew().getSoftwareDevelopers().add(softwareDeveloper.getId(), softwareDeveloper);

            softwareDeveloperRepository.save(softwareDeveloper);

            return softwareDeveloper.getFullName() + " Has Been Successfully Created and Added to " + techCrew.getCrewName();

        }

    }

    public String updateSoftwareDeveloper(UpdateSoftwareDeveloperRequestDto updateSoftwareDeveloperDto) throws Exception {

        int idRequest = updateSoftwareDeveloperDto.getId();
        String fullNameRequest = updateSoftwareDeveloperDto.getFullName();
        boolean isFrontEndRequest = updateSoftwareDeveloperDto.isFrontEndDeveloper();
        boolean isBackEndRequest = updateSoftwareDeveloperDto.isBackEndDeveloper();
        boolean isArchitectRequest = updateSoftwareDeveloperDto.isArchitect();
        int techCrewIdRequest = updateSoftwareDeveloperDto.getTechCrewId();

        TechCrew techCrew = techCrewRepository.findById(techCrewIdRequest).get();

        Optional<SoftwareDeveloper> softwareDeveloperOptional = softwareDeveloperRepository.findById(idRequest);
        SoftwareDeveloper softwareDeveloper = softwareDeveloperOptional.get();

        if (softwareDeveloperOptional.isPresent()) {

            softwareDeveloper.setFullName(fullNameRequest);
            /*
            softwareDeveloper.setFrontEndDeveloper(isFrontEndRequest);
            softwareDeveloper.setBackEndDeveloper(isBackEndRequest);
            softwareDeveloper.setArchitect(isArchitectRequest);
             */
            softwareDeveloper.setTechCrew(techCrew);

            if (softwareDeveloper.getFullName().isBlank()) {

                throw new Exception("Developer's Name Cannot Be Empty.");

            }else {


                softwareDeveloperRepository.save(softwareDeveloper);

                return "Developer Updated Successfully.";

            }

        }else {

            throw new Exception("Developer -with the specified ID- Not Found");

        }

    }

    public String deleteSoftwareDeveloperById(Integer softwareDeveloperId) throws Exception {

        Optional<SoftwareDeveloper> softwareDeveloperOptional = softwareDeveloperRepository.findById(softwareDeveloperId);
        SoftwareDeveloper softwareDeveloper = softwareDeveloperOptional.get();

        if (softwareDeveloperOptional.isPresent()) {

            softwareDeveloper.getTechCrew().getSoftwareDevelopers().remove(softwareDeveloper);

            softwareDeveloperRepository.delete(softwareDeveloper);

            return "Software Developer Deleted.";

        }else {

            throw new Exception("Cannot Found a Developer to Delete with the Specified ID.");

        }

    }

}
