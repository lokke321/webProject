package org.webproject.service;

import org.webproject.dto.UsersChar;
import org.webproject.entity.UsersCharEntity;

import java.util.Collection;

public interface AllCharByUser {

    Collection<UsersChar> findByUser(String sid);

    Collection<UsersChar> getAll();

}
