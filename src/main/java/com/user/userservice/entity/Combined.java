package com.user.userservice.entity;

import java.util.List;

import lombok.Data;

@Data
public class Combined {
    private List<UserEntity> usr;
    private List<Resource> reso;
}
