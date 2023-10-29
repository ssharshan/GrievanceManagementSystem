package com.scaler.greivance.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@lombok.Getter
@lombok.Setter
@MappedSuperclass
public class BaseClass {

    @Id
    @UuidGenerator
    UUID id;

    @CreatedDate
    Instant createdAt;

    @LastModifiedDate
    Instant updatedAt;

    @CreatedBy
    UUID createdBy;

    @LastModifiedBy
    UUID updatedBy;

}
