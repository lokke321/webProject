package org.webproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webproject.entity.ItemEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Integer>{
}
