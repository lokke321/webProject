package org.webproject.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "сharacters")

public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer hp;
    private Integer dmg;

    public CharacterEntity (Integer id) {
        this.id = id;
    }

    public CharacterEntity (Integer id, String name, Integer hp, Integer dmg){
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;

    }

    public CharacterEntity(){}
}
