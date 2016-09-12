package com.ztw.basic.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by zsl-pc on 2016/9/12.
 */
@Repository
public class MongoUserService {

    @Autowired
    MongoTemplate mongoTemplate;

    public void addUser(MongoUser user) {
        mongoTemplate.insert(user);
    }
}
