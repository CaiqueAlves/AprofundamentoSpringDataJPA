create table tb_cidade(
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtd_habitantes)
    values
    (1, 'São Paulo', 12396372),
    (2, 'Rio de Janeiro', 10000000),
    (3, 'Fortaleza', 800000),
    (4, 'Salvador', 700000),
     (5, 'Belo Horizonte', 60000),
     (6, 'Porto Alegre', 7000000),
     (7, 'Porto Velho', 4948989),
     (8, 'Palmas', 78787900),
     (9, 'Recife', 23234780),
     (11, 'Brasilia', 12123132)
    ;

