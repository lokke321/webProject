package org.webproject.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_char")
public class UsersCharEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String charname;

    @OneToOne
    @JoinColumn(name = "char_id")
    private CharacterEntity character;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    private String charclass;
    private Integer hpchar;
    private Integer dmgchar;


    public UsersCharEntity (String charname, CharacterEntity character, UserEntity user,  String charclass, Integer hpchar, Integer dmgchar){
        this.charname = charname;
        this.character = character;
        this.user = user;
        this.charclass = charclass;
        this.hpchar = hpchar;
        this.dmgchar = dmgchar;

    }

}
