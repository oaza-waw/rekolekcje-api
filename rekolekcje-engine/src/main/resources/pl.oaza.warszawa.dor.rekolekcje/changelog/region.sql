--liquibase formatted sql

--changeset saphir:region-0
CREATE TABLE region(id BIGINT, name VARCHAR(255));

--changeset saphir:region-1
--commet Change Parish to allow Region assignment
ALTER TABLE region ADD PRIMARY KEY (id);
CREATE SEQUENCE region_seq;
ALTER TABLE region ALTER id SET DEFAULT nextval('region_seq');
SELECT setval('region_seq', (SELECT MAX(id) FROM region));

ALTER TABLE parish ADD COLUMN region BIGINT
  REFERENCES region(id) ON UPDATE CASCADE ON DELETE SET NULL
  DEFAULT NULL;
