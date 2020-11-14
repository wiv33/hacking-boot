package org.psawesome.chap01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 07:59
 */
class PoliteServerTest {
    @Test
    void testInitPolite() {
        PoliteServer server = new PoliteServer(new KitchenService());
        Flux<Dish> jobs = server.doingMyJob();
        StepVerifier.create(jobs)
                .expectSubscription()
                .expectNextCount(12)
                .expectNextMatches(Dish::isDelivered)
                .thenCancel()
                .verify()
        ;
    }
}