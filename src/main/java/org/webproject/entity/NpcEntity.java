package org.webproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "npc")
@NoArgsConstructor
public class NpcEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer hp;
    private Integer dmg;

    @Column(name = "avatar_npc_path")
    private String avatarNpcPath;


}
