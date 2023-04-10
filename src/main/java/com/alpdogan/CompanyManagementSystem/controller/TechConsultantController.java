package com.alpdogan.CompanyManagementSystem.controller;

import com.alpdogan.CompanyManagementSystem.configuration.ResponseModel;
import com.alpdogan.CompanyManagementSystem.dto.request.SaveTechConsultantRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateTechConsultantRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.TechConsultantResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.TechConsultant;
import com.alpdogan.CompanyManagementSystem.service.TechConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techConsultants")
public class TechConsultantController {

    @Autowired
    TechConsultantService techConsultantService;

    @GetMapping("/findTechConsultantById")
    public ResponseEntity<?> findTechConsultantById (@RequestParam int techConsultantId) {

        try {

            TechConsultant techConsultant = techConsultantService.findTechConsultantById(techConsultantId);

            return new ResponseEntity<>(techConsultant, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("No Consultant Found With The Specified ID."), HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("findAllTechConsultants")
    public ResponseEntity<?> findAllTechConsultants() {

        try {

            List<TechConsultantResponseDto> techConsultantResponseDtos = techConsultantService.findAllTechConsultants();

            return new ResponseEntity<>(techConsultantResponseDtos, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("No Consultant Found With The Specified ID."), HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("/saveTechConsultant")
    public ResponseEntity<String> saveTechConsultant (@RequestBody SaveTechConsultantRequestDto saveTechConsultantRequestDto) {

        try {

            String techConsultantSaveDescription = techConsultantService.saveTechConsultant(saveTechConsultantRequestDto);

            return new ResponseEntity<>(techConsultantSaveDescription, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/updateTechConsultant")
    public ResponseEntity<String> updateTechConsultantById (@RequestBody UpdateTechConsultantRequestDto updateTechConsultantRequestDto) {

        try {

            String updateTechConsultantDescription = techConsultantService.updateTechConsultantById(updateTechConsultantRequestDto);

            return new ResponseEntity<>(updateTechConsultantDescription, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/deleteTechConsultant")
    public ResponseEntity<String> deleteTechConsultantById(@RequestParam Integer techConsultantId) {

        try {

            String deleteTechConsultantDescription = techConsultantService.deleteTechConsultantById(techConsultantId);

            return new ResponseEntity<>(deleteTechConsultantDescription, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

}
