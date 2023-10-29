package com.scaler.greivance.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCategoryDTO {
    UUID id;
    String name;
    String description;
    UUID department;
}
