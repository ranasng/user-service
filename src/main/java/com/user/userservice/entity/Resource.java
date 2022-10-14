package com.user.userservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Resource {
    @Id
    private int id;
    private String name;
    private int year;
    private String color;
    private String pantoneValue;
}
