package com.naoesquece.naoesquece.services;

import com.naoesquece.naoesquece.dto.LembreteRequestDto;
import com.naoesquece.naoesquece.dto.LembreteResponseDto;
import com.naoesquece.naoesquece.enums.Prioridade;
import com.naoesquece.naoesquece.enums.Status;
import com.naoesquece.naoesquece.exception.ResourceNotFoundException;
import com.naoesquece.naoesquece.model.Lembrete;
import com.naoesquece.naoesquece.repository.LembreteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LembreteService {
    private final LembreteRepository lembreteRepository;

    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }
    @Transactional
    public LembreteResponseDto cadastrar(LembreteRequestDto dto) {
        Lembrete lembrete = new Lembrete();
        lembrete.setTitulo(dto.getTitulo());
        lembrete.setDescricao(dto.getDescricao());
        lembrete.setDataHora(dto.getDataHora());
        lembrete.setPrioridade(dto.getPrioridade() == null ? Prioridade.BAIXA : dto.getPrioridade());
        lembrete.setStatus(Status.PENDENTE);
        LocalDateTime agora = LocalDateTime.now();
        lembrete.setCriadoEm(agora);
        lembrete.setAtualizadoEm(agora);
        return LembreteResponseDto.fromEntity(lembreteRepository.save(lembrete));
    }

    public List<LembreteResponseDto> listarTodos() {
        List<Lembrete> lembretes = lembreteRepository.findAll();
        List<LembreteResponseDto> dtos = new ArrayList<>();
        for (Lembrete l : lembretes) {
            dtos.add(LembreteResponseDto.fromEntity(l));
        }
        return dtos;
    }
    public LembreteResponseDto buscarPorId(Integer id) {
        return LembreteResponseDto.fromEntity(obterEntidadePorId(id));
    }

    @Transactional
    public LembreteResponseDto atualizar(Integer id, LembreteRequestDto dto) {
        Lembrete lembrete = obterEntidadePorId(id);
        lembrete.setTitulo(dto.getTitulo());
        lembrete.setDescricao(dto.getDescricao());
        lembrete.setDataHora(dto.getDataHora());
        if (dto.getPrioridade() != null) {
            lembrete.setPrioridade(dto.getPrioridade());
        }
        lembrete.setAtualizadoEm(LocalDateTime.now());
        return LembreteResponseDto.fromEntity(lembreteRepository.save(lembrete));
    }
    @Transactional
    public LembreteResponseDto concluir(Integer id) {
        Lembrete lembrete = obterEntidadePorId(id);
        lembrete.setStatus(Status.CONCLUIDO);
        lembrete.setAtualizadoEm(LocalDateTime.now());
        return LembreteResponseDto.fromEntity(lembreteRepository.save(lembrete));
    }

    @Transactional
    public void excluir(Integer id) {
        lembreteRepository.delete(obterEntidadePorId(id));
    }
    private Lembrete obterEntidadePorId(Integer id){
        Optional<Lembrete> lembrete = lembreteRepository.findById(id);
        if(lembrete.isPresent()){
            return lembrete.get();
        }else{
            throw new ResourceNotFoundException("Lembrete não encontrado");
        }



    }
}
