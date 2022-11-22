package com.formation.velo.service;

import java.util.List;
import java.util.Optional;

import com.formation.velo.model.Defibrillator;

public interface DefibrillatorService {
    List<Defibrillator> findAll();
    Optional<Defibrillator> findById(Integer id);
    Defibrillator save(Defibrillator defibrillator);

    void deleteById(Integer id);

    void delete(Defibrillator defibrillator);

    void saveRecords();

    Optional<Defibrillator> findByRecordId(String recordId);
}
