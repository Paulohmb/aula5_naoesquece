package com.naoesquece.naoesquece.dto;

import com.naoesquece.naoesquece.enums.Prioridade;
import com.naoesquece.naoesquece.enums.Status;
import com.naoesquece.naoesquece.model.Lembrete;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LembreteResponseDto {

    private Integer id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private Prioridade prioridade;
    private Status status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public static LembreteResponseDto fromEntity(Lembrete lembrete){
        if(lembrete == null){
      return null;
    }
    return new LembreteResponseDto(
            lembrete.getId(),
            lembrete.getTitulo(),
            lembrete.getDescricao(),
            lembrete.getDataHora(),
            lembrete.getPrioridade(),
            lembrete.getStatus(),
            lembrete.getCriadoEm(),
            lembrete.getAtualizadoEm()
    );}

}