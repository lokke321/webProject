create table if not exists npc (
    id  serial primary key,
    name varchar not null,
    hp integer,
    dmg integer,
    avatar_npc_path text
);

create table if not exists message_npc (
id serial primary key,
npc_id integer,
message text,

constraint message_npc_npc_id_fkey foreign key (npc_id) references npc (id)
);

insert into npc (name,avatar_npc_path) values
('Незнакомая Эльфийка','/attachment/avatars/elf_start_game.jpg');

insert into message_npc (npc_id,message) values
('1','Приветствую тебя путник. Это самое начало твоего пути, ты готов к приключениям?');

