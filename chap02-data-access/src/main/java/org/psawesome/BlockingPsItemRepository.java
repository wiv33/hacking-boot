package org.psawesome;

import org.springframework.data.repository.CrudRepository;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-08 월요일 02:13
 */
public interface BlockingPsItemRepository extends CrudRepository<PsItem, String> {
}
