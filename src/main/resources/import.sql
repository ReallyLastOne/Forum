INSERT INTO `section`(id,name) values (1, 'section1')
INSERT INTO `section`(id,name) values (2, 'section2')
INSERT INTO `section`(id,name) values (3, 'section3')
INSERT INTO `section`(id,name) values (4, 'section4')
INSERT INTO `section`(id,name) values (5, 'section5')

INSERT INTO subsection(id,name) values (1, 'subsection1')
INSERT INTO subsection(id,name) values (2, 'subsection2')
INSERT INTO subsection(id,name) values (3, 'subsection3')
INSERT INTO subsection(id,name) values (4, 'subsection4')
INSERT INTO subsection(id,name) values (5, 'subsection5')
INSERT INTO subsection(id,name) values (6, 'subsection6')
INSERT INTO subsection(id,name) values (7, 'subsection7')
INSERT INTO subsection(id,name) values (8, 'subsection8')
INSERT INTO subsection(id,name) values (9, 'subsection9')
INSERT INTO subsection(id,name) values (10, 'subsection10')


INSERT INTO section_subsections(section_id,subsections_id) values (1, 1)
INSERT INTO section_subsections(section_id,subsections_id) values (1, 10)
INSERT INTO section_subsections(section_id,subsections_id) values (1, 5)

INSERT INTO section_subsections(section_id,subsections_id) values (2, 2)
INSERT INTO section_subsections(section_id,subsections_id) values (2, 3)

INSERT INTO section_subsections(section_id,subsections_id) values (5, 4)
INSERT INTO section_subsections(section_id,subsections_id) values (5, 8)

INSERT INTO section_subsections(section_id,subsections_id) values (3, 6)

INSERT INTO section_subsections(section_id,subsections_id) values (4, 7)
INSERT INTO section_subsections(section_id,subsections_id) values (4, 9)


INSERT INTO `user`(id,email,name,password) values (1, 'asd@o2.com', 'ian', 'passw!1Aa3b')
INSERT INTO `user`(id,email,name,password) values (2, 'asdd@o2.com', 'johhn', 'pasS2sw!1Aa3b')
INSERT INTO `user`(id,email,name,password) values (3, 'assd@o2.com', 'george', 'pAassw!1Aa3b')
INSERT INTO `user`(id,email,name,password) values (4, 'absd@o2.com', 'tom', 'Passw!1SAa3b')

INSERT INTO thread (id,name) values (1, 'thread1')
INSERT INTO thread (id,name) values (2, 'thread2')
INSERT INTO thread (id,name) values (3, 'thread3')
INSERT INTO thread (id,name) values (4, 'thread4')
INSERT INTO thread (id,name) values (5, 'thread5')

INSERT INTO subsection_threads(subsection_id, threads_id) values (1, 1)
INSERT INTO subsection_threads(subsection_id, threads_id) values (1, 3)
INSERT INTO subsection_threads(subsection_id, threads_id) values (1, 2)
INSERT INTO subsection_threads(subsection_id, threads_id) values (1, 4)
INSERT INTO subsection_threads(subsection_id, threads_id) values (1, 5)

INSERT INTO post(id,content) values(1, 'post1')
INSERT INTO post(id,content) values(2, 'post2')
INSERT INTO post(id,content) values(3, 'post3')
INSERT INTO post(id,content) values(4, 'post4')
INSERT INTO post(id,content) values(5, 'post5')

insert into USER_POSTS(user_id,posts_id) values (1, 1)
insert into USER_POSTS(user_id,posts_id) values (1, 2)
insert into USER_POSTS(user_id,posts_id) values (1, 3)
insert into USER_POSTS(user_id,posts_id) values (1, 4)
insert into USER_POSTS(user_id,posts_id) values (1, 5)

insert into THREAD_POSTS (thread_id, posts_id) values(1, 1)
insert into THREAD_POSTS (thread_id, posts_id) values(1, 2)
insert into THREAD_POSTS (thread_id, posts_id) values(1, 3)
insert into THREAD_POSTS (thread_id, posts_id) values(1, 4)
insert into THREAD_POSTS (thread_id, posts_id) values(1, 5)