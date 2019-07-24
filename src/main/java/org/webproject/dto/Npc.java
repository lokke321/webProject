package org.webproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Npc {

    private Integer id;
    private String name;
    private Integer hp;
    private Integer dmg;
    private String avatarNpcPath;

}
