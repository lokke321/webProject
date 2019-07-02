package org.webproject.service;

import org.webproject.dto.Char;

import java.util.Collection;

public interface CharactersService {

    boolean auth (String login, String password);

    Collection<Char> getAll();

}
