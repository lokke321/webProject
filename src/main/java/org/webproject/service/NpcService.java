package org.webproject.service;

import org.webproject.dto.Npc;

import java.util.Collection;

public interface NpcService {

    Collection<Npc> findAll();
    Npc findById(Integer npcId);
}
