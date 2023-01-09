package com.prueba.blog.services;

import com.prueba.blog.models.Exposure;
import com.prueba.blog.repositories.ExposureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExposureService {

    private ExposureRepository exposureRepository;

    @Autowired
    public ExposureService(ExposureRepository exposureRepository) {
        this.exposureRepository = exposureRepository;
    }

    @Transactional(readOnly = true)
    public Exposure getById(Long id) {
        return exposureRepository.findById(id).get();
    }
}
