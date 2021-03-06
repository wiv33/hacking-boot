package org.psawesome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.annotation.Id;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsItem {

  @Id
  private String id;
  private String name;
  private String description;
  private double price;

  public PsItem(String name, String description, double price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public PsItem(String name, double price) {
    this.name = name;
    this.price = price;
  }
}
