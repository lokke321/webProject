package org.webproject.service;

import org.webproject.dto.User;

import java.util.Collection;

public interface UserService {

    boolean auth (String login, String password);

    Collection<User> getAll();

 }
