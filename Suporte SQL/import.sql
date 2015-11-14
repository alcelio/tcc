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


INSERT INTO `usuario`
(`DTYPE`,`idUsuario`,`ativo`,`dataCadastro`,`dataNascimento`,`email`,
`foneCel`,`login`,`nome`,`senha`,`idSexo`)
VALUES
('Usuario',1,1,'2015/08/26','1980/08/01','doalcelio@gmail.com',
'(51)96511789','vip','USUARIO VIP','vip',1);

INSERT INTO `permissao_usuario` (`idUsuario`, `idPermissao`) 
VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4);

INSERT INTO `disciplina` (`idDisciplina`, `dsDisciplina`)
VALUES 
(1, 'BIOLOGIA'),
(2, 'MATEMÁTICA'),
(3, 'GEOGRAFIA'),
(4, 'FÍSICA'),
(5, 'QUÍMICA'),
(6, 'HISTÓRIA')
;

INSERT INTO `topicoestudo` (`idTopicoEstudo`, `dsTopicoEstudo`, `idDisciplina`) 
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


