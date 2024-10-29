--Posts table

-- !Ups
CREATE TABLE Posts (
  "id" INT PRIMARY KEY AUTO_INCREMENT,
  "title" VARCHAR(255) NOT NULL,
  "content" VARCHAR(255) NOT NULL
);

-- !Downs
DROP TABLE Posts;
