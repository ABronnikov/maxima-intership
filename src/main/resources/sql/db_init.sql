CREATE TABLE users
(
    id          SERIAL PRIMARY KEY,
    login       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    modified_at TIMESTAMP    NOT NULL,
    removed_at  TIMESTAMP    NOT NULL,
    user_role   VARCHAR(255) NOT NULL DEFAULT 'USER_ROLE',
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    patronymic  VARCHAR(255) NOT NULL,
    telegram    VARCHAR(255) NOT NULL,
    email       VARCHAR(255) UNIQUE,
    last_visit  TIMESTAMP    NOT NULL,
    is_removed  BOOLEAN DEFAULT FALSE
);

CREATE TABLE document_templates
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    modified_at TIMESTAMP    NOT NULL,
    removed_at  TIMESTAMP    NOT NULL,
    version     INTEGER      NOT NULL,
    is_removed  BOOLEAN DEFAULT FALSE
);

CREATE TABLE document_fields
(
    id                    SERIAL PRIMARY KEY,
    name                  VARCHAR(255) NOT NULL,
    created_at            TIMESTAMP    NOT NULL,
    modified_at           TIMESTAMP    NOT NULL,
    removed_at            TIMESTAMP    NOT NULL,
    type                  VARCHAR(255) NOT NULL,
    placeholder           VARCHAR(255) NOT NULL,
    default_value         VARCHAR(255) NOT NULL,
    document_templates_id INTEGER REFERENCES document_templates (id),
    is_removed            BOOLEAN DEFAULT FALSE

);

CREATE TABLE credentials
(
    id              SERIAL PRIMARY KEY,
    text            TEXT         NOT NULL,
    version         INTEGER      NOT NULL,
    created_at      TIMESTAMP    NOT NULL,
    modified_at     TIMESTAMP    NOT NULL,
    removed_at      TIMESTAMP    NOT NULL,
    credential_type VARCHAR(255) NOT NULL,
    is_removed      BOOLEAN DEFAULT FALSE
);

CREATE TABLE contractors
(
    id            SERIAL PRIMARY KEY,
    sur_name      VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    modified_at   TIMESTAMP    NOT NULL,
    removed_at    TIMESTAMP    NOT NULL,
    patronymic    VARCHAR(255) NOT NULL,
    phone         VARCHAR(255) NOT NULL,
    telegram      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    credential_id INTEGER REFERENCES credentials (id),
    country       VARCHAR(255) NOT NULL,
    is_removed    BOOLEAN DEFAULT FALSE
);

CREATE TABLE comments
(
    id            SERIAL PRIMARY KEY,
    text          TEXT      NOT NULL,
    created_at    TIMESTAMP NOT NULL,
    modified_at   TIMESTAMP NOT NULL,
    removed_at    TIMESTAMP NOT NULL,
    contractor_id INTEGER REFERENCES contractors (id),
    is_removed    BOOLEAN DEFAULT FALSE
);

CREATE TABLE documents
(
    id                       SERIAL PRIMARY KEY,
    name                     VARCHAR(255) NOT NULL,
    created_at               TIMESTAMP    NOT NULL,
    modified_at              TIMESTAMP    NOT NULL,
    removed_at               TIMESTAMP    NOT NULL,
    internal_registry_number VARCHAR(255) NOT NULL,
    template_id              INTEGER REFERENCES document_templates (id),
    contractor_id            INTEGER REFERENCES contractors (id),
    is_removed               BOOLEAN DEFAULT FALSE
);

CREATE TABLE files
(
    id                 SERIAL PRIMARY KEY,
    path_and_file_name VARCHAR(255) NOT NULL,
    created_at         TIMESTAMP    NOT NULL,
    modified_at        TIMESTAMP    NOT NULL,
    removed_at         TIMESTAMP    NOT NULL,
    document_id        INTEGER REFERENCES documents (id),
    is_removed         BOOLEAN DEFAULT FALSE
);
