package org.psawesome;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}
