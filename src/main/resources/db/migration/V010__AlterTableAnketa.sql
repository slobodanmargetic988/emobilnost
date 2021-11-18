

alter table `emobilnost`.`anketa` add column `clan_id` int default null;


alter table `emobilnost`.`anketa` add foreign key (`clan_id`) references `clanovi`(id);