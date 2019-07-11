package org.webproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webproject.entity.*;
import org.webproject.exeption.ChatException;
import org.webproject.repository.*;
import org.webproject.service.AuthorizationSessionService;
import org.webproject.service.CreateUserCharService;


@Service
public class CreateUserCharServiceImpl implements CreateUserCharService {

    private final UserRepository userRepository;
    private final CharactersRepository charactersRepository;
    private final UsersCharRepository usersCharRepository;
    private final InventoryRepository inventoryRepository;
    private final AuthorizationSessionService authSessionService;

    @Autowired
    public CreateUserCharServiceImpl(UserRepository userRepository, CharactersRepository charactersRepository, UsersCharRepository usersCharRepository, InventoryRepository inventoryRepository,  AuthorizationSessionService authSessionService) {
        this.userRepository = userRepository;
        this.charactersRepository = charactersRepository;
        this.usersCharRepository = usersCharRepository;
        this.inventoryRepository = inventoryRepository;
        this.authSessionService = authSessionService;
    }

    @Override
    public boolean createUserChar(Integer charId ,String sid, String charname) {

        CharacterEntity entity = charactersRepository.findNameById(charId);

        if (entity != null ){
            CharacterEntity saveEntity = charactersRepository.findNameById(charId);
            String names = saveEntity.getName();
            Integer hp = saveEntity.getHp();
            Integer dmg = saveEntity.getDmg();

            String login = authSessionService.findLoginBySessionId(sid);
            UserEntity userEntity = userRepository.findByLogin(login);

           UsersCharEntity newCharEntity = usersCharRepository.save(new UsersCharEntity(charname,saveEntity, userEntity, names, hp, dmg ));

           inventoryRepository.save(new InventoryEntity(newCharEntity));

            return true;
        }

        return false;
    }


 }

