package org.psawesome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-16 화요일 23:43
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {


  private final PsItemRepository psItemRepository;
  private final PsCartRepository psCartRepository;

  private final CartService cartService;


  @GetMapping
  Mono<Rendering> home() {
    return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", this.psItemRepository.findAll())
            .modelAttribute("cart", this.psCartRepository.findById("My Cart")
                    .defaultIfEmpty(new PsCart("My Cart"))).build());
  }

  @PostMapping("/add/{id}")
  Mono<String> addToCart(@PathVariable String id) {
    log.info("addToCart id : {}", id);
    return cartService.addToCart("My Cart", id)
            .thenReturn("redirect:/");
  }

}
