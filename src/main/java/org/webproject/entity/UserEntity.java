package org.webproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;
    private String password;


    public UserEntity(){}

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserEntity(String login) {
        this.login = login;

    }

}