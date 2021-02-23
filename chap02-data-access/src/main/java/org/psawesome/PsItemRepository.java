package org.psawesome;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:12
 */
@Repository
public interface PsItemRepository extends ReactiveCrudRepository<PsItem, String> {

  Flux<PsItem> findByNameContaining(String partialName);
}
