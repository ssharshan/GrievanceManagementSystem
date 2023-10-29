package com.scaler.greivance.repository;

import com.scaler.greivance.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, UUID> {
    List<Grievance> findAllByCategoryId(UUID categoryId);
}