package com.formation.velo.repository;

import com.formation.velo.model.Defibrillator;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DefibrillatorRepository extends JpaRepository<Defibrillator, Integer> {
    Optional<Defibrillator> findByRecordId(String recordId);
}
