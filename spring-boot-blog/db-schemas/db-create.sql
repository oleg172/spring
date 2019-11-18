CREATE TABLE public.users
(
    id bigserial NOT NULL,
    name character varying(100) NOT NULL UNIQUE,
    email character varying(100) NOT NULL UNIQUE,
    password character varying(100),
    provider character varying(50) NOT NULL,
    created DATE,
    updated DATE,
    PRIMARY KEY (id),
    CONSTRAINT valid_password CHECK (((char_length(password) > 0 ) AND (provider = 'local')) OR ((char_length(password) >= 0 ) AND (provider != 'local')))
);
CREATE UNIQUE INDEX users_name_index
    on public.users (name);
CREATE UNIQUE INDEX users_email_index
    on public.users (email);

CREATE TABLE public.roles
(
    id bigserial NOT NULL,
    name character varying(100) NOT NULL UNIQUE,
    created DATE,
    updated DATE,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    user_id bigint REFERENCES users (id) ON UPDATE CASCADE,
    role_id bigint REFERENCES roles (id) ON UPDATE CASCADE,
    PRIMARY KEY (user_id, role_id)
);