package com.user.userservice.controller;

import com.user.userservice.entity.Resource;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/user-service")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    CompletableFuture<ResponseEntity> getUserList() {

        CompletableFuture<List<UserEntity>> users = userService.getUseList();
        return users.thenApply(ResponseEntity::ok);
    }
    @GetMapping("/resource")
    CompletableFuture<ResponseEntity> getResourceList() {

        CompletableFuture<List<Resource>> resources = userService.getResourceList();
        return resources.thenApply(ResponseEntity::ok);
    }

}
