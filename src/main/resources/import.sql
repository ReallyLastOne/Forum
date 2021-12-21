INSERT INTO `section`(id,name, description) values (1, 'General', 'General issues')
INSERT INTO `section`(id,name, description) values (2, 'Hyde park', 'Anything that does not fit in another sections')
INSERT INTO `section`(id,name, description) values (3, 'section3', 'description of section')
INSERT INTO `section`(id,name, description) values (4, 'section4', 'description of section')
INSERT INTO `section`(id,name, description) values (5, 'section5', 'description of section')

//passw!1Aa3b
INSERT INTO `user`(id,banned,email,name,password, register_date, description, birth_date, sex) values (1,false, 'asd@o2.com', 'ian', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS', '2018-07-14T17:45:55.9483536', 'No description provided.', '1950-01-06', true)
INSERT INTO `user`(id,banned,email,name,password, register_date, description) values (2, false,'as2dd@o2.com', 'johhn', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS','2018-07-14T17:45:55.9483536', 'i like chicken')
INSERT INTO `user`(id,banned,email,name,password, register_date, description) values (3,false, 'a23sd@o2.com', 'george', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS','2015-04-14T17:45:55.9483536', 'big fan of books')
INSERT INTO `user`(id,banned,email,name,password, register_date, description) values (4,true, 'ab2sd@o2.com', 'tom', '$2a$10$4yKaR9AzPspVxt0B5219U.YRjNzDhYGl3CkGRakXmcqHADzkIEEUS', '2018-07-14T17:45:55.9483536', '')

INSERT INTO thread (id,name,section_id) values (1, 'About effectiveness of this', 1)
INSERT INTO thread (id,name,section_id) values (2, 'Why something works like that?', 2)
INSERT INTO thread (id,name,section_id) values (3, 'Disagree with opinion about', 2)
INSERT INTO thread (id,name,section_id) values (4, 'Hello to everyone', 1)

INSERT INTO post(id,content, thread_id, author_id, creation_date) values(1, 'What do you think about something?', 1, 1, '2016-07-14T11:40:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(2, 'I definitely agree', 1, 2, '2017-01-01T12:32:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(3, 'you r wrong lol', 1, 3, '2018-05-13T23:42:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(4, 'noobs', 1, 1, '2019-07-14T17:11:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(5, 'post5', 2, 1, '2000-11-01T5:45:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(6, 'post6', 2, 2, '2015-01-07T5:05:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(7, 'post7', 3, 3, '2005-01-06T6:25:55.9483536')
INSERT INTO post(id,content, thread_id, author_id, creation_date) values(8, 'I want to say hello to everyone', 4, 1, '2025-05-05T6:00:55.9483536')

INSERT INTO role(id, code) values (1, 'user')
INSERT INTO role(id, code) values (2, 'moderator')
INSERT INTO role(id, code) values (3, 'admin')

insert into user_roles(user_id, role_id) values (1,3)
insert into user_roles(user_id, role_id) values (2,2)
insert into user_roles(user_id, role_id) values (3,1)
insert into user_roles(user_id, role_id) values (4,1)
