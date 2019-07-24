package org.webproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webproject.dto.User;
import org.webproject.entity.UserEntity;
import org.webproject.entity.UsersCharEntity;

@Repository
public interface UsersCharRepository extends CrudRepository <UsersCharEntity, Integer> {

   Iterable<UsersCharEntity> findAllByUser(UserEntity userEntity);
   UsersCharEntity findByCharname(String charname);

}
