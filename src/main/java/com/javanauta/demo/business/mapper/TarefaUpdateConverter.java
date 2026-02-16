package com.javanauta.demo.business.mapper;


import com.javanauta.demo.business.dto.TarefasDTO;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO dto,@MappingTarget TarefasEntity entity);

}
