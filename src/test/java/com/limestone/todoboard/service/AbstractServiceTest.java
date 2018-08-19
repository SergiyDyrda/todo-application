package com.limestone.todoboard.service;

import com.limestone.todoboard.TodoboardApplicationTests;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergiy Dyrda on 19.08.2018
 */

public abstract class AbstractServiceTest extends TodoboardApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void initTestData(String initFileName, Class<?> clazz) throws IOException {
        File file = new ClassPathResource(initFileName).getFile();
        List<Document> collect = Files.readAllLines(file.toPath())
                .stream()
                .map(Document::parse)
                .collect(Collectors.toList());
        mongoTemplate.insert(collect, clazz);
    }

    public void cleanUpTestData(Class<?> clazz) {
        mongoTemplate.dropCollection(clazz);
    }
}
