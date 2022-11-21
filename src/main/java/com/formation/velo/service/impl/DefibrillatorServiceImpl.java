package com.formation.velo.service.impl;

import com.formation.velo.api.client.defibrillator.OpenData;
import com.formation.velo.api.client.defibrillator.OpenDataNantesClient;
import com.formation.velo.model.Defibrillator;
import com.formation.velo.service.DefibrillatorService;

import lombok.extern.java.Log;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.formation.velo.repository.DefibrillatorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Log
public class DefibrillatorServiceImpl implements DefibrillatorService {
    
    public final DefibrillatorRepository defibrillatorRepository;

    public DefibrillatorServiceImpl(DefibrillatorRepository repository) {
        this.defibrillatorRepository = repository;
    }
    
    @Override
    public List<Defibrillator> findAll() {
        return defibrillatorRepository.findAll();
    }

    @Override
    public Optional<Defibrillator> findById(Integer id) {
        return defibrillatorRepository.findById(id);
    }

    @Override
    public Defibrillator save(Defibrillator defibrillator) {
        return defibrillatorRepository.save(defibrillator);
    }

    @Override
    public void deleteById(Integer id) {
        defibrillatorRepository.deleteById(id);
    }

    @Override
    public void delete(Defibrillator defibrillator) {
        defibrillatorRepository.delete(defibrillator);
    }

    @Override
    public void saveRecords() {
        // 1: call OpenData
        String baseUrl = "https://data.nantesmetropole.fr";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        OpenDataNantesClient client = retrofit.create(OpenDataNantesClient.class);
        Call<OpenData> openDataVeloNantesCall = client.getRecords();
        try {
            OpenData openDataVeloNantes = openDataVeloNantesCall.execute().body();
            log.info(openDataVeloNantes.toString());
            // 2: Save data in defibrillator (table)

            Arrays.stream(openDataVeloNantes.getRecords()).forEach(record -> {
                Optional<Defibrillator> defibrillator = findByRecordId(record.getRecordId());
                if(defibrillator.isPresent()) {
                    //update defibrillator
                    defibrillator.get().setDesignation(record.getField().getDesignation());
                    defibrillator.get().setOpeningTime(record.getField().getOpeningTime());
                    defibrillator.get().setClosingTime(record.getField().getClosingTime());
                    defibrillator.get().setOpeningDays(record.getField().getOpeningDays());
                    defibrillator.get().setPosition(record.getField().getPosition());
                    defibrillator.get().setLongitude(record.getField().getGeoShape().getCoordinates()[1]);
                    defibrillator.get().setLatitude(record.getField().getGeoShape().getCoordinates()[0]);
                    defibrillator.get().setAddress(record.getField().getAddress());
                    //save defibrillator
                    save(defibrillator.get());
                } else {
                    //create defibrillator
                    Defibrillator newStation = Defibrillator.builder()
                        .recordId(record.getRecordId())
                        .designation(record.getField().getDesignation())
                        .openingDays(record.getField().getOpeningDays())
                        .openingTime(record.getField().getOpeningTime())
                        .closingTime(record.getField().getClosingTime())
                        .position(record.getField().getPosition())
                        .latitude(record.getField().getGeoShape().getCoordinates()[0])
                        .longitude(record.getField().getGeoShape().getCoordinates()[1])
                        .address(record.getField().getAddress())
                        .build();
                    //save defibrillator
                    save(newStation);
                }
            });

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Defibrillator> findByRecordId(String recordId) {
        return defibrillatorRepository.findByRecordId(recordId);   
    }
}
