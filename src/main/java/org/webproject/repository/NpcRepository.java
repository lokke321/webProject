package org.webproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webproject.entity.NpcEntity;

@Repository
public interface NpcRepository extends CrudRepository<NpcEntity, Integer> {

}
