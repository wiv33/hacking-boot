package org.psawesome;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-21 일요일 23:55
 */
@Service
@RequiredArgsConstructor
public class CartService {

  private final PsItemRepository itemRepository;
  private final PsCartRepository psCartRepository;


  Mono<PsCart> addToCart(String cartId, String id) {
    return this.psCartRepository.findById(cartId);
  }
}
