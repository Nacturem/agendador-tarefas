package com.javanauta.demo.business.mapper;


import com.javanauta.demo.business.dto.TarefasDTORecord;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {


    @Mapping(source= "id", target = "id")
    @Mapping(source = "dataEvento", target ="dataEvento")
    @Mapping(source = "dataCriacao", target ="dataCriacao")
    TarefasEntity paraTarefaEntity(TarefasDTORecord dto);

    TarefasDTORecord paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTORecord> dtos);

    List<TarefasDTORecord> paraListaTarefasDTORecord(List<TarefasEntity> entities);



}
