package br.com.patrickalmeida.apimultithreadingjava.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ExternalTask {
    private UUID externalIdentifier;
}
