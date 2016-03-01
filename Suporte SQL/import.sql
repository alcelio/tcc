drop database tcc_banco;

create database tcc_banco;


INSERT INTO `Sexo` (`IdSexo`,`dsSexo`) VALUES 
 (1,'MASCULINO'),
 (2,'FEMININO');



 INSERT INTO `Serie`(`idSerie`,`dsSerie`) VALUES
(1,'1° ANO'),
(2,'2° ANO'),
(3,'3° ANO'),
(4,'4° ANO'),
(5,'5° ANO'),
(6,'6° ANO'),
(7,'7° ANO'),
(8,'8° ANO'),
(9,'9° ANO');

INSERT INTO `Turno` (`idTurno`,`dsTurno`) VALUES
(1,'DIURNO MANHÃ'),
(2,'DIURNO TARDE'),
(3,'VESPERTINO'),
(4,'NOTURNO'),
(5,'SÁBADO'),
(6,'SEMANAL'),
(7,'QUINZENAL'),
(8,'MENSAL'),
(9,'INTEGRAL'),
(10,'INDEFINIDO');


INSERT INTO `StatusAvaliacao` (`idStatusAvaliacao`,`dsStatusAvaliacao`) VALUES
(1,'CORRIGIDA'),
(2,'PENDENTE_CORRECAO'),
(3,'AGUARDANDO_INICIO');

INSERT INTO `Permissao`(`idPermissao`,`dsDescricao`,`dsPermissao`) VALUES
(1,'Administrador','ROLE_ADMIN'),
(2,'Professor','ROLE_PROFESSOR'),
(3,'Aluno','ROLE_ALUNO'),
(4,'Usuario','ROLE_USUARIO'); 


INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Usuario', 1, true, '2015-08-26', '1980-08-01', 'doalcelio@gmail.com', '(51)96511789', 'vip', 'USUARIO VIP', null, 'vip', null, null, null, null, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 9, true, '2015-11-14', '1980-08-01', 'doalcelio@gmail.com', '(51) 9651-1789', 'aluno', 'ALUNO TESTE UM', null, 'aluno', null, null, null, 10, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 11, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com', '(51) 5151-5151', 'aluno1', 'ALUNO TESTE UM', null, 'aluno', null, null, null, 12, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 13, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com.br', '(43) 3434-3343', 'dsds', 'ALCELIO GOMES', null, 'ddd', null, null, null, 14, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 15, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com', '(54) 5118-6141', 'aluno2', 'ALUNO DO TESTEIS', null, 'aluno2', null, null, null, 16, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 17, true, '2015-11-14', '2015-11-14', 'arlene.callai@gmail.com', '(89) 0904-0209', 'aluno03', 'ALUNO TRES TESTE', null, 'aluno3', null, null, null, 18, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 19, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com.br', '(45) 6565-6565', 'aluno15', 'ALUNO QUINZE', null, 'aluno15', null, null, null, 20, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 21, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com', '(54) 5645-6465', 'aluno4', 'ALUNO DO TESTEIS', null, 'aluno4', null, null, null, 22, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 23, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com', '(56) 5656-5656', 'aluno5', 'ALCELIO GOMES', null, 'aluno5', null, null, null, 24, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 25, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com', '(45) 4654-5646', 'aluno8', 'DSDS', null, 'aluno8', null, null, null, 26, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 27, true, '2015-11-14', '2015-11-14', 'arlene.calai@gmail.com', '(45) 6564-6556', 'aluno9', 'ALCELIO GOMES', null, 'aluno9', null, null, null, 28, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 29, true, '2015-11-14', '2000-08-01', 'andresilva@gmail.com', '(34) 2333-3333', 'andre silva', 'ANDRE SILVA', null, 'silvaandre', null, null, null, 30, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 31, true, '2015-11-14', '2015-11-14', 'emailteste@teste.com', '(58) 1135-9132', 'aluno11', 'ALUNO ONZE', null, 'aluno11', null, null, null, 32, 1, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 33, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com.br', '(45) 5456-4654', 'aluno25', 'ALUNO DO TESTEIS', null, 'aluno25', null, null, null, 34, 2, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 35, true, '2015-11-14', '2015-11-14', 'alcelio@gmail.com.br', '(45) 4444-4444', 'aluno19', 'ALUNO TESTE', null, 'aluno19', null, null, null, 36, 2, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 37, true, '2015-11-14', '2015-11-14', 'doalcelio@gmail.com', '(45) 4546-5465', 'al45', 'SASASASASA SASASA', null, 'al45', null, null, null, 38, 2, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Professor', 39, true, '2015-11-14', '2015-11-14', 'emaildoprofessor@prof.com.br', '(45) 4565-6465', 'professor1', 'PROFESOR TESTE UM', null, 'p1', null, null, null, 40, 2, null, false);
INSERT INTO USUARIO (DTYPE, idUsuario, ativo, dataCadastro, dataNascimento, email, foneCel, login, nome, permissao, senha, titulacao, foneResponsavel, nomeResponsavel, idEndereco, idSexo, idAvaliacao, primeiroAcesso) VALUES ('Aluno', 41, true, '2015-12-09', '2015-12-09', 'doalcelio@gmail.com', '(77) 7777-7777', 'TESTE', 'ALUNO DO TESTEIS', null, 'teste', null, null, null, 42, 1, null, false);


INSERT INTO `permissao_usuario` (`idUsuario`, `idPermissao`) 
VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4);

INSERT INTO `Disciplina` (`idDisciplina`, `dsDisciplina`)
VALUES 
(1, 'BIOLOGIA'),
(2, 'MATEMÁTICA'),
(3, 'GEOGRAFIA'),
(4, 'FÍSICA'),
(5, 'QUÍMICA'),
(6, 'HISTÓRIA')
;

INSERT INTO `TopicoEstudo` (`idTopicoEstudo`, `dsTopicoEstudo`, `idDisciplina`) 
VALUES 
(1, 'REINO MONERA', 1),
(2, 'MAMÍFEROS', 1),
(3, 'FUNGOS', 1),
(4, 'BACTÉRIAS', 1),
(5, 'PLANTAS', 1),
(6, 'GEOMETRIA', 2),
(7, 'RELEVO', 2),
(8, 'MOVIMENTOS', 4),
(9, 'BASES E ÁCIDOS', 5),
(10, 'PRÉ-HISTÓRIA', 6)

;






SELECT senha FROM Usuario WHERE login = '?';


SELECT Permissao.dsPermissao, 'Roles' FROM Usuario, permissao_usuario, Permissao
WHERE Usuario.idUsuario = permissao_usuario.idUsuario
AND Permissao.idPermissao =permissao_usuario.idPermissao and login = '?';


