package org.psawesome;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:12
 */
public interface PsItemRepository extends ReactiveCrudRepository<PsItem, String> {
}
