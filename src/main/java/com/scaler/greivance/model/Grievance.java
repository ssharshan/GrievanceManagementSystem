package com.scaler.greivance.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Grievance extends BaseClass{
    String title;
    String description;
    //@JsonManagedReference(value = "grievance-category")
    @ManyToOne
    Category category;


//    Department department;
    String status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grievance")
    List<Comment> comments = new ArrayList<>();

    String attachments;
    UUID createdBy;
    UUID assignedTo;
    UUID resolvedBy;
    UUID updatedBy;


}
