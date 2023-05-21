package br.com.patrickalmeida.apimultithreadingjava.domain.repository;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.User;

import java.util.List;

public interface UserSearchInBatch {
    List<User> findAllUsers();
    User findOneByName(String name) throws Exception;
}
