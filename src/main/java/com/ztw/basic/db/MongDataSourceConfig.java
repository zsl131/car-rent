package com.ztw.basic.db;

import com.mongodb.*;
import com.ztw.basic.exception.SystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by zsl-pc on 2016/9/12.
 */
@Configuration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@Component
@EnableMongoRepositories
@PropertySource("config/mondb.properties")
public class MongDataSourceConfig extends AbstractMongoConfiguration {

    @Value("${mongo.database}")
    private String dbname;

    @Value("${mongo.host}")
    private String dbhost;

    @Value("${mongo.port}")
    private String dbport;

    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;

    public MongDataSourceConfig() {
        if(dbport==null || "".equalsIgnoreCase(dbport.trim())) {
            dbport = "27017";
        }

        System.out.println(this.dbhost+","+this.dbport+","+this.dbname+","+this.username+","+this.password);
    }

    @Override
    protected String getDatabaseName() {
        return this.dbname;
    }

    @Override
    @Bean(name = "mongods")
    public Mongo mongo() {
        return buildMongo();
    }

    public MongoClient buildMongo() {
        try {
        /*ServerAddress serverAddress = new ServerAddress(dbhost, Integer.valueOf(dbport));
        MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbname, password.toCharArray());
        Mongo mongo = new MongoClient(serverAddress, Arrays.asList(credential));
        mongo.setWriteConcern(WriteConcern.SAFE);
//        return null;
        return mongo;*/

            ServerAddress serverAddress = new ServerAddress(dbhost+":"+dbport);
            MongoCredential credential = MongoCredential.createCredential(username, dbname, password.toCharArray());

            MongoClient client = new MongoClient(serverAddress, Arrays.asList(credential), MongoClientOptions.builder().serverSelectionTimeout(1000).build());

            return client;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new SystemException("Mongo 数据库连接失败！");
        }
    }
}
