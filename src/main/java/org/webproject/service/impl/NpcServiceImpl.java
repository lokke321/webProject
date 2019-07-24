package org.webproject.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webproject.dto.Npc;
import org.webproject.entity.NpcEntity;
import org.webproject.exeption.QuestException;
import org.webproject.repository.NpcRepository;
import org.webproject.service.NpcService;

import java.util.Collection;

@Service
public class NpcServiceImpl extends AbstractService implements NpcService {

   private final NpcRepository npcRepository;

    public NpcServiceImpl(ModelMapper modelMapper, NpcRepository npcRepository) {
        super(modelMapper);
        this.npcRepository = npcRepository;
    }

    @Override
    public Collection<Npc> findAll() {
        Iterable<NpcEntity> values = npcRepository.findAll();
        return findAllEntities(values, Npc.class);
    }

    @Override
    public Npc findById(Integer npcId) {
        return npcRepository.findById(npcId) // Optional<RoomEntity>
                .map(entity -> modelMapper.map(entity, Npc.class))
                .orElseThrow(QuestException::new);
    }
}
