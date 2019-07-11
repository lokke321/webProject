package org.webproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webproject.entity.CharacterEntity;

@Repository
public interface CharactersRepository extends CrudRepository<CharacterEntity, Integer> {

    CharacterEntity findNameById(Integer id);

}
