--liquibase formatted sql

--changeset saphir:region-0
CREATE TABLE region(id BIGINT, name VARCHAR(255));
