package com.javanauta.demo.business.mapper;


import com.javanauta.demo.business.dto.TarefasDTORecord;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTORecord dto,@MappingTarget TarefasEntity entity);

}
