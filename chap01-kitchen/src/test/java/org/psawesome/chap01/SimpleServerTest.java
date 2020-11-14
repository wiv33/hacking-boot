package org.psawesome.chap01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 07:05
 */
public class SimpleServerTest {

    @Test
    void testInitServer() {
        SimpleServer server = new SimpleServer(new KitchenService());
        assertNotNull(server);
        StepVerifier.create(server.doingMyJob())
                .expectNextCount(33)
                .thenCancel()
                .verify()
        ;
    }
}
