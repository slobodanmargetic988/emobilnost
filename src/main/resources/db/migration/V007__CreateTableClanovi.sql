create table `emobilnost`.`clanovi` (
`id` int NOT NULL AUTO_INCREMENT,
`ime` varchar(255),
`prezime` varchar(255),
`email` varchar(255),
`adresa` varchar(255),
`mesto` varchar(255),
`postanski_broj` varchar(255),
`drzava` varchar(255),
`broj_telefona` varchar(255),
`jmbg` varchar(255),
`naziv_pravne_osobe` varchar(255),
`pib` int(255),
`is_pravno_lice` boolean default true,
`datum_pocetka_clanstva` date,
`datum_isteka_clanstva` date,

PRIMARY KEY (id)
)ENGINE=InnoDB;