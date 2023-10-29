package com.scaler.greivance.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserGrievanceDTO {

    UUID id;
    String title;
    String description;
    UserCategoryDTO category;
    String status;
    List<UserCommentDTO> comments = new ArrayList<>();
//    String createdBy;
//    String updatedBy;
//    String createdAt;
//    String updatedAt;

}
