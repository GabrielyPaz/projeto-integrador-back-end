--liquibase formatted sql

--changeset 20231207-1 author:gabrielysouza120@gmail.com label:create-schema

create table cidades
(
    id     binary(16) not null,
    estado varchar(255),
    nome   varchar(255),
    primary key (id)
) engine=InnoDB;

create table caracteristicas
(
    icone_url varchar(80),
    id        binary(16) not null,
    nome      varchar(255),
    primary key (id)
) engine=InnoDB;


create table categorias
(
    id           binary(16) not null,
    nome         varchar(100),
    qualificacao varchar(100),
    descricao    varchar(255),
    url_imagem   text,
    primary key (id)
) engine=InnoDB;

create table funcao
(
    id   binary(16) not null,
    nome varchar(255),
    primary key (id),
    constraint uq_funcao_name unique (nome)
) engine=InnoDB;

create table usuario
(
    id        binary(16) not null,
    id_funcao binary(16),
    email     varchar(255),
    nome      varchar(255),
    senha     varchar(255),
    sobrenome varchar(255),
    primary key (id),
    constraint fk_usuario_funcao foreign key (id_funcao) references funcao (id),
    constraint uq_usuario_email unique (email)
) engine=InnoDB;

create table clientes
(
    id        binary(16) not null,
    id_cidade binary(16),
    email     varchar(255),
    nome      varchar(255),
    sobrenome varchar(255),
    primary key (id),
    constraint fk_cliente_cidade foreign key (id_cidade) references cidades (id)
) engine=InnoDB;

create table carros
(
    categoria_id binary(16),
    id           binary(16) not null,
    id_endereco  binary(16),
    descricao    varchar(255),
    foto_carro   enum ('HATCH','IMPORTADO','SEDAN','SUV'),
    modelo       varchar(255),
    primary key (id),
    constraint fk_veiculo_endereco foreign key (id_endereco) references cidades (id),
    constraint fk_categoria_veiculo foreign key (categoria_id) references categorias (id)
) engine=InnoDB;

create table caracteristica_carro
(
    id_caracteristica binary(16) not null,
    id_carro          binary(16) not null,
    primary key (id_caracteristica, id_carro),
    constraint fk_carro_categoria foreign key (id_carro) references carros (id),
    constraint fk_categoria_carro foreign key (id_caracteristica) references caracteristicas (id)
) engine=InnoDB;

create table reserva
(
    data_final   datetime(6),
    data_inicial datetime(6),
    id           binary(16) not null,
    id_carro     binary(16),
    id_reservas  binary(16),
    id_usuario   binary(16),
    primary key (id),
    constraint fk_usuario_reservas foreign key (id_reservas) references usuario (id),
    constraint fk_veiculo_reservas foreign key (id_carro) references carros (id),
    constraint fk_reserva_usuario foreign key (id_usuario) references usuario (id)
) engine=InnoDB;

create table alugueis
(
    cancelado            bit,
    data_aluguel         date,
    alugado_em           datetime(6),
    entregue_em          datetime(6),
    carro_pra_alugar_id  binary(16),
    cidade_de_aluguel_id binary(16),
    cliente_id           binary(16),
    id                   binary(16) not null,
    motivo_cancelamento  varchar(80),
    primary key (id),
    constraint fk_aluguel_carro foreign key (carro_pra_alugar_id) references carros (id),
    constraint fk_cliente_carro_alugado foreign key (cliente_id) references clientes (id),
    constraint fk_cidade_carro_alugado foreign key (cidade_de_aluguel_id) references cidades (id)
) engine=InnoDB;
