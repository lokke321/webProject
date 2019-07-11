package org.webproject.service;

import org.webproject.dto.Char;
import org.webproject.entity.CharacterEntity;
import org.webproject.entity.UserEntity;

import java.util.Collection;

public interface CharactersService {

    boolean auth (String login, String password);

    Collection<Char> getAll();
    Collection<Char> getCharByCharClass(String name);

}
