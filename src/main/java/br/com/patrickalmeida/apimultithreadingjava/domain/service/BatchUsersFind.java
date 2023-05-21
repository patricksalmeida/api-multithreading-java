package br.com.patrickalmeida.apimultithreadingjava.domain.service;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BatchUsersFind {
    List<User> getAllUsers();
    CompletableFuture<User> getOneByName(String name) throws Exception;
}
