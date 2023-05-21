package br.com.patrickalmeida.apimultithreadingjava.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String name;
    private String email;
}
