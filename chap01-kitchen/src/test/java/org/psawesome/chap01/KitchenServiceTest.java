package org.psawesome.chap01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 06:42
 */
public class KitchenServiceTest {

    @Test
    void testInitDishes() {
        KitchenService service = new KitchenService();

        assertNotNull(service);
        Flux<Dish> result = service.getDishes();
        final List<String> expectedMenu = List.of("Sesame chicken",
                "Lo mein noodles, plain",
                "Sweet & sour beef");
        StepVerifier.create(result.map(Dish::getDescription))
                .expectNextMatches(expectedMenu::contains)
                .expectNextMatches(expectedMenu::contains)
                .expectNextMatches(expectedMenu::contains)
                .expectNextMatches(expectedMenu::contains)
                .expectNextMatches(expectedMenu::contains)
                .thenCancel()
                .verify();

    }
}
