--
--    Copyright 2009-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

DROP TABLE comment;
DROP TABLE post_tag;
DROP TABLE tag;
DROP TABLE post;
DROP TABLE blog;
DROP TABLE author;
DROP PROCEDURE selectTwoSetsOfAuthors;
DROP PROCEDURE insertAuthor;
DROP PROCEDURE selectAuthorViaOutParams;

CREATE TABLE author (
id                INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 10000),
username          VARCHAR(255) NOT NULL,
password          VARCHAR(255) NOT NULL,
email             VARCHAR(255) NOT NULL,
bio               CLOB,
favourite_section VARCHAR(25),
PRIMARY KEY (id)
);

CREATE TABLE blog (
id          INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
author_id   INT NOT NULL,
title       VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE post (
id          INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
blog_id     INT,
author_id   INT NOT NULL,
created_on  TIMESTAMP NOT NULL,
section     VARCHAR(25) NOT NULL,
subject     VARCHAR(255) NOT NULL,
body        CLOB NOT NULL,
draft       INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (blog_id) REFERENCES blog(id)
);

CREATE TABLE tag (
id          INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
name        VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE post_tag (
post_id     INT NOT NULL,
tag_id      INT NOT NULL,
PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE comment (
id          INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
post_id     INT NOT NULL,
name        LONG VARCHAR NOT NULL,
comment     LONG VARCHAR NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE node (
id  INT NOT NULL,
parent_id INT,
PRIMARY KEY(id)
);

CREATE PROCEDURE selectTwoSetsOfAuthors(DP1 INTEGER, DP2 INTEGER)
PARAMETER STYLE JAVA
LANGUAGE JAVA
READS SQL DATA
DYNAMIC RESULT SETS 2
EXTERNAL NAME 'com.pxs.jcode.ibatis.database.StoredProcedures.selectTwoSetsOfTwoAuthors';

CREATE PROCEDURE insertAuthor(DP1 INTEGER, DP2 VARCHAR(255), DP3 VARCHAR(255), DP4 VARCHAR(255))
PARAMETER STYLE JAVA
LANGUAGE JAVA
EXTERNAL NAME 'com.pxs.jcode.ibatis.database.StoredProcedures.insertAuthor';

CREATE PROCEDURE selectAuthorViaOutParams(ID INTEGER, OUT USERNAME VARCHAR(255), OUT PASSWORD VARCHAR(255), OUT EMAIL VARCHAR(255), OUT BIO VARCHAR(255))
PARAMETER STYLE JAVA
LANGUAGE JAVA
EXTERNAL NAME 'com.pxs.jcode.ibatis.database.StoredProcedures.selectAuthorViaOutParams';
