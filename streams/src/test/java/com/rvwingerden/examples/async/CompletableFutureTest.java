package com.rvwingerden.examples.async;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    @Test
    public void simpleTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Test");
        System.out.println(future.get());
    }

    @Test
    public void waiting1Seccond() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(10000);
            future.complete("Poeh hee, dat was moeilijk... eindelijk klaar!!!");
            return null;
        });
        Thread.sleep(3000);
        System.out.println(LocalDateTime.now() + " - three seconds have passed...");
        Thread.sleep(3000);
        System.out.println(LocalDateTime.now() + " - another three seconds...");
        Thread.sleep(3000);
        System.out.println(LocalDateTime.now() + " - within one second the future returns...");
        String result = future.get();
        System.out.println(LocalDateTime.now() + " - " + result);
    }

}
