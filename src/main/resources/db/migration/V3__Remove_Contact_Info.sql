ALTER TABLE IF EXISTS users
    DROP CONSTRAINT IF EXISTS users_contact_info_fk;

DROP TABLE IF EXISTS contact_info CASCADE;

ALTER TABLE IF EXISTS users
    ALTER COLUMN contact_info_id
        SET NOT NULL;

ALTER TABLE IF EXISTS users
    ADD CONSTRAINT users_contact_info_uq UNIQUE (contact_info_id);