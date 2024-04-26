create table enderecos(
    id bigint not null auto_increment,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)
);

create table clientes(
    id bigint not null auto_increment,
    endereco_id bigint,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    tipo varchar(6) not null unique,

    primary key(id),
    constraint fk_endereco_id foreign key (endereco_id) references enderecos(id)
);

create table produtos(
    id bigint not null auto_increment,
    descricao varchar(100) not null,
    preco_unitario numeric(20,2),
    tipo varchar(6) not null unique,

    primary key(id)
);

create table pedidos(
    id bigint not null auto_increment,
    cliente_id bigint,
    data_pedido datetime not null,
    total numeric(20,2),

    primary key(id),
    constraint fk_cliente_id foreign key (cliente_id) references clientes(id)
);

create table produtos_pedido(
    id bigint not null auto_increment,
    pedido_id bigint,
    produto_id bigint,
    quantidade bigint,

    primary key(id),
    constraint fk_pedido_id foreign key (pedido_id) references pedidos(id),
    constraint fk_produto_id foreign key (produto_id) references produtos(id)
);