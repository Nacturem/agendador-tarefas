package com.javanauta.demo.business;

import com.javanauta.demo.business.dto.TarefasDTORecord;
import com.javanauta.demo.business.mapper.TarefaConverter;
import com.javanauta.demo.business.mapper.TarefaUpdateConverter;
import com.javanauta.demo.infrastructure.entity.TarefasEntity;
import com.javanauta.demo.infrastructure.enums.StatusNotificacao;
import com.javanauta.demo.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.demo.infrastructure.repository.TarefasRepository;
import com.javanauta.demo.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {


    private final TarefasRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTORecord gravarTarefa(String token,TarefasDTORecord dto){
       String email = jwtUtil.extractUsername(token.substring(7));
        TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(), dto.descricao(), LocalDateTime.now(),
                dto.dataEvento(), email, null,StatusNotificacao.PENDENTE);


        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);


        return tarefaConverter.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefasDTORecord(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacao(dataInicial, dataFinal,
                        StatusNotificacao.PENDENTE));
    }

    public List<TarefasDTORecord> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefasDTORecord(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try{
            tarefaRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id,
                    e.getCause());
        }
    }
    public TarefasDTORecord alteraStatus(StatusNotificacao status, String id){
        try {
        TarefasEntity entity = tarefaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada" + id));

        entity.setStatusNotificacao(status);
        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());

        }
    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada" + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }


    }}
