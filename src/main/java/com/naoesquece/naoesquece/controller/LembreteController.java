package com.naoesquece.naoesquece.controller;

import com.naoesquece.naoesquece.dto.LembreteRequestDto;
import com.naoesquece.naoesquece.dto.LembreteResponseDto;
import com.naoesquece.naoesquece.services.LembreteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lembretes")
public class LembreteController {
    private final LembreteService lembreteService;

    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    public ResponseEntity<LembreteResponseDto> cadastrar(
            @Valid @RequestBody LembreteRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lembreteService.cadastrar(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<LembreteResponseDto>> listar() {
        return ResponseEntity.ok(lembreteService.ListarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LembreteResponseDto> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(lembreteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LembreteResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody LembreteRequestDto requestDto) {
        return ResponseEntity.ok(lembreteService.atualizar(id, requestDto));
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<LembreteResponseDto> concluir(@PathVariable Integer id) {
        return ResponseEntity.ok(lembreteService.concluir(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
            lembreteService.excluir(id);
            return ResponseEntity.noContent().build();

    }
}
