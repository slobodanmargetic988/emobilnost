alter table `emobilnost`.`users` add column `marka` varchar(255) default null;
alter table `emobilnost`.`users`  add column `registracija` varchar(255) default null;
alter table `emobilnost`.`users`  add column `imaev` BOOLEAN default false;
alter table `emobilnost`.`clanovi` add column `marka` varchar(255) default null;
alter table `emobilnost`.`clanovi`  add column `registracija` varchar(255) default null;
alter table `emobilnost`.`clanovi`  add column `imaev` BOOLEAN default false;