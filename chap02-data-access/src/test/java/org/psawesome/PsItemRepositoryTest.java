package org.psawesome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-10 수요일 23:42
 */

class PsItemRepositoryTest {

  @Autowired
  PsItemRepository repository;

  @Test
  void testInitRepository() {
    Assertions.assertNotNull(repository);
  }
}