CREATE TABLE public.users
(
    id serial NOT NULL,
    name character varying(100) NOT NULL UNIQUE,
    email character varying(50) NOT NULL UNIQUE,
    password character varying(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX users_name_index
on public.users (name);
CREATE UNIQUE INDEX users_email_index
on public.users (email);