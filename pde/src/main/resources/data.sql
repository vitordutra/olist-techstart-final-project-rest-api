-- Tema
INSERT INTO TEMA(codigo, descricao) values (1, 'Java - Spring Cloud Data Flow');
INSERT INTO TEMA(codigo, descricao) values (2, 'Java - Spring Security');
INSERT INTO TEMA(codigo, descricao) values (3, 'Docker');
INSERT INTO TEMA(codigo, descricao) values (4, 'Kubernetes');
INSERT INTO TEMA(codigo, descricao) values (5, 'OPS');

-- Coordenador
INSERT INTO COORDENADOR(matricula, nome, email) values (111, 'Igor Joaquim', 'igor.joaquim@olist.com');
INSERT INTO COORDENADOR(matricula, nome, email) values (222, 'Byanca Ribeiro', 'byanca.ribeiro@olist.com');
INSERT INTO COORDENADOR(matricula, nome, email) values (333, 'Sergio Marchiori', 'sergio.marchiori@olist.com');

-- Time
INSERT INTO TIME(codigo, nome, matricula_coordenador_id) values (1, 'Freights', 1);
INSERT INTO TIME(codigo, nome, matricula_coordenador_id) values (2, 'FBO', 2);
INSERT INTO TIME(codigo, nome, matricula_coordenador_id) values (3, 'SRE', 3);



-- Olister
INSERT INTO OLISTER(matricula, nome, email, codigo_time) values (111, 'Vitor Dutra', 'joao.gois@olist.com', 1);
INSERT INTO OLISTER(matricula, nome, email, codigo_time) values (222, 'Thiago Costa', 'thiago.costa@olist.com', 1);
INSERT INTO OLISTER(matricula, nome, email, codigo_time) values (333, 'Alana Dias', 'alana.dias@olist.com', 2);


-- Times_Temas
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (1 , 1);
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (1 , 2);
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (2 , 1);
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (2 , 2);
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (3, 3);
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (4, 3);
INSERT INTO TIMES_TEMAS(tema_id, time_id) VALUES (5, 3);