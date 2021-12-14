INSERT INTO `section`(id,name, description) values (1, 'section1', 'description of section')
INSERT INTO `section`(id,name, description) values (2, 'section2', 'description of section')
INSERT INTO `section`(id,name, description) values (3, 'section3', 'description of section')
INSERT INTO `section`(id,name, description) values (4, 'section4', 'description of section')
INSERT INTO `section`(id,name, description) values (5, 'section5', 'description of section')

//passw!1Aa3b
INSERT INTO `user`(id,banned,email,name,password, register_date) values (1,false, 'asd@o2.com', 'ian', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS', '2018-07-14T17:45:55.9483536')
INSERT INTO `user`(id,banned,email,name,password, register_date) values (2, false,'as2dd@o2.com', 'johhn', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS','2018-07-14T17:45:55.9483536')
INSERT INTO `user`(id,banned,email,name,password, register_date) values (3,false, 'a23sd@o2.com', 'george', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS','2015-04-14T17:45:55.9483536')
INSERT INTO `user`(id,banned,email,name,password, register_date) values (4,false, 'ab2sd@o2.com', 'tom', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS', '2018-07-14T17:45:55.9483536')

INSERT INTO thread (id,name,section_id) values (1, 'thread1', 1)
INSERT INTO thread (id,name,section_id) values (2, 'thread2', 2)
INSERT INTO thread (id,name,section_id) values (3, 'thread3', 2)

INSERT INTO post(id,content, thread_id, author_id, creation_date) values(1, 'post1', 1, 1, '2016-07-14T11:40:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(2, 'post2', 1, 2, '2017-01-01T12:32:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(3, 'post3', 1, 3, '2018-05-13T23:42:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(4, 'post4', 1, 1, '2019-07-14T17:11:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(5, 'post5', 2, 1, '2000-11-01T5:45:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(6, 'post6', 2, 2, '2015-01-07T5:05:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(7, 'post7', 3, 3, '2005-01-06T6:25:55.9483536')

INSERT INTO role(id, code) values (1, 'user')
INSERT INTO role(id, code) values (2, 'moderator')
INSERT INTO role(id, code) values (3, 'admin')

insert into user_roles(user_id, role_id) values (1,3)
insert into user_roles(user_id, role_id) values (2,2)
insert into user_roles(user_id, role_id) values (3,1)
insert into user_roles(user_id, role_id) values (4,1)
