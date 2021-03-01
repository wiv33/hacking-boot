package org.psawesome;

import de.flapdoodle.embed.mongo.Command;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoOperationsExtensionsKt;
import org.springframework.stereotype.Component;

/**
 * package: org.psawesome
 * author: PS
 * DATE: 2021-02-14 일요일 23:37
 */
@SpringBootApplication
public class DataAccessApplication {
  public static void main(String[] args) {
    BlockHound.install();
    SpringApplication.run(DataAccessApplication.class, args);
  }

}

@Component
class RepositoryDatabaseLoader {

  @Bean
  CommandLineRunner initRepositoryData(BlockingPsItemRepository blockingPsItemRepository) {
    return args -> {
      blockingPsItemRepository.save(new PsItem("alf alarm clock", 19.99));
      blockingPsItemRepository.save(new PsItem("Smurf TV tray", 24.99));
    };
  }
}

@Component
class TemplateDatabaseLoader {
  @Bean
  CommandLineRunner initTemplateData(MongoOperations mongoOperations) {
    return args -> {
      mongoOperations.save(new PsItem("ps awesome", 33.33));
      mongoOperations.save(new PsItem("we are the", 26.11));
    };
  }

}