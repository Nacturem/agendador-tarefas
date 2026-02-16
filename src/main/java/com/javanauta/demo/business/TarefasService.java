package com.javanauta.demo.business;

import com.javanauta.demo.business.dto.TarefasDTO;
import com.javanauta.demo.business.mapper.TarefaConverter;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import com.javanauta.demo.infrastructure.enums.StatusNotificacao;
import com.javanauta.demo.infrastructure.repository.TarefasRepository;
import com.javanauta.demo.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {


    private final TarefasRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token,TarefasDTO dto){
       String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);


        return tarefaConverter.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

}
