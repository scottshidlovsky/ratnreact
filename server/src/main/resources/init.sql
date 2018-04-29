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