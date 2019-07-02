package org.webproject.service;

import org.webproject.dto.Character;

import java.util.Collection;

public interface CharactersService {

    boolean auth (String login, String password);

    Collection<Character> getAll();

}
