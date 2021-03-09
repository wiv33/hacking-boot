package org.psawesome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-03-09 화요일 23:46
 */
@DataMongoTest
public class MongoDbSliceTest {

  @Autowired
  PsItemRepository repository;

  @Test
  void testItemRepositorySavesItems() {
    var desc = "description";
    commonAssertions(desc);
  }

  private void commonAssertions(String desc) {
    PsItem psItem = new PsItem("name", desc, 1.99);

    repository.save(psItem)
            .as(StepVerifier::create)
            .expectNextMatches(item -> {
              assertNotNull(item.getId());
              assertEquals("name", item.getName());
              assertEquals(item.getDescription(), "description");
              assertEquals(item.getPrice(), 1.99);
              return true;
            })
            .verifyComplete();
  }

  @Test
  @Disabled
  void testIgnoreSaveTest() {
    var failedTest = "description_2";
    commonAssertions(failedTest);
  }
}
