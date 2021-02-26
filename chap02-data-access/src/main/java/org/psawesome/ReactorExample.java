package org.psawesome;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-26 금요일 16:51
 */
public class ReactorExample {

  public static void main(String[] args) {
    Mono<Integer> source;

    if (ThreadLocalRandom.current().nextBoolean()) {
      source = Flux.range(1, 10).elementAt(5);
    } else {
      source = Flux.just(1, 2, 3, 4).elementAt(5);
    }

    source.subscribeOn(Schedulers.parallel())
            .block();
  }
}
