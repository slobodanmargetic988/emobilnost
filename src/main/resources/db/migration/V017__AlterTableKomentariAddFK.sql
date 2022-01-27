

alter table `emobilnost`.`komentari` add column `odgovor_na_id` int default null;


alter table `emobilnost`.`komentari` add foreign key (`odgovor_na_id`) references `komentari`(id);