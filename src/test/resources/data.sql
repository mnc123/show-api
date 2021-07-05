

drop table if exists butaca_tickets;
drop table if exists tickets;
drop table if exists funciones;
DROP table IF exists butacas;
DROP table IF exists salas;
DROP table IF exists teatros;
DROP table IF exists shows;

CREATE TABLE shows (
    id INT GENERATED ALWAYS AS IDENTITY,
    descripcion varchar(200),
    PRIMARY KEY(id)
);


create table teatros (
    id INT GENERATED ALWAYS AS IDENTITY,
    descripcion varchar(200),
    PRIMARY KEY(id)
);

create table salas (
    id INT GENERATED ALWAYS AS IDENTITY,
    teatro_id INT,
    PRIMARY KEY(id),
    CONSTRAINT fk_sala_teatro
      FOREIGN KEY(teatro_id) REFERENCES teatros(id)
);

create table butacas (
    id INT GENERATED ALWAYS AS IDENTITY,
    sala_id INT,
    PRIMARY KEY(id),
    CONSTRAINT fk_butaca_salas
      FOREIGN KEY(sala_id) REFERENCES salas(id)
);

create table funciones (
    id INT GENERATED ALWAYS AS IDENTITY,
    fecha timestamp,
    show_id int,
    sala_id int,
    PRIMARY KEY(id),
    CONSTRAINT fk_funcion_show
      FOREIGN KEY(show_id) REFERENCES shows(id)
);

create table tickets (
    id INT GENERATED ALWAYS AS IDENTITY,
    nro_ticket varchar(50) UNIQUE,
    dni varchar(50),
    nombre varchar(100),
    funcion_id int,
    butaca_id INT,
    PRIMARY KEY(id),
    CONSTRAINT fk_ticket_butaca
      FOREIGN KEY(butaca_id) REFERENCES butacas(id),
    CONSTRAINT fk_ticket_funcion
      FOREIGN KEY(funcion_id) REFERENCES funciones(id)
);

create table butaca_tickets (
    id INT GENERATED ALWAYS AS IDENTITY,
    butaca_id INT,
    precio numeric,
    seccion varchar(50),
    funcion_id int,
    ticket_id INT,
    PRIMARY KEY(id),
    CONSTRAINT fk_butaca
      FOREIGN KEY(butaca_id) REFERENCES butacas(id),
    CONSTRAINT fk_ticket
      FOREIGN KEY(ticket_id) REFERENCES tickets(id),
    CONSTRAINT fk_funcion
      FOREIGN KEY(funcion_id) REFERENCES funciones(id)
);


insert into shows (descripcion) values ('Superman');
insert into shows (descripcion) values ('Star Wars'); -- 2

insert into teatros (descripcion) values ('Luna Park'); -- 1
insert into teatros (descripcion) values ('Gran Rex');

-- teatro 1
insert into salas (teatro_id) values (1); -- 1
insert into salas (teatro_id) values (1);
insert into salas (teatro_id) values (1); -- 3
insert into salas (teatro_id) values (1); -- 4
insert into salas (teatro_id) values (2); -- 5
insert into salas (teatro_id) values (2); -- 6


-- sala 1
insert into butacas (sala_id) values (1); -- 1
insert into butacas (sala_id) values (1);
insert into butacas (sala_id) values (1);
insert into butacas (sala_id) values (1); -- 4


-- sala 2
insert into butacas (sala_id) values (2); -- 5
insert into butacas (sala_id) values (2);
insert into butacas (sala_id) values (2);
insert into butacas (sala_id) values (2); -- 8


-- teatro 2
insert into butacas (sala_id) values (5); -- 5
insert into butacas (sala_id) values (5);
insert into butacas (sala_id) values (6);
insert into butacas (sala_id) values (6); -- 8


 -- Superman
insert into funciones (fecha, show_id, sala_id) values ('2021-12-18 08:21:36.175627+03', 1, 1); -- 1
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (200, 1, 'A', 1);
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (200, 2, 'A', 1);
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (200, 3, 'A', 1);
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (200, 4, 'A', 1);


-- Star Wars
insert into funciones (fecha, show_id, sala_id) values ('2021-01-19 08:21:36.175627+03', 2, 2); -- 2
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (10, 3, 'A', 2);
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (300, 4, 'A', 2);

-- Star Wars
insert into funciones (fecha, show_id, sala_id) values ('2020-12-20 08:21:36.175627+03', 2, 2); -- 2
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (10, 3, 'A', 2);
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (300, 4, 'A', 2);

insert into funciones (fecha, show_id, sala_id) values ('2020-01-20 08:21:36.175627+03', 1, 5); -- 3
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (10, 5, 'A', 3);
insert into butaca_tickets (precio, butaca_id, seccion, funcion_id) values (300, 6, 'A', 3);