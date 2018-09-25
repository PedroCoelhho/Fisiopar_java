create table pre_atendimento (
    id_patendimento int not null auto_increment,
    fk_paciente int not null,
    sintomas varchar(255) not null,
    primary key (id_patendimento),
    foreign key (fk_paciente) references paciente(id_paciente)
);

create table atendimento (
    id_atendimento int not null auto_increment,
    tipo_atendimento varchar(45) not null,
    fk_medico int not null,
    bloco_inicial varchar(45) not null,
    bloco_atual varchar(45) not null,
    horario time not null,
    data_at date not null,
    observacoes varchar(255),
    fk_patendimento int not null,
    fk_ubs int not null,
    primary key (id_atendimento),
    foreign key (fk_medico) references medico(id_medico),
    foreign key (fk_patendimento) references pre_atendimento(id_patendimento),
    foreign key (fk_ubs) references ubs(id)
);

create table consulta (
    id_consulta int not null auto_increment,
    fk_atendimento int not null,
    hora time not null,
    datac date not null,
    primary key (id_consulta),
    foreign key (fk_atendimento) references atendimento(id_atendimento)
);

create table agenda (
    id_agenda int not null auto_increment,
    data_ag date not null,
    hora_ag time not null,
    tipo varchar(45) not null,
    fk_atendimento int,
    fk_consulta int,
    detalhes varchar(255),
    primary key(id_agenda),
    foreign key (fk_atendimento) references atendimento(id_atendimento),
    foreign key (fk_consulta) references consulta(id_consulta)
    
);

alter table usuario add nivelAcesso int not null after id;