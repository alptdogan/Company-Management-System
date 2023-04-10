package com.alpdogan.CompanyManagementSystem.controller;

import com.alpdogan.CompanyManagementSystem.configuration.ResponseModel;
import com.alpdogan.CompanyManagementSystem.dto.request.SaveSoftwareDeveloperRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.request.UpdateSoftwareDeveloperRequestDto;
import com.alpdogan.CompanyManagementSystem.dto.response.SoftwareDeveloperResponseDto;
import com.alpdogan.CompanyManagementSystem.entity.SoftwareDeveloper;
import com.alpdogan.CompanyManagementSystem.service.SoftwareDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/softwareDevelopers")
public class SoftwareDeveloperController {

    @Autowired
    SoftwareDeveloperService softwareDeveloperService;

    @GetMapping("/findSoftwareDeveloperById")
    public ResponseEntity<?> findSoftwareDeveloperById(@RequestParam int softwareDeveloperId) {

        try{
            SoftwareDeveloper softwareDeveloper = softwareDeveloperService.findSoftwareDeveloperById(softwareDeveloperId);

            return new ResponseEntity<>(softwareDeveloper, HttpStatus.OK);
        }
        catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("No Developer Found With The Specified ID."), HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("findAllSoftwareDevelopers")
    public ResponseEntity<?> findAllSoftwareDevelopers() {

        try {

            List<SoftwareDeveloperResponseDto> softwareDeveloperResponseDtos = softwareDeveloperService.findAllSoftwareDevelopers();

            return new ResponseEntity<>(softwareDeveloperResponseDtos, HttpStatus.OK);

        }
        catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("There Is No Crew To Be Listed."), HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("/saveSoftwareDeveloper")
    public ResponseEntity<String> saveSoftwareDeveloper(@RequestBody SaveSoftwareDeveloperRequestDto saveSoftwareDeveloperRequestDto) {

        try {

            String softwareDeveloperSaveDescription = softwareDeveloperService.saveSoftwareDeveloper(saveSoftwareDeveloperRequestDto);

            return new ResponseEntity<>(softwareDeveloperSaveDescription, HttpStatus.OK);

        }
        catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/updateSoftwareDeveloper")
    public ResponseEntity<String> updateSoftwareDeveloperById (@RequestBody UpdateSoftwareDeveloperRequestDto updateSoftwareDeveloperRequestDto) {

        try {

            String updateSoftwareDeveloperDescription = softwareDeveloperService.updateSoftwareDeveloper(updateSoftwareDeveloperRequestDto);

            return new ResponseEntity<>(updateSoftwareDeveloperDescription, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/deleteSoftwareDeveloper")
    public ResponseEntity<String> deleteSoftwareDeveloperById(@RequestParam Integer softwareDeveloperId) {

        try{

            String deleteSoftwareDeveloperDescription = softwareDeveloperService.deleteSoftwareDeveloperById(softwareDeveloperId);

            return new ResponseEntity<>(deleteSoftwareDeveloperDescription, HttpStatus.OK);

        }
        catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

}
