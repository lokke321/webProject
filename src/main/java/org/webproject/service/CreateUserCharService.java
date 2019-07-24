package org.webproject.service;

import javafx.geometry.InsetsBuilder;
import org.webproject.dto.User;
import org.webproject.entity.UserEntity;

public interface CreateUserCharService {

    boolean createUserChar (Integer charId ,String sid, String charname);
    boolean deleteUserChar (String charname);


}
