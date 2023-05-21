package br.com.patrickalmeida.apimultithreadingjava.api;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.ExternalTask;
import br.com.patrickalmeida.apimultithreadingjava.domain.entity.User;
import br.com.patrickalmeida.apimultithreadingjava.domain.service.BatchUsersFind;
import br.com.patrickalmeida.apimultithreadingjava.domain.service.ExternalApi;
import br.com.patrickalmeida.apimultithreadingjava.infra.thread.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/users")
public class UsersResource {
    @Autowired
    private BatchUsersFind batchUsersFind;
    @Autowired
    private ExternalApi externalApi;
    @Autowired
    private AsyncTask<User> asyncTask;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = batchUsersFind.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/external-call")
    public ResponseEntity<?> makeExternalApiCall() throws Exception {
        List<User> usersToCallAsync = Arrays.asList(
                User.builder().name("Perceval").build(),
                User.builder().name("Gerik").build(),
                User.builder().name("Karyn").build(),
                User.builder().name("Margi").build(),
                User.builder().name("Vassily").build(),
                User.builder().name("Leeland").build(),
                User.builder().name("Erminia").build(),
                User.builder().name("Ruby").build(),
                User.builder().name("Emogene").build(),
                User.builder().name("Mar").build(),
                User.builder().name("Yetty").build()
        );

        List<User> users = asyncTask.runAsync(usersToCallAsync, user -> {
            try {
                return getUser(user.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return ResponseEntity.ok(users);
    }

    public CompletableFuture<User> getUser(String name) throws Exception {
        return batchUsersFind.getOneByName(name);
    }

    @Async
    public CompletableFuture<ExternalTask> makeExternalRequest(User user) {
        return CompletableFuture.completedFuture(externalApi.makeRequest(user.getEmail()));
    }
}
