package com.prueba.blog.repositories;

import com.prueba.blog.models.Exposure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExposureRepository extends JpaRepository<Exposure, Long> {
}
