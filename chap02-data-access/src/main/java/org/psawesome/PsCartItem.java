package org.psawesome;

import lombok.Data;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:10
 */
@Data
public class PsCartItem {

  private PsItem item;
  private int quantity;

  public PsCartItem(PsItem item) {
    this.item = item;
    this.quantity = 1;
  }
}
