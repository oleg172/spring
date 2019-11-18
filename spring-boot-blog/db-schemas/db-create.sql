CREATE TABLE public.users
(
    id serial NOT NULL,
    name character varying(100) NOT NULL UNIQUE,
    email character varying(100) NOT NULL UNIQUE,
    password character varying(100),
    provider character varying(50) NOT NULL,
    provider_id character varying(50),
    PRIMARY KEY (id),
    CONSTRAINT valid_password CHECK (((char_length(password) > 0 ) AND (provider = 'local')) OR ((char_length(password) >= 0 ) AND (provider != 'local')))
);
CREATE UNIQUE INDEX users_name_index
    on public.users (name);
CREATE UNIQUE INDEX users_email_index
    on public.users (email);