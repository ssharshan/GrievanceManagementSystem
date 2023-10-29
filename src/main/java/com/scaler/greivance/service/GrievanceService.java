package com.scaler.greivance.service;

import com.scaler.greivance.dto.CreateGrievanceDTO;
import com.scaler.greivance.dto.StatusUpdateDTO;
import com.scaler.greivance.dto.UserCategoryDTO;
import com.scaler.greivance.dto.UserGrievanceDTO;
import com.scaler.greivance.model.Category;
import com.scaler.greivance.model.Grievance;
import com.scaler.greivance.repository.GrievanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GrievanceService {

    private GrievanceRepository grievanceRepository;
    private CategoryService categoryService;

    private CommentService commentService;

    public GrievanceService(GrievanceRepository grievanceRepository, CategoryService categoryService, CommentService commentService) {
        this.grievanceRepository = grievanceRepository;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    public List<UserGrievanceDTO> getAllGrievances() {
        return grievanceRepository.findAll()
                .stream()
                .map(grievance ->toUserGrievanceDTO(grievance))
                .toList();
    }

    public UserGrievanceDTO getGrievanceById(UUID id) {
        Grievance grievance = grievanceRepository.findById(id).orElse(null);
        return toUserGrievanceDTO(grievance);
    }

    public Grievance saveGrievance(Grievance grievance) {
        return grievanceRepository.save(grievance);
    }

    public void deleteGrievanceById(UUID id) {
        grievanceRepository.deleteById(id);
    }

    public Grievance updateGrievanceById(UUID id, Grievance grievance) {
        Grievance grievanceToUpdate = grievanceRepository.findById(id).orElse(null);
        if (grievanceToUpdate != null) {
            grievanceToUpdate.setTitle(grievance.getTitle());
            grievanceToUpdate.setDescription(grievance.getDescription());
            grievanceToUpdate.setCategory(grievance.getCategory());
//            grievanceToUpdate.setDepartment(grievance.getDepartment());
            return grievanceRepository.save(grievanceToUpdate);
        }
        return null;
    }

    public UserGrievanceDTO updateGrievanceStatusById(UUID id, StatusUpdateDTO statusUpdateDTO) {
        Grievance grievanceToUpdate = grievanceRepository.findById(id).orElse(null);
        if (grievanceToUpdate != null) {
            grievanceToUpdate.setStatus(statusUpdateDTO.getStatus());
            return toUserGrievanceDTO(grievanceRepository.save(grievanceToUpdate));
        }
        return null;
    }

    public UserGrievanceDTO saveGrievanceDTO(CreateGrievanceDTO grievanceDTO) {

        Category category = categoryService.getCategoryById(grievanceDTO.getCategoryId());

        Grievance grievance = new Grievance();
        grievance.setTitle(grievanceDTO.getTitle());
        grievance.setDescription(grievanceDTO.getDescription());
        grievance.setCategory(category);
        grievance.setStatus("OPEN");
        grievance = grievanceRepository.save(grievance);

        return toUserGrievanceDTO(grievance);
    }

    public List<Grievance> getAllGrievancesByCategoryId(UUID categoryId) {
        List<Grievance> grievances = grievanceRepository.findAllByCategoryId(categoryId);
        return grievances;
    }

    public UserGrievanceDTO toUserGrievanceDTO(Grievance grievance) {
        UserGrievanceDTO userGrievanceDTO = new UserGrievanceDTO();
        userGrievanceDTO.setId(grievance.getId());
        userGrievanceDTO.setTitle(grievance.getTitle());
        userGrievanceDTO.setDescription(grievance.getDescription());
        userGrievanceDTO.setStatus(grievance.getStatus());

        UserCategoryDTO userCategoryDTO = categoryService.toUserCategoryDTO(grievance.getCategory());
        userGrievanceDTO.setCategory(userCategoryDTO);

        grievance.getComments().forEach(comment -> {
            userGrievanceDTO.getComments().add(commentService.toUserCommentDTO(comment));
        });

        return userGrievanceDTO;
    }



}
