package com.alpdogan.CompanyManagementSystem.controller;

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
    public ResponseEntity<SoftwareDeveloper> findSoftwareDeveloperById(@RequestParam int softwareDeveloperId) {

        SoftwareDeveloper softwareDeveloper = softwareDeveloperService.findSoftwareDeveloperById(softwareDeveloperId);

        return new ResponseEntity<>(softwareDeveloper, HttpStatus.OK);

    }

    @GetMapping("findAllSoftwareDevelopers")
    public ResponseEntity<List<SoftwareDeveloperResponseDto>> findAllSoftwareDevelopers() {

        List<SoftwareDeveloperResponseDto> softwareDeveloperResponseDtos = softwareDeveloperService.findAllSoftwareDevelopers();

        return new ResponseEntity<>(softwareDeveloperResponseDtos, HttpStatus.OK);

    }

    @PostMapping("/saveSoftwareDeveloper")
    public ResponseEntity<String> saveSoftwareDeveloper(@RequestBody SaveSoftwareDeveloperRequestDto saveSoftwareDeveloperRequestDto) {

        String softwareDeveloperSaveDescription = softwareDeveloperService.saveSoftwareDeveloper(saveSoftwareDeveloperRequestDto);

        return new ResponseEntity<>(softwareDeveloperSaveDescription, HttpStatus.OK);

    }

    @PostMapping("/updateSoftwareDeveloper")
    public ResponseEntity<String> updateSoftwareDeveloperById (@RequestBody UpdateSoftwareDeveloperRequestDto updateSoftwareDeveloperRequestDto) {

        String updateSoftwareDeveloperDescription = softwareDeveloperService.updateSoftwareDeveloper(updateSoftwareDeveloperRequestDto);

        return new ResponseEntity<>(updateSoftwareDeveloperDescription, HttpStatus.OK);

    }

    @DeleteMapping("/deleteSoftwareDeveloper")
    public ResponseEntity<String> deleteSoftwareDeveloperById(@RequestParam Integer softwareDeveloperId) {

        String deleteSoftwareDeveloperDescription = softwareDeveloperService.deleteSoftwareDeveloperById(softwareDeveloperId);

        return new ResponseEntity<>(deleteSoftwareDeveloperDescription, HttpStatus.OK);

    }

}
