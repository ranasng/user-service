package com.user.userservice.service;

import com.user.userservice.entity.Resource;
import com.user.userservice.entity.UserEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
     CompletableFuture<List<UserEntity>> getUseList();
     CompletableFuture<List<Resource>> getResourceList();

}
