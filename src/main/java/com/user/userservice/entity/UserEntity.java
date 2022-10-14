package com.user.userservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserEntity {
    @Id
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
}
