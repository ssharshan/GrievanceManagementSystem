package com.scaler.greivance.service;

import com.scaler.greivance.dto.AdminDepartmentDTO;
import com.scaler.greivance.dto.UserDepartmentDTO;
import com.scaler.greivance.model.Department;
import com.scaler.greivance.repository.DepartmentRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private CategoryService categoryService;

    public DepartmentService(DepartmentRepository departmentRepository, @Lazy CategoryService categoryService) {
        this.departmentRepository = departmentRepository;
        this.categoryService = categoryService;
    }

    public List<UserDepartmentDTO> getAllUserDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> toUserDepartmentDTO(department))
                .toList();
    }

    public UserDepartmentDTO getUserDepartmentById(UUID id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            return toUserDepartmentDTO(department);
        }
        return null;
    }

    public List<AdminDepartmentDTO> getAllAdminDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> toAdminDepartmentDTO(department))
                .toList();
    }

    public AdminDepartmentDTO getAdminDepartmentById(UUID id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            return toAdminDepartmentDTO(department);
        }
        return null;
    }

    public Department getDepartmentById(UUID id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public AdminDepartmentDTO saveDepartment(Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return this.toAdminDepartmentDTO(savedDepartment);
    }

    public void deleteDepartmentById(UUID id) {
        departmentRepository.deleteById(id);
    }

    public AdminDepartmentDTO updateDepartmentById(UUID id, Department department) {
        Department departmentToUpdate = departmentRepository.findById(id).orElse(null);
        if (departmentToUpdate != null) {
            departmentToUpdate.setName(department.getName());
            departmentToUpdate.setDescription(department.getDescription());
            return saveDepartment(departmentToUpdate);
        }
        return null;
    }

    public AdminDepartmentDTO toAdminDepartmentDTO(Department department) {
        AdminDepartmentDTO adminDepartmentDTO = new AdminDepartmentDTO();
        adminDepartmentDTO.setId(department.getId());
        adminDepartmentDTO.setName(department.getName());
        adminDepartmentDTO.setDescription(department.getDescription());
        department.getCategories().forEach(category -> {
            adminDepartmentDTO.getCategories().add(this.categoryService.toAdminCategoryDTO(category));
        });
        return adminDepartmentDTO;
    }

    public Department toDepartment(AdminDepartmentDTO adminDepartmentDTO) {
        Department department = new Department();
        department.setId(adminDepartmentDTO.getId());
        department.setName(adminDepartmentDTO.getName());
        department.setDescription(adminDepartmentDTO.getDescription());
        adminDepartmentDTO.getCategories().forEach(adminCategoryDTO -> {
            department.getCategories().add(this.categoryService.toCategory(adminCategoryDTO));
        });
        return department;
    }

    public UserDepartmentDTO toUserDepartmentDTO(Department department) {
        UserDepartmentDTO userDepartmentDTO = new UserDepartmentDTO();
        userDepartmentDTO.setId(department.getId());
        userDepartmentDTO.setName(department.getName());
        userDepartmentDTO.setDescription(department.getDescription());
        department.getCategories().forEach(category -> {
            userDepartmentDTO.getCategories().add(this.categoryService.toUserCategoryDTO(category));
        });
        return userDepartmentDTO;
    }

}
