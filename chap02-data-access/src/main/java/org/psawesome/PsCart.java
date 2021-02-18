package org.psawesome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.List;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PsCart {

  @Id
  private String id;
  private List<PsCartItem> cartItems;

  public PsCart(String id) {
    this(id, Collections.emptyList());
  }
}
