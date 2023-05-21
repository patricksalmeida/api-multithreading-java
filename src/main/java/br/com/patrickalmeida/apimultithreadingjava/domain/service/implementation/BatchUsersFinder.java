package br.com.patrickalmeida.apimultithreadingjava.domain.service.implementation;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.User;
import br.com.patrickalmeida.apimultithreadingjava.domain.repository.UserSearchInBatch;
import br.com.patrickalmeida.apimultithreadingjava.domain.service.BatchUsersFind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class BatchUsersFinder implements BatchUsersFind {
    @Autowired
    private UserSearchInBatch userSearchInBatch;
    @Override
    public List<User> getAllUsers() {
        return userSearchInBatch.findAllUsers();
    }

    @Async
    @Override
    public CompletableFuture<User> getOneByName(String name) throws Exception {
//        int min = 100;
//        int max = 6000;
//        long randomDiff = Double.valueOf(Math.random()*(max-min+1)+min).longValue();
        long randomDiff = 700;
        log.warn("Sleeping {} ms", randomDiff);
        Thread.sleep(randomDiff);
        return CompletableFuture.completedFuture(userSearchInBatch.findOneByName(name));
    }
}
