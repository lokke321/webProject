package org.webproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webproject.entity.InventoryEntity;
import org.webproject.entity.UsersCharEntity;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {

    InventoryEntity findByCharId(UsersCharEntity usersCharEntity);
}
