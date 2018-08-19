package com.limestone.todoboard.config;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

//@Configuration
public class TestMongoConfiguration {

//    private static final String MONGO_DB_URL = "localhost";
//    private static final String MONGO_DB_NAME = "todoboard";
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws IOException {
//        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//        mongo.setBindIp(MONGO_DB_URL);
//        MongoClient mongoClient = mongo.getObject();
//        return new MongoTemplate(mongoClient, MONGO_DB_NAME);
//    }
//
//    @Autowired
//    private MongoSpringDataTicketRepository innerRepository;
//
//    @Bean
//    public MongoTicketRepository ticketRepository() {
//        return new MongoTicketRepository(innerRepository);
//    }
//
//    @Bean
//    public MongoTicketService ticketService() {
//        return new MongoTicketService(ticketRepository());
//    }
}
