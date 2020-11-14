package org.psawesome.chap01;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * package: org.psawesome.chap01
 * author: PS
 * DATE: 2020-11-14 토요일 06:48
 */
@RequiredArgsConstructor
@Data
public class Dish {
    private final String description;
    private boolean delivered;

    public static Dish deliver(Dish dish) {
        final Dish deliveredDish = new Dish(dish.getDescription());
        deliveredDish.setDelivered(true);
        return deliveredDish;
    }

}
