package com.scaler.greivance.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserDepartmentDTO {

    UUID id;
    String name;
    String description;
    List<UserCategoryDTO> categories = new ArrayList<>();
}
