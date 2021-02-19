package org.psawesome;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-16 화요일 23:43
 */
@Controller
@RequiredArgsConstructor
public class HomeController {


  private final PsItemRepository psItemRepository;
  private final PsCartRepository psCartRepository;


  @GetMapping
  Mono<Rendering> home() {
    return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", this.psItemRepository.findAll())
            .modelAttribute("cart", this.psCartRepository.findById("My Cart")
                    .defaultIfEmpty(new PsCart("My Cart"))).build());
  }

  @PostMapping("/add/{id}")
  Mono<String> addToCart(@PathVariable String id) {
    return this.psCartRepository.findById("My Cart")
            .defaultIfEmpty(new PsCart("My Cart"))
            .flatMap(cart -> cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem()
                            .getId().equals(id))
                    .findAny()
                    .map(cartItem -> {
                      cartItem.increment();
                      return Mono.just(cart);
                    })
                    .orElseGet(() -> this.psItemRepository.findById(id)
                            .map(PsCartItem::new)
                            .map(cartItem -> {
                              cart.getCartItems().add(cartItem);
                              return cart;
                            })
                    )
            )
            .flatMap(this.psCartRepository::save)
            .thenReturn("redirect:/");
  }
}
