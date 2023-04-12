package com.alpdogan.CompanyManagementSystem.controller;

import com.alpdogan.CompanyManagementSystem.configuration.ResponseModel;
import com.alpdogan.CompanyManagementSystem.dto.request.SaveTechCrewRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateTechCrewRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.TechCrewResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.TechCrew;
import com.alpdogan.CompanyManagementSystem.service.TechCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techCrews")
public class TechCrewController {

    @Autowired
    TechCrewService techCrewService;

    @GetMapping("/findTechCrewById")
    public ResponseEntity<?> findTechCrewById (@RequestParam int techCrewId) {

        try {
            TechCrew techCrew = techCrewService.findTechCrewById(techCrewId);

            return new ResponseEntity<>(techCrew, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("No Tech Crew Found With The Specified ID."), HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("findAllTechCrews")
    public ResponseEntity<?> findAllTechCrews() {

        try {
            List<TechCrewResponseDto> techCrewResponseDtos = techCrewService.findAllTechCrews();

            return new ResponseEntity<>(techCrewResponseDtos, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("There Is No Crew To Be Listed."), HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("/saveTechCrew")
    public ResponseEntity<String> saveTechCrew(@RequestBody SaveTechCrewRequestDto saveTechCrewRequestDto) {

        try {
            String techCrewSaveDescription = techCrewService.saveTechCrew(saveTechCrewRequestDto);

            return new ResponseEntity<>(techCrewSaveDescription, HttpStatus.OK);
        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/updateTechCrew")
    public ResponseEntity<String> updateTechCrewById(@RequestBody UpdateTechCrewRequestDto updateTechCrewRequestDto) {

        try {

            String updateTechCrewDescription = techCrewService.updateTechCrewById(updateTechCrewRequestDto);

            return new ResponseEntity<>(updateTechCrewDescription, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/deleteTechCrew")
    public ResponseEntity<String> deleteTechCrewById(@RequestParam Integer techCrewId) {

        try {
            
            String deleteTechCrewDescription = techCrewService.deleteTechCrewById(techCrewId);

            return new ResponseEntity<>(deleteTechCrewDescription, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

}
