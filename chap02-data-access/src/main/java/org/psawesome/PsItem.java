package org.psawesome;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:08
 */
@Data
public class PsItem {

  @Id
  private String id;
  private String name;
  private double price;
}
