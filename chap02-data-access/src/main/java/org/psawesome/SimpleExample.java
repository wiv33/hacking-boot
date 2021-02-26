package org.psawesome;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-26 금요일 16:48
 */
public class SimpleExample {

  public static void main(String[] args) {
    final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    List<Integer> source;

    if (ThreadLocalRandom.current().nextBoolean()) {
      source = IntStream.range(1, 11).boxed()
              .collect(Collectors.toList());
    } else {
      source = List.of(1, 2, 3, 4);
    }

    try {
      es.submit(() -> source.get(5)).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } finally {
      es.shutdown();
    }
  }
}
