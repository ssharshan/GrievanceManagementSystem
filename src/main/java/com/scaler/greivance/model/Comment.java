package com.scaler.greivance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Comment extends BaseClass {

    @Column(nullable = false, length = 3000)
    private String comment;
    private String attachments;
    @ManyToOne
    @JoinColumn(name = "grievance_id")
    @JsonBackReference
    private Grievance grievance;

}