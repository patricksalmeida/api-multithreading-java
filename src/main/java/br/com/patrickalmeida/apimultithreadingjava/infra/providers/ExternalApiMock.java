package br.com.patrickalmeida.apimultithreadingjava.infra.providers;

import br.com.patrickalmeida.apimultithreadingjava.domain.entity.ExternalTask;
import br.com.patrickalmeida.apimultithreadingjava.domain.service.ExternalApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ExternalApiMock implements ExternalApi {
    @Override
    public ExternalTask makeRequest(String email) {
        try {
            log.info("Making mock request with email {}", email);
            Thread.sleep(10000);
            log.info("Ended mock request with email {}", email);
            return new ExternalTask(UUID.randomUUID());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
