package com.scaler.greivance.controller;

import com.scaler.greivance.dto.AdminDepartmentDTO;
import com.scaler.greivance.dto.UserDepartmentDTO;
import com.scaler.greivance.model.Department;
import com.scaler.greivance.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<UserDepartmentDTO> getAllUserDepartments() {
        return this.departmentService.getAllUserDepartments();
    }

    @GetMapping("/departments/{id}")
    public UserDepartmentDTO getDepartmentById(@PathVariable UUID id) {
        return this.departmentService.getUserDepartmentById(id);
    }

    @PostMapping("/admin/departments")
    public AdminDepartmentDTO saveDepartment(@RequestBody Department department) {
        return this.departmentService.saveDepartment(department);
    }

    @DeleteMapping("/admin/departments/{id}")
    public void deleteDepartmentById(@PathVariable UUID id) {
        this.departmentService.deleteDepartmentById(id);
    }

    @GetMapping("/admin/departments")
    public List<AdminDepartmentDTO> getAllAdminDepartments() {
        return this.departmentService.getAllAdminDepartments();
    }

    @GetMapping("/admin/departments/{id}")
    public AdminDepartmentDTO getAdminDepartmentById(@PathVariable UUID id) {
        return this.departmentService.getAdminDepartmentById(id);
    }
}
