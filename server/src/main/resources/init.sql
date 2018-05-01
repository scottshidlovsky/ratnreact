DROP TABLE IF EXISTS todo;
CREATE TABLE todo (
  `id` bigint auto_increment primary key,
  `title` varchar(256),
  `completed` bool default false,
  `order` int default null
);
INSERT INTO todo (title, completed) VALUES (
'My Title', false
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  `id` bigint auto_increment primary key,
  `username` varchar(60) unique,
  `password` char(60),
);
INSERT INTO user (username, password) VALUES (
'test', '$2a$10$vrO7PaWr8gGch6XHRvhXN.0j2VkJUF3981Ra4JPAEEOqkytnBTYuO'
);