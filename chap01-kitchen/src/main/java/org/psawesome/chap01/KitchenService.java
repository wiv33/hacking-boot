package org.psawesome.chap01;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 06:47
 */
public class KitchenService {
    public Flux<Dish> getDishes() {
        return Flux.<Dish>generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }

    private final List<Dish> menu = List.of(
            new Dish("Sesame chicken"),
            new Dish("Lo mein noodles, plain"),
            new Dish("Sweet & sour beef"));

    private Dish randomDish() {
        return menu.get(ThreadLocalRandom.current().nextInt(menu.size()));
    }
}
