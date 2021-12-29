create table `emobilnost`.`komentari` (
`id` int NOT NULL AUTO_INCREMENT,
tekst text  NOT NULL,
datum datetime NOT NULL,
naslov varchar(255),
`slika` int default null,

PRIMARY KEY (id),
foreign key (`slika`) references `slika`(`id`)
)ENGINE=InnoDB;