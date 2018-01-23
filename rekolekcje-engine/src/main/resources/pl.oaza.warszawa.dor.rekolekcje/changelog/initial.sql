--liquibase formatted sql

--changeset qiubix:1516734022510-1
CREATE SEQUENCE authority_seq;

--changeset qiubix:1516734022510-2
CREATE SEQUENCE hibernate_sequence;

--changeset qiubix:1516734022510-3
CREATE SEQUENCE user_seq;

--changeset qiubix:1516734022510-4
CREATE TABLE authority (
  id BIGINT NOT NULL,
  name VARCHAR(50) NOT NULL
);

--changeset qiubix:1516734022510-5
CREATE TABLE participant (
  id BIGINT NOT NULL, address VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255), parish VARCHAR(255), pesel BIGINT NOT NULL);

--changeset qiubix:1516734022510-6
CREATE TABLE user_authority (user_id BIGINT NOT NULL, authority_id BIGINT NOT NULL);

--changeset qiubix:1516734022510-7
CREATE TABLE users (id BIGINT NOT NULL, email VARCHAR(50) NOT NULL, enabled BOOLEAN NOT NULL, firstname VARCHAR(50) NOT NULL, lastpasswordresetdate TIMESTAMP WITHOUT TIME ZONE NOT NULL, lastname VARCHAR(50) NOT NULL, password VARCHAR(100) NOT NULL, username VARCHAR(50) NOT NULL);

--changeset qiubix:1516734022510-8
ALTER TABLE authority ADD CONSTRAINT authority_pkey PRIMARY KEY (id);

--changeset qiubix:1516734022510-9
ALTER TABLE participant ADD CONSTRAINT participant_pkey PRIMARY KEY (id);

--changeset qiubix:1516734022510-10
ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY (id);

--changeset qiubix:1516734022510-11
ALTER TABLE users ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);

--changeset qiubix:1516734022510-12
ALTER TABLE user_authority ADD CONSTRAINT fkgvxjs381k6f48d5d2yi11uh89 FOREIGN KEY (authority_id) REFERENCES authority (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset qiubix:1516734022510-13
ALTER TABLE user_authority ADD CONSTRAINT fkhi46vu7680y1hwvmnnuh4cybx FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
