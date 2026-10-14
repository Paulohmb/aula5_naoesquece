package com.naoesquece.naoesquece.dto;

import com.naoesquece.naoesquece.enums.Prioridade;
import com.naoesquece.naoesquece.enums.Status;
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
public class LembreteRequestDto {
    @NotBlank(message = "o título é obrigatório")
    @Size(min = 3,max = 100,message = "o título deve ter entre 3 a 100 caracteres.")
    private String titulo;
    @Size(max = 255, message = "a descrição não pode exeder 255 caracteres")
    private String descricao;
    @NotNull(message = "A data e hora são obrigatórias")
    @FutureOrPresent (message = "A data e hora do lembrete não pode estar no passado")
    private LocalDateTime dataHora;

    private Prioridade prioridade;

}
