insert into cuenta values(1, 'pepito@email.com', '123');
insert into cuenta values(2, 'juanita@email.com', '234');
insert into cuenta values(3, 'jorge@email.com', '321');
insert into cuenta values(4, 'maria@email.com', '111');
insert into cuenta values(5, 'gildardo@email.com', '222');

insert into cuenta values(6, 'zayra@email.com', '$2a$10$rSeFIv3pX9R7gbTkkAuqSueKDiHAGIspbwcjEVFtzA2zePE6lmKJq');
insert into cuenta values(7, 'esteban@email.com', '222');
insert into cuenta values(8, 'julian@email.com', '222');
insert into cuenta values(9, 'luis@email.com', '222');
insert into cuenta values(10, 'andres@email.com', '222');

insert into cuenta values(11, 'hidalgo@email.com', '222');
insert into cuenta values(12, 'henao@email.com', '222');
insert into cuenta values(13, 'parra@email.com', '222');
insert into cuenta values(14, 'arias@email.com', '222');
insert into cuenta values(15, 'arcila@email.com', '222');

insert into paciente values('24567234', 0, 'ACTIVO', 'Pepito Perez', '5454545', 'url_foto', 'Sin alergias', 1, '1990-01-01', 1, 1);
insert into paciente values('18635123', 0, 'ACTIVO', 'Juanita Lopez', '4564545', 'url_foto', 'Sin
alergias', 0, '1995-11-28', 1, 2);
insert into paciente values('78689906', 1, 'ACTIVO', 'Jorge Valencia', '786989', 'url_foto', 'Sin
alergias', 2, '2000-11-28', 1, 3);
insert into paciente values('798790', 2, 'ACTIVO', 'Maria Adonil', '87676896', 'url_foto', 'Sin
alergias', 3, '1964-11-28', 0, 4);
insert into paciente values('7890798', 3, 'ACTIVO', 'Jose Mazo', '8709789', 'url_foto', 'Sin
alergias', 4, '1953-11-28', 0, 5);

insert into medico values('68907879', 0, 'ACTIVO', 'Zayra Parra', '6989678', 'url_foto', 0, 6);
insert into medico values('6438756', 0, 'ACTIVO', 'Esteban Garía', '78987', 'url_foto', 1,  7);
insert into medico values('839580', 1, 'ACTIVO', 'Julian Mazo', '456578', 'url_foto', 2,  8);
insert into medico values('32543567', 2, 'ACTIVO', 'Luis Angel', '798790', 'url_foto', 3, 9);
insert into medico values('97980789', 3, 'ACTIVO', 'Cristian Arcila', '567569', 'url_foto', 4, 10);

insert into administrador values(11);
insert into administrador values(12);
insert into administrador values(13);
insert into administrador values(14);
insert into administrador values(15);

insert into cita values(1,0,'2023-11-01 14:30:00', NOW(), 'motivo',null, 6,1);
insert into cita values(2,0,'2023-11-02 14:30:00', NOW(), 'motivo',null, 6,2);
insert into cita values(3,0,'2023-11-03 14:30:00', NOW(), 'motivo',null, 8,3);
insert into cita values(4,0,'2023-11-04 14:30:00', NOW(), 'motivo',null, 9,4);
insert into cita values(5,0,'2023-11-05 14:30:00', NOW(), 'motivo',null, 10,5);
/*siguiendo la lógica primero se crea una cita y después se le da atención, por tanto puedo crear esta cita y no
  necesariamente debo crear otro registro para atención, esta será la cita para el test crearAtencion,
  es decir, esta es la cita pendiente por atender*/
insert into cita values(6,0,'2023-11-05 14:30:00', NOW(), 'motivo',null, 10,5);
/*una atención obligatoriamente debe estar associada a una cita*/
insert into atencion values(1,'diagnostico', 'notas medicas', 'tratamiento',1);
insert into atencion values(2,'diagnostico', 'notas medicas', 'tratamiento',2);
insert into atencion values(3,'diagnostico', 'notas medicas', 'tratamiento',3);
insert into atencion values(4,'diagnostico', 'notas medicas', 'tratamiento',4);
insert into atencion values(5,'diagnostico', 'notas medicas', 'tratamiento',5);

insert into dia_libre values(1,'2023-11-06', 6);
insert into dia_libre values(2,'2023-11-07',7);
insert into dia_libre values(3,'2023-11-08',8);
insert into dia_libre values(4,'2023-11-09',9);
insert into dia_libre values(5,'2023-11-10',10);

insert into horario_medico values(1,'LUNES','14:00:00', '07:00:00', 6);
insert into horario_medico values(2,'MARTES','14:00:00', '07:00:00', 7);
insert into horario_medico values(3,'MIERCOLES','14:00:00', '07:00:00', 8);
insert into horario_medico values(4,'JUEVES','14:00:00', '07:00:00', 9);
insert into horario_medico values(5,'VIERNES','14:00:00', '07:00:00', 10);

insert into pqrs values(1,0,'2023-10-15 14:30:00', '', '',1);
insert into pqrs values(2,0,'2023-10-15 14:30:00', '', '',2);
insert into pqrs values(3,0,'2023-10-15 14:30:00', '', '',3);
insert into pqrs values(4,0,'2023-10-15 14:30:00', '', '',4);
insert into pqrs values(5,0,'2023-10-15 14:30:00', '', '',5);
/*siguiendo la lógica el primer msj no tieen que tener un msj asociado, a partir del primer msj todas son respuestas*/
insert into mensaje values(1,'Hola','2023-10-15 14:30:00', '', 1,null,1);
insert into mensaje values(2,'como estás','2023-10-15 14:30:00', '', 2,1,1);
insert into mensaje values(3,'contenido','2023-10-15 14:30:00', '', 3,2,1);
insert into mensaje values(4,'contenido','2023-10-15 14:30:00', '', 4,3,2);
insert into mensaje values(5,'Hola, soy un paciente','2023-10-15 14:30:00', '', 5,4,2);