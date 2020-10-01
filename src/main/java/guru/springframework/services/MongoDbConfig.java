package guru.springframework.services;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Works as is without config; could be configured by application.properties
 * Another way - extends AbstractMongoClientConfiguration
 * But it seems redundant - required to override getDatabaseName, getMappingBasePackages - aka @ComponentScan
 * and mongoClient
 */
//@Configuration
@EnableMongoRepositories(basePackages = "guru.springframework.repositories")
public class MongoDbConfig {
    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbUri;

    @Bean
    public MongoClient mongo() {
//        MongoClients.create();
        ConnectionString connectionString = new ConnectionString(mongoDbUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) throws Exception {
        return new MongoTemplate(mongoClient, mongoDbName);
    }
}
