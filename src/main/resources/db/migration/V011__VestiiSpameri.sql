create table `emobilnost`.`spameri` (
`id` int NOT NULL AUTO_INCREMENT,
`ipadresa` varchar(255),
`brojac` int,
PRIMARY KEY (id)
)ENGINE=InnoDB;




create table `emobilnost`.`slika` (
`id` int NOT NULL AUTO_INCREMENT,
`title` varchar(255),
`file_name` varchar(255),
`active` BOOLEAN default true,
`galerija` BOOLEAN default false,
`alt_text` varchar(255) default "",
primary key (`id`)
)ENGINE=InnoDB;

create table `emobilnost`.`video` (
`id` int NOT NULL AUTO_INCREMENT,
`title` varchar(255),
`file_name` varchar(255),
`active` BOOLEAN default true,
`galerija` BOOLEAN default false,

primary key (`id`)
)ENGINE=InnoDB;

create table `emobilnost`.`vesti` (
`id` int NOT NULL AUTO_INCREMENT,
tekst text  NOT NULL,
datum datetime NOT NULL,
naslov varchar(255),
`slika` int default null,

PRIMARY KEY (id),
foreign key (`slika`) references `slika`(`id`)
)ENGINE=InnoDB;