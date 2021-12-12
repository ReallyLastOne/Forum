INSERT INTO `section`(id,name) values (1, 'section1')
INSERT INTO `section`(id,name) values (2, 'section2')
INSERT INTO `section`(id,name) values (3, 'section3')
INSERT INTO `section`(id,name) values (4, 'section4')
INSERT INTO `section`(id,name) values (5, 'section5')

//passw!1Aa3b
INSERT INTO `user`(id,banned,email,name,password, register_date) values (1,false, 'asd@o2.com', 'ian', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS', '2018-07-14T17:45:55.9483536')
INSERT INTO `user`(id,banned,email,name,password, register_date) values (2, false,'as2dd@o2.com', 'johhn', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS','2018-07-14T17:45:55.9483536')
INSERT INTO `user`(id,banned,email,name,password, register_date) values (3,false, 'a23sd@o2.com', 'george', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS','2015-04-14T17:45:55.9483536')
INSERT INTO `user`(id,banned,email,name,password, register_date) values (4,false, 'ab2sd@o2.com', 'tom', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS', '2018-07-14T17:45:55.9483536')

INSERT INTO thread (id,name,section_id) values (1, 'thread1', 1)
INSERT INTO thread (id,name,section_id) values (2, 'thread2', 2)
INSERT INTO thread (id,name,section_id) values (3, 'thread3', 2)
INSERT INTO thread (id,name,section_id) values (4, 'thread4', 1)
INSERT INTO thread (id,name,section_id) values (5, 'thread5', 1)
INSERT INTO thread (id,name,section_id) values (6, 'thread6', 2)
INSERT INTO thread (id,name,section_id) values (7, 'thread7', 3)

INSERT INTO post(id,content, thread_id, author_id) values(1, 'post1', 1, 1)
INSERT INTO post(id,content, thread_id, author_id) values(2, 'post2', 1, 2)
INSERT INTO post(id,content, thread_id, author_id) values(3, 'post3', 1, 3)
INSERT INTO post(id,content, thread_id, author_id) values(4, 'post4', 1, 1)
INSERT INTO post(id,content, thread_id, author_id) values(5, 'post5', 2, 1)

INSERT INTO role(id, code) values (1, 'user')
INSERT INTO role(id, code) values (2, 'moderator')
INSERT INTO role(id, code) values (3, 'admin')

insert into user_roles(user_id, role_id) values (1,3)
insert into user_roles(user_id, role_id) values (2,2)
insert into user_roles(user_id, role_id) values (3,1)
insert into user_roles(user_id, role_id) values (4,1)
