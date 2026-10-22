package com.naoesquece.naoesquece.exception;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ValidationErrorResponse extends ErrorResponse{

    private List<CampoErro> erros = new ArrayList<>();

    public ValidationErrorResponse(
            LocalDateTime timestamp,
            int status,
            String error,
            String message,
            String path) {
        super(timestamp, status, error, message, path);

    }

    public void adicionaErro(String campo, String mensagem){
        this.erros.add(new CampoErro(campo, mensagem));
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor

    private static class CampoErro{

        private String campo;
        private String mensagem;

    }

}
