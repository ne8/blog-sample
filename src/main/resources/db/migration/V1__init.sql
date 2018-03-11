CREATE SCHEMA IF NOT EXISTS blog;

CREATE TABLE IF NOT EXISTS blog.users (
  id       BIGINT PRIMARY KEY,
  username VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS blog.posts (
  id            BIGINT PRIMARY KEY,
  title         VARCHAR(600) NOT NULL,
  slug          VARCHAR(600) NOT NULL,
  text_content  TEXT         NOT NULL,
  creation_date TIMESTAMP    NOT NULL,
  update_date   TIMESTAMP    NOT NULL,
  user_id       BIGINT       NOT NULL
);

CREATE TABLE IF NOT EXISTS blog.comments (
  id           BIGINT PRIMARY KEY,
  title        VARCHAR(600) NOT NULL,
  text_content TEXT         NOT NULL,
  user_id      BIGINT       NOT NULL,
  --   TODO: not null CONSTRAINT should be added
  post_id      BIGINT
);

CREATE SEQUENCE hibernate_sequence
  START WITH 1
  INCREMENT BY 1;