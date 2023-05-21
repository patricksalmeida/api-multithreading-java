package br.com.patrickalmeida.apimultithreadingjava.infra.thread;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Component
public class AsyncTask<T> {

    public List<T> runAsync(List<T> values, Function<T, CompletableFuture<T>> function) throws Exception {
        List<CompletableFuture<T>> completableFutures = new ArrayList<>(0);

        for (T value : values) {
            CompletableFuture<T> futureValue = function.apply(value);
            completableFutures.add(futureValue);
        }

        CompletableFuture[] futures = new CompletableFuture[values.size()];

        CompletableFuture.allOf(completableFutures.toArray(futures)).join();

        List<T> completedValues = new ArrayList<>(0);

        for (CompletableFuture<T> completableFuture : completableFutures) {
            T completedValue = completableFuture.get();
            completedValues.add(completedValue);
        }

        return completedValues;
    }
}
