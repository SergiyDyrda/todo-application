package com.limestone.todoboard.service;

import com.limestone.todoboard.TodoboardApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by Sergiy Dyrda on 19.08.2018
 */

public abstract class AbstractServiceTest extends TodoboardApplicationTests {

    @Autowired
    @Qualifier("preLoadMongo")
    private CommandLineRunner preloadMongo;

    public void initTestData() throws Exception {
        preloadMongo.run();
    }
}
