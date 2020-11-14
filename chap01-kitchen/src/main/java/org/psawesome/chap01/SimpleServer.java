package org.psawesome.chap01;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 07:06
 */
@RequiredArgsConstructor
public class SimpleServer {

    private final KitchenService kitchenService;

    public Flux<Dish> doingMyJob() {
        return this.kitchenService.getDishes()
                .map(Dish::deliver);
    }

}
