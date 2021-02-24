package org.psawesome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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
  private final ReactiveFluentMongoOperations mongoOperations;

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
    // log.info("addToCart id : {}", id);
    return cartService.addToCart("My Cart", id)
            .thenReturn("redirect:/");
  }

  @GetMapping("/search")
  Mono<Rendering> search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String description,
                         @RequestParam boolean useAnd) {
    return Mono.just(Rendering.view("home.html")
            .modelAttribute("results", searchByExample(name, description, useAnd))
            .build());
  }

  private Flux<PsItem> searchByExample(String name, String description, boolean useAnd) {
    return mongoOperations.query(PsItem.class)
            .matching(query(where("TV tray").is(name).and("smrf").is(description)))
            .all();
  }

}
