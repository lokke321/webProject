package org.webproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webproject.entity.*;
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
    private final ItemRepository itemRepository;

    @Autowired
    public CreateUserCharServiceImpl(UserRepository userRepository, CharactersRepository charactersRepository, UsersCharRepository usersCharRepository, InventoryRepository inventoryRepository, AuthorizationSessionService authSessionService, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.charactersRepository = charactersRepository;
        this.usersCharRepository = usersCharRepository;
        this.inventoryRepository = inventoryRepository;
        this.authSessionService = authSessionService;
        this.itemRepository = itemRepository;
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



    @Override
    public boolean deleteUserChar(String charname) {

       UsersCharEntity usersCharEntity = usersCharRepository.findByCharname(charname);

       InventoryEntity inventoryEntity = inventoryRepository.findByCharId(usersCharEntity);

       //TODO: Добавить удаление item с текущего inventary

       inventoryRepository.delete(inventoryEntity);
       usersCharRepository.delete(usersCharEntity);

       return true;
    }


}

