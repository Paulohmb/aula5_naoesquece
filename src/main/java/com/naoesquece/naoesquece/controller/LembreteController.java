package com.naoesquece.naoesquece.controller;

import com.naoesquece.naoesquece.dto.LembreteRequestDto;
import com.naoesquece.naoesquece.dto.LembreteResponseDto;
import com.naoesquece.naoesquece.enums.Prioridade;
import com.naoesquece.naoesquece.enums.Status;
import com.naoesquece.naoesquece.exception.ResourceNotFoundException;
import com.naoesquece.naoesquece.model.Lembrete;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lembretes")
public class LembreteController {
    private final List<Lembrete> lembretes = new ArrayList<Lembrete>();

    @PostMapping
    public ResponseEntity<LembreteResponseDto> cadastrar(
        @Valid @RequestBody LembreteRequestDto requestDto){
         Lembrete lembrete = new Lembrete();
         lembrete.setId(lembretes.size()+1);
         lembrete.setTitulo(requestDto.getTitulo());
         lembrete.setDescricao(requestDto.getDescricao());
         lembrete.setDataHora(requestDto.getDataHora());
         lembrete.setPrioridade(Prioridade.BAIXA);

         lembrete.setStatus(Status.PENDENTE);
         lembrete.setCriadoEm(LocalDateTime.now());
         lembrete.setAtualizadoEm(LocalDateTime.now());
         lembretes.add(lembrete);
        return ResponseEntity.status(HttpStatus.CREATED).body(LembreteResponseDto.fromEntity(lembrete));
    }

    @GetMapping
    public ResponseEntity<List<LembreteResponseDto>> listar() {
        List<LembreteResponseDto> dtos = new ArrayList<>();
        for(Lembrete lembrete : lembretes) {
            dtos.add(LembreteResponseDto.fromEntity(lembrete));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lembrete> buscar(@PathVariable Integer id) {

            return  ResponseEntity.ok(lembretePorIdOuLancaExcecao(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lembrete> atualizar(@PathVariable Integer id, @RequestBody Lembrete lembrete) {
        Lembrete lembreteExistente = lembretePorIdOuLancaExcecao(id);
        lembreteExistente.setTitulo(lembrete.getTitulo());
        lembreteExistente.setDescricao(lembrete.getDescricao());
        lembreteExistente.setStatus(lembrete.getStatus());
        lembreteExistente.setAtualizadoEm(LocalDateTime.now());

        return ResponseEntity.ok(lembreteExistente);

    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Lembrete> concluir(@PathVariable Integer id) {
        Lembrete lembreteExistente = lembretePorIdOuLancaExcecao(id);
        lembreteExistente.setStatus(Status.CONCLUIDO);
        lembreteExistente.setAtualizadoEm(LocalDateTime.now());
        return ResponseEntity.ok(lembreteExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
            lembretes.remove(lembretePorIdOuLancaExcecao(id));
            return ResponseEntity.noContent().build();

    }

    private Optional<Lembrete> buscarPorId(Integer id) {
        return lembretes
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }
    private Lembrete lembretePorIdOuLancaExcecao(Integer id){
        for(Lembrete l: lembretes){
            if(l.getId().equals(id)){
                return l;
            }
        }
    throw new ResourceNotFoundException(
            "Lembrete nao encontrado coma ID:" + id.toString()
    );

    }
}
