package com.scaler.greivance.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateGrievanceDTO {

    private String title;
    private String description;
    private UUID categoryId;

}
