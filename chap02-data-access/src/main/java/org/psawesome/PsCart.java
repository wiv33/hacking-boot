package org.psawesome;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:11
 */
@Data
@NoArgsConstructor
public class PsCart {

  @Id
  private String id;
  private List<PsCartItem> cartItemList;
}
