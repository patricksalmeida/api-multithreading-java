package br.com.patrickalmeida.apimultithreadingjava.infra.database.repository;

import br.com.patrickalmeida.apimultithreadingjava.infra.database.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FindUserRepository extends JpaRepository<UserTable, Long> {
    Optional<UserTable> findTop1ByName(String name);
}
