create table `emobilnost`.`komentari` (
`id` int NOT NULL AUTO_INCREMENT,
`vest_id` int,
`ime` varchar(255),
`tekst` varchar(1255),
`aktivan` BOOLEAN default true,
`datum` datetime NOT NULL,
primary key (`id`)
)ENGINE=InnoDB;