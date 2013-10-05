CREATE TABLE player
(
  id bigserial NOT NULL,
  first_name character varying(80) NOT NULL,
  last_name character varying(160) NOT NULL,
  nickname character varying(20) NOT NULL,
  coins double precision NOT NULL DEFAULT 0,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE player OWNER TO postgres;