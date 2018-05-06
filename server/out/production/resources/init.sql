

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  `user_id` bigint auto_increment primary key,
  `username` varchar(60) unique,
  `password` char(60),
);
INSERT INTO user (username, password) VALUES (
'test', '$2a$10$vrO7PaWr8gGch6XHRvhXN.0j2VkJUF3981Ra4JPAEEOqkytnBTYuO'
);

DROP TABLE IF EXISTS todo;
CREATE TABLE todo (
  `todo_id` int auto_increment primary key,
  `title` varchar(256),
  `completed` bool default false,
  `order` int default null,
  `user_id` bigint NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

INSERT INTO todo (title, completed, `order`, user_id) VALUES (
'Create some todos', false, 1, 1
);