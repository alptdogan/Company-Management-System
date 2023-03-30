package com.alpdogan.CompanyManagementSystem.controller;

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
    public ResponseEntity<TechCrew> findTechCrewById (@RequestParam int techCrewId) {

        TechCrew techCrew = techCrewService.findTechCrewById(techCrewId);

        return new ResponseEntity<>(techCrew, HttpStatus.OK);

    }

    @GetMapping("findAllTechCrew")
    public ResponseEntity<List<TechCrewResponseDto>> findAllTechCrews() {

        List<TechCrewResponseDto> techCrewResponseDtos = techCrewService.findAllTechCrews();

        return new ResponseEntity<>(techCrewResponseDtos, HttpStatus.OK);

    }

    @PostMapping("/saveTechCrew")
    public ResponseEntity<String> saveTechCrew(@RequestBody SaveTechCrewRequestDto saveTechCrewRequestDto) {

        String techCrewSaveDescription = techCrewService.saveTechCrew(saveTechCrewRequestDto);

        return new ResponseEntity<>(techCrewSaveDescription, HttpStatus.OK);

    }

    @PostMapping("/updateTechCrew")
    public ResponseEntity<String> updateTechCrewById(@RequestBody UpdateTechCrewRequestDto updateTechCrewRequestDto) {

        String updateTechCrewDescription = techCrewService.updateTechCrewById(updateTechCrewRequestDto);

        return new ResponseEntity<>(updateTechCrewDescription, HttpStatus.OK);

    }

    @DeleteMapping("/deleteTechCrew")
    public ResponseEntity<String> deleteTechCrewById(@RequestParam Integer techCrewId) {

        String deleteTechCrewDescription = techCrewService.deleteTechCrewById(techCrewId);

        return new ResponseEntity<>(deleteTechCrewDescription, HttpStatus.OK);

    }

}