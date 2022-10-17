package com.user.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.userservice.entity.Combined;
import com.user.userservice.entity.Resource;
import com.user.userservice.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Executor taskExecutor;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Async
    public CompletableFuture<List<UserEntity>> getUseList() {
        long startTime = System.currentTimeMillis();
        String url = "https://reqres.in/api/users?page=1&per_page=3";
        String json = restTemplate.getForObject(url, String.class);

        List<UserEntity> users = null;        
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode usersNode = jsonNode.get("data");
            users = objectMapper.treeToValue(usersNode, List.class);
            
            logger.info("User Thread Name {}", Thread.currentThread().getName());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        logger.info("User Exexution Time {}", endTime - startTime);
        return CompletableFuture.completedFuture(users);
    }

    @Override
    public CompletableFuture<List<Resource>> getResourceList() {
        long startTime = System.currentTimeMillis();
        String url = "https://reqres.in/api/resource?page=1&per_page=20";
        CompletableFuture<List<Resource>> resource = CompletableFuture.supplyAsync(() -> {
            String json = restTemplate.getForObject(url, String.class);
            List<Resource> res = null;
            try {
                JsonNode jsonNode = objectMapper.readTree(json);
                JsonNode resourceNode = jsonNode.get("data");
                res = objectMapper.treeToValue(resourceNode, List.class);
                logger.info("Resource Thread name {}", Thread.currentThread().getName());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return res;
        }, taskExecutor);
        long endTime = System.currentTimeMillis();
        logger.info("Resource Execution Time {} ", endTime - startTime);
        return resource;
    }

	@Override
	public CompletableFuture<Combined> getCombinedResult() {
		CompletableFuture<List<UserEntity>> users = this.getUseList();
		CompletableFuture<List<Resource>> resources = this.getResourceList();
		
		return CompletableFuture.allOf(users, resources).thenApplyAsync(none -> {
			Combined combinedResult = new Combined();
			List<UserEntity> userList = users.join();
			if (null != userList) {
				combinedResult.setUsr(userList);
			}
			List<Resource> resourceList = resources.join();
			if (null != resourceList) {
				combinedResult.setReso(resourceList);
			}
			return combinedResult;
		},taskExecutor).exceptionally(ex -> {
			logger.error(ex.getMessage());
			return null;
		});
		
	}

	
  

}
