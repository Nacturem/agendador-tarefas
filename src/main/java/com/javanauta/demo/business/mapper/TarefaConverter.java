package com.javanauta.demo.business.mapper;


import com.javanauta.demo.business.dto.TarefasDTO;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {


    @Mapping(source= "id", target = "id")
    @Mapping(source = "dataEvento", target ="dataEvento")
    @Mapping(source = "dataCriacao", target ="dataCriacao")
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);



}
