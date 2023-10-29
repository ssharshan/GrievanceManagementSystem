package com.scaler.greivance.service;

import com.scaler.greivance.dto.AdminCategoryDTO;
import com.scaler.greivance.dto.UserCategoryDTO;
import com.scaler.greivance.model.Category;
import com.scaler.greivance.model.Department;
import com.scaler.greivance.repository.CategoryRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private DepartmentService departmentService;

    private GrievanceService grievanceService;

    public CategoryService(CategoryRepository categoryRepository, @Lazy DepartmentService departmentService, @Lazy GrievanceService grievanceService) {
        this.categoryRepository = categoryRepository;
        this.departmentService = departmentService;
        this.grievanceService = grievanceService;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public AdminCategoryDTO getAdminCategoryDTOById(UUID id) {
        Category category = getCategoryById(id);
        if(category != null) {
            return toAdminCategoryDTO(category);
        }
        return null;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategoryById(UUID id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        if (categoryToUpdate != null) {
            categoryToUpdate.setName(category.getName());
            categoryToUpdate.setDescription(category.getDescription());
            return categoryRepository.save(categoryToUpdate);
        }
        return null;
    }

    public AdminCategoryDTO updateCategoryById(UUID id, AdminCategoryDTO category) {
        Category categoryToUpdate = toCategory(category);
        Category updatedCategory = updateCategoryById(id, categoryToUpdate);
        if (updatedCategory != null) {
            return toAdminCategoryDTO(updatedCategory);
        }
        return null;
    }

    public List<Category> getAllCategoriesByDepartmentId(UUID departmentId) {
        List<Category> categories = categoryRepository.findAllByDepartmentId(departmentId);
        return categories;
    }

    public List<UserCategoryDTO> getAllUserCategoriesByDepartmentId(UUID departmentId) {
        List<Category> categories = getAllCategoriesByDepartmentId(departmentId);
        return categories.stream().map(category -> toUserCategoryDTO(category)).toList();

    }

    public Category saveCategoryByDepartmentId(UUID departmentId, Category category) {
        Department department = departmentService.getDepartmentById(departmentId);
        category.setDepartment(department);
        return categoryRepository.save(category);
    }

    public AdminCategoryDTO saveCategoryByDepartmentId(UUID departmentId, AdminCategoryDTO category) {

        Category categoryToSave = toCategory(category);
        categoryToSave =  saveCategoryByDepartmentId(departmentId, categoryToSave);
        return toAdminCategoryDTO(categoryToSave);
    }

    public AdminCategoryDTO toAdminCategoryDTO(Category category) {
        AdminCategoryDTO adminCategoryDTO = new AdminCategoryDTO();
        adminCategoryDTO.setId(category.getId());
        adminCategoryDTO.setName(category.getName());
        adminCategoryDTO.setDescription(category.getDescription());
        category.getGrievances().forEach(grievance -> {
            adminCategoryDTO.getGrievances().add(grievance.getId());
        });
        return adminCategoryDTO;
    }

    public Category toCategory(AdminCategoryDTO adminCategoryDTO) {
        Category category = new Category();
        category.setId(adminCategoryDTO.getId());
        category.setName(adminCategoryDTO.getName());
        category.setDescription(adminCategoryDTO.getDescription());

        grievanceService.getAllGrievancesByCategoryId(adminCategoryDTO.getId()).forEach(grievance -> {
            category.getGrievances().add(grievance);
        });
        return category;
    }

    public UserCategoryDTO toUserCategoryDTO(Category category) {
        UserCategoryDTO userCategoryDTO = new UserCategoryDTO();
        userCategoryDTO.setId(category.getId());
        userCategoryDTO.setName(category.getName());
        userCategoryDTO.setDescription(category.getDescription());
        userCategoryDTO.setDepartment(category.getDepartment().getId());
        return userCategoryDTO;
    }
}
