package com.naoesquece.naoesquece.model;

import com.naoesquece.naoesquece.enums.Prioridade;
import com.naoesquece.naoesquece.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lembrete {
    private Integer id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private Prioridade prioridade;
    private Status status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
