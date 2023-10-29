package com.scaler.greivance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Department extends BaseClass{
    String name;
    String description;

    @JsonManagedReference(value = "department-category")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", cascade = jakarta.persistence.CascadeType.REMOVE)
    List<Category> categories = new ArrayList<>();

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
//    List<Grievance> grievances;

}
