CREATE TABLE `assistances` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(300) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

insert into assistances (name, description) values ('Troca de aparelho', 'Troca de aparelho decodificador de sinal');
insert into assistances (name, description) values ('Troca de cabo interno', 'Troca de cabo interno no local da instalação');
insert into assistances (name, description) values ('Troca de fiação interna', 'Troca de fiação interna no local da instalação');
insert into assistances (name, description) values ('Manutenção em fogão', 'Reparo sem necessidade de compra de peças');
insert into assistances (name, description) values ('Manutenção em geladeira', 'Reparo sem necessidade de compra de peças');
insert into assistances (name, description) values ('Manutenção em máquina de lavar', 'Reparo sem necessidade de compra de peças');
