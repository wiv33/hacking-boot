package org.psawesome;

import reactor.core.publisher.Mono;

public class AltInventoryServiceImpl extends CartService {
  public AltInventoryServiceImpl(PsItemRepository psItemRepository, PsCartRepository psCartRepository) {
    super(psItemRepository, psCartRepository);
  }

  @Override
  Mono<PsCart> addToCart(String cartId, String itemId) {
    var myCart = super.psCartRepository.findById(cartId)
            .defaultIfEmpty(new PsCart(cartId))
            .block();

    assert myCart != null;
    return myCart.getCartItems().stream()
            .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
            .findAny()
            .map(this::incrementCartItem)
            .orElseGet(() -> this.psItemRepository.findById(itemId)
                    .map(PsCartItem::new)
                    .map(cartItem -> {
                      myCart.getCartItems().add(cartItem);
                      return cartItem;
                    })
            ).flatMap(cart -> this.psCartRepository.save(myCart));

  }

  private Mono<PsCartItem> incrementCartItem(PsCartItem cartItem) {
    cartItem.increment();
    return Mono.just(cartItem);
  }
}
