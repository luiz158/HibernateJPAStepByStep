CREATE TABLE student (
  id int IDENTITY NOT NULL PRIMARY KEY,
  passport_id int NULL,
  name varchar(32) NOT NULL,
  email varchar(32) NOT NULL
);

CREATE TABLE passport (
  id int IDENTITY NOT NULL PRIMARY KEY,
  number varchar(32) NOT NULL,
  issued_country varchar(32) NOT NULL
);

alter table student add constraint student_passport_fk foreign key (passport_id) references passport(id);


CREATE TABLE project (
  id int IDENTITY NOT NULL PRIMARY KEY,
  name varchar(32) NOT NULL
);

CREATE TABLE task (
  id int IDENTITY NOT NULL PRIMARY KEY,
  project_id int NOT NULL,
  desc varchar(216) NOT NULL,
  start_date date DEFAULT NULL
);

alter table task add constraint task_project_fk foreign key (project_id) references project(id);

CREATE TABLE student_project (
  id int IDENTITY NOT NULL PRIMARY KEY,
  student_id int NOT NULL,
  project_id int NOT NULL,
  task_id int NOT NULL
);

alter table student_project add constraint student_project_student_fk foreign key (student_id) references student(id);
alter table student_project add constraint student_project_project_fk foreign key (project_id) references project(id);
alter table student_project add constraint student_project_task_fk foreign key (task_id) references task(id);

