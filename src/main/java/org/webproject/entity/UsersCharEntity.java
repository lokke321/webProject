package org.webproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne
    @JoinColumn(name = "inventary_id")
    private InventoryEntity inventory;

    private String charclass;
    private Integer hpchar;
    private Integer dmgchar;

}
