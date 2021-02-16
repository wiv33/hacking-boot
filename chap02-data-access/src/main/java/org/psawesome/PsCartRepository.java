package org.psawesome;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-16 화요일 23:44
 */
@Repository
public interface PsCartRepository extends ReactiveCrudRepository<PsCart, String> {
}
