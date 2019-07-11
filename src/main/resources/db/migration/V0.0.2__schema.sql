create table if not exists items (
    id  serial primary key,
    name varchar not null,
    addHp integer,
    addDmg integer,
    resultAction boolean
);

insert into items (name , addHp) values
('Зелье Здоровья', 10);


create table if not exists inventories (
    id serial primary key,
    id_items integer,

  	constraint inventories_items_id_fkey foreign key (id_items) references items (id)
);

create table if not exists users_char (
	id serial primary key,
	charname varchar(100) not null unique,

	char_id integer not null,
	users_id integer not null,
	inventary_id integer not null,
    charclass varchar (100) not null,
	hpchar integer,
	dmgchar integer,

	constraint users_char_inventary_id_fkey foreign key (inventary_id) references inventories (id),
	constraint users_char_charclass_id_fkey foreign key (char_id) references сharacters (id),
	constraint users_char_users_id_fkey foreign key (users_id) references users (id)
);


insert into inventories (id , id_items) values
(1,1);

insert into users_char (charname,charclass, char_id, users_id, hpchar , dmgchar, inventary_id)
values
        ('ТестиК','Гоблин-Шаман',5,3,100,30,1);

