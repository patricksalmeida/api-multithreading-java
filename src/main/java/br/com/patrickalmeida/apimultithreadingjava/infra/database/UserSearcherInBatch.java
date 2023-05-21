package br.com.patrickalmeida.apimultithreadingjava.infra.database;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.User;
import br.com.patrickalmeida.apimultithreadingjava.domain.repository.UserSearchInBatch;
import br.com.patrickalmeida.apimultithreadingjava.infra.database.model.UserTable;
import br.com.patrickalmeida.apimultithreadingjava.infra.database.repository.FindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSearcherInBatch implements UserSearchInBatch {
    @Autowired
    private FindUserRepository findUserRepository;
    @Override
    public List<User> findAllUsers() {
        return findUserRepository.findAll().stream()
                .map(user -> User.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public User findOneByName(String name) throws Exception {
        Optional<UserTable> userTable = findUserRepository.findTop1ByName(name);
        if (userTable.isEmpty()) {
            throw new Exception("User not found");
        }
        return User.builder()
                .email(userTable.get().getEmail())
                .name(userTable.get().getName())
                .build();
    }
}
