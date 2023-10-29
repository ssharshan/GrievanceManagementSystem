package com.scaler.greivance.controller;

import com.scaler.greivance.dto.AdminCategoryDTO;
import com.scaler.greivance.dto.UserCategoryDTO;
import com.scaler.greivance.model.Category;
import com.scaler.greivance.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{departmentId}/categories")
    public List<UserCategoryDTO> getAllCategoriesByDepartmentId(@PathVariable UUID departmentId) {
        return this.categoryService.getAllUserCategoriesByDepartmentId(departmentId);
    }

    @GetMapping("/admin/{departmentId}/categories/{categoryId}")
    public AdminCategoryDTO getCategoryById(@PathVariable UUID departmentId, @PathVariable UUID categoryId) {
        return this.categoryService.getAdminCategoryDTOById(categoryId);
    }

    @PostMapping("/admin/{departmentId}/categories")
    public AdminCategoryDTO saveCategory(@PathVariable UUID departmentId, @RequestBody AdminCategoryDTO category) {
        return this.categoryService.saveCategoryByDepartmentId(departmentId, category);
    }

    @PutMapping("/admin/{departmentId}/categories/{categoryId}")
    public AdminCategoryDTO updateCategoryById(@PathVariable UUID departmentId, @PathVariable UUID categoryId, @RequestBody AdminCategoryDTO category) {
        return this.categoryService.updateCategoryById(categoryId, category);
    }

    //TODO: Add delete category by id
    //TODO: Add admin list categories
}
