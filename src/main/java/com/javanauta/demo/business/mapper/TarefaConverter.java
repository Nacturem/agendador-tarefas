package com.javanauta.demo.business.mapper;


import com.javanauta.demo.business.dto.TarefasDTO;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);


    TarefasDTO paraTarefaDTO(TarefasEntity entity);

}
