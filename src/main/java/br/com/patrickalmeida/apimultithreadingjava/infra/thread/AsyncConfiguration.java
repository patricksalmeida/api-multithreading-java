package br.com.patrickalmeida.apimultithreadingjava.infra.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@EnableAsync
@Configuration
public class AsyncConfiguration {
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor()  {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        log.warn("Available processors to open threads {}", availableProcessors);

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(availableProcessors);
        executor.setMaxPoolSize(availableProcessors);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("AsynchThread-");
        executor.initialize();
        return executor;
    }
}
