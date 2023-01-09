package com.prueba.blog.mappers;

import com.prueba.blog.dtos.requests.ExposureDTO;
import com.prueba.blog.models.Exposure;
import org.mapstruct.Mapper;

@Mapper
public interface ExposureMapper {

    ExposureDTO mapFromExposure(Exposure exposure);
}
