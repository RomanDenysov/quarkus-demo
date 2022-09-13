ALTER TABLE IF EXISTS users
    DROP CONSTRAINT IF EXISTS users_contact_info_fk;

DROP TABLE IF EXISTS contact_info CASCADE;

ALTER TABLE IF EXISTS users
    ADD CONSTRAINT users_contact_info_uq UNIQUE (contact_info_id);

ALTER TABLE users RENAME COLUMN contact_info_id to contact_id;