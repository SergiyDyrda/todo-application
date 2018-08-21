package com.limestone.todoboard.configuration;


import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableMongoRepositories(basePackages = "com.limestone.todoboard.**.repository")
public class DatabaseInit {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean(name = "preLoadMongo")
    CommandLineRunner preLoadMongo() throws Exception {
        return args -> {
            cleanModelData(User.class);
            initModelData("db/users-testdata.json", User.class);
            cleanModelData(Ticket.class);
            initModelData("db/tickets-testdata.json", Ticket.class);
        };
    }

    private void initModelData(String initFile, Class<?> clazz) throws IOException {
        File file = new ClassPathResource(initFile).getFile();
        List<Document> entities = Files.readAllLines(file.toPath())
                .stream()
                .map(Document::parse)
                .collect(Collectors.toList());
        mongoTemplate.insert(entities, clazz);
    }

    private void cleanModelData(Class<?> clazz) {
        mongoTemplate.dropCollection(clazz);
    }

}
