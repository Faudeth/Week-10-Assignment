create database if not exists video_games_db;

use video_games_db;

drop table if exists consoles;
drop table if exists games;

create table consoles (
	console_id int (10) not null auto_increment,
    console_name varchar (50) not null,
    primary key (console_id)
);

create table games (
	game_id int (50) not null auto_increment,
    title varchar (50) not null,
    console_id int (10) not null,
    primary key (game_id),
    foreign key (console_id) references consoles (console_id)
);