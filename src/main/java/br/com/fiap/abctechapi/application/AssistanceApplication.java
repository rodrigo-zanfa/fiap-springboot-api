package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistsDto;

import java.util.List;

public interface AssistanceApplication {
    List<AssistsDto> getAssists();
}
