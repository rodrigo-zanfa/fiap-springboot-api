package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MinimunAssistsRequiredException extends RuntimeException {
    private String description;

    public MinimunAssistsRequiredException(String message, String description) {
        super(message);

        this.description = description;
    }
}
