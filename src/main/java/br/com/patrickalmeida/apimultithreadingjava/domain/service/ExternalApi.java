package br.com.patrickalmeida.apimultithreadingjava.domain.service;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.ExternalTask;

public interface ExternalApi {
    ExternalTask makeRequest(String email);
}
