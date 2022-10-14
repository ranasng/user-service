package com.user.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.userservice.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {
    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;

    @Override
    @Async
    public CompletableFuture<List<UserEntity>> getUseList() {
        long startTime=System.currentTimeMillis();
        String url = "https://reqres.in/api/users?page=1&per_page=10";
        String json = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserEntity> users = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode usersNode = jsonNode.get("data");
            users = objectMapper.treeToValue(usersNode, List.class);

            logger.info("Thread Name {}",Thread.currentThread().getName());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        long endTime=System.currentTimeMillis();
        long time=endTime-startTime;
        System.out.println("Time "+time+" ms");
        return CompletableFuture.completedFuture(users);
    }

}
