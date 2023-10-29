package com.scaler.greivance.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCommentDTO {

    UUID id;
    String comment;
    String createdBy;
    String createdAt;
}
