package org.webproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    private Integer id;
    private  String login;
    private String password;
}