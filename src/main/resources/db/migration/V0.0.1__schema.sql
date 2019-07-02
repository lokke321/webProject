
create table if not exists users (
	id serial primary key,
	login varchar(20) not null unique,
	password varchar(100) not null
);


insert into users (login, password)
values
       ('user', 'password'),
       ('admin', 'admin_password');

create table if not exists сharacters (
	id serial primary key,
	name varchar(100) not null unique,
	hp varchar not null,
	dmg varchar  not null
	);

create table if not exists auth_sessions (
  sid varchar(100) primary key,
  user_id integer not null unique,
  expired_date timestamp not null,
  constraint auth_sessions_user_id_fkey foreign key (user_id) references users(id)
);


insert into сharacters (name, hp, dmg) values
('Человек-Воин','120','20'),
('Эльф-Маг','80','35'),
('Дворф-Паладин','120','20'),
('Таурен-Друид','100','30'),
('Гоблин-Шаман','100','30')