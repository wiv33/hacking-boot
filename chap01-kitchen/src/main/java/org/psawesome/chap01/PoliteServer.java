package org.psawesome.chap01;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 07:59
 *
 * 예의 바른 서버
 */
@RequiredArgsConstructor
@Slf4j
public class PoliteServer {
    private final KitchenService kitchen;
    public Flux<Dish> doingMyJob() {
        return this.kitchen.getDishes()
                .doOnNext(dish -> log.info("Thank you for {}!", dish))
                .doOnError(error -> log.error("So sorry about {}", error.getMessage()))
                .doOnComplete(() -> log.info("Thanks for all your hard work!"))
                .doOnCancel(() -> log.info("Thanks for all"))
                .map(Dish::deliver);
    }

}
