package org.webproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webproject.entity.UsersCharEntity;

@Repository
public interface UsersCharRepository extends CrudRepository <UsersCharEntity, Integer> {

    UsersCharEntity findByCharClass(String charname);
    UsersCharEntity findById();
}
