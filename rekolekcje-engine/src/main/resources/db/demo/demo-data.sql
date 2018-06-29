--liquibase formatted sql

--changeset author:qiubix
INSERT INTO parish (id, name, address)
    VALUES (1, 'Parafia Jakuba', 'Pl. Narutowicza 1');
INSERT INTO parish (id, name, address)
    VALUES (2, 'Parafia Zbawiciela', 'Pl. Zbawiciela 1');
INSERT INTO parish (id, name, address)
    VALUES (3, 'Św. Anna', 'Krakowskie Przedmieście 2');
INSERT INTO participant( id, first_name, last_name, pesel, parish_id, father_name, mother_name, christening_place, christening_date, emergency_contact_name, emergency_contact_number, street, street_number, flat_number, postal_code, city, current_treatment, medications, allergies, other)
    VALUES (1, 'Jason', 'Tatum', '5413415', 1, 'Joe', 'Jill', 'Boston', '2017-07-31 22:00:00.000000', 'Isaiah Thomas', 41451551, 'TD Garden', 23, 4, 'USA', 'Boston', 'None', 'Rutinoscorbin', 'Peanuts', 'other medical data');
INSERT INTO participant (id, first_name, last_name, pesel, parish_id)
    VALUES (2, 'Jaylen', 'Brown', '51341451', 2);
