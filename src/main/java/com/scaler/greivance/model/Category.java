package com.scaler.greivance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Category extends BaseClass {

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonBackReference(value = "department-category")
    private Department department;

//    private UUID createdBy;
//    private UUID updatedBy;

    @OneToMany(mappedBy = "category")
    //@JsonBackReference(value = "grievance-category")
    private List<Grievance> grievances = new ArrayList<>();
}