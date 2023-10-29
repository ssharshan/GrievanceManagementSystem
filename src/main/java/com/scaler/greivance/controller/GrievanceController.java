package com.scaler.greivance.controller;

import com.scaler.greivance.dto.CreateGrievanceDTO;
import com.scaler.greivance.dto.StatusUpdateDTO;
import com.scaler.greivance.dto.UserGrievanceDTO;
import com.scaler.greivance.model.Grievance;
import com.scaler.greivance.service.GrievanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/grievances")
public class GrievanceController {

    private GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @GetMapping
    public List<UserGrievanceDTO> getAllGrievances() {
        return this.grievanceService.getAllGrievances();
    }

    @GetMapping("/{id}")
    public UserGrievanceDTO getGrievanceById(@PathVariable UUID id) {
        return this.grievanceService.getGrievanceById(id);
    }

    @PostMapping
    public UserGrievanceDTO saveGrievance(@RequestBody CreateGrievanceDTO grievanceDTO) {
        return this.grievanceService.saveGrievanceDTO(grievanceDTO);
    }

    @PutMapping("/{id}/status")
    public UserGrievanceDTO updateGrievanceStatusById(@PathVariable UUID id, @RequestBody StatusUpdateDTO status) {
        return this.grievanceService.updateGrievanceStatusById(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteGrievanceById(@PathVariable UUID id) {
        this.grievanceService.deleteGrievanceById(id);
    }

    //TODO: Add admin list grievances
    //TODO: Add update grievance by id
}
