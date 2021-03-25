package org.psawesome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class BlockHoundSleepTest {

  private AltInventoryServiceImpl inventoryService;

  @BeforeEach
  void setUp() {

  }

  @Test
  @DisplayName("blocking error일 때 처리")
  void testThreadSleepIsABlockingCall() {
    Mono.delay(Duration.ofSeconds(1))
            .log()
            .flatMap(aLong -> {
              try {
                Thread.sleep(1000);
                return Mono.just(aLong);
              } catch (InterruptedException e) {
                /*
                 * 출력되지 않음
                 * cancel() 호출
                 * */
//              if Not used BlockHound : java.lang.AssertionError: expectation "expectErrorMatches" failed (expected: onError(); actual: onNext(0))
                System.out.println("ps error : " + e.getMessage());
                Assertions.assertTrue(e.getMessage().contains("failed (expected: onError(); actual: onNext(0))"));
                return Mono.error(e);
              }
            })
//            .doOnError(throwable -> System.out.println("throwable = " + throwable.getMessage()))
//            .doOnCancel(() -> System.out.println("true = " + true))
            .log("check error")
            .as(StepVerifier::create)
            .verifyErrorMatches(throwable -> {
              Assertions.assertTrue(throwable.getMessage().contains("Blocking call! java.lang.Thread.sleep"));
              return true;
            })
    ;

  }
}
