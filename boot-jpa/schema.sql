drop DATABASE if EXISTS micromall;
create DATABASE micromall default character set utf8mb4 default collate utf8mb4_general_ci;

use micromall;
CREATE TABLE dept (
  id INT NOT NULL PRIMARY KEY auto_increment,
  department_name VARCHAR ( 50 ) NOT NULL DEFAULT ''
);

INSERT INTO dept (id, department_name) VALUES (1, '人力资源部');
INSERT INTO dept (id, department_name) VALUES (2, '销售部');
INSERT INTO dept (id, department_name) VALUES (3, '物流部');
INSERT INTO dept (id, department_name) VALUES (4, '市场部');
INSERT INTO dept (id, department_name) VALUES (5, '财务部');
INSERT INTO dept (id, department_name) VALUES (6, '质量保证部');
INSERT INTO dept (id, department_name) VALUES (7, '生产部');
INSERT INTO dept (id, department_name) VALUES (8, '研发部');
INSERT INTO dept (id, department_name) VALUES (9, '信息技术部');
INSERT INTO dept (id, department_name) VALUES (10, '客户服务部');
