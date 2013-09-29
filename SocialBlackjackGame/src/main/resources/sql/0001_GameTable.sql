CREATE TABLE game_table
(
  id serial NOT NULL,
  minimum_bet double precision NOT NULL DEFAULT 0,
  maximum_bet double precision NOT NULL DEFAULT 0,
  max_players integer NOT NULL DEFAULT 6,
  CONSTRAINT table_pkey PRIMARY KEY (id),
  CONSTRAINT max_bet_higher_than_min_bet CHECK (maximum_bet > minimum_bet)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE game_table OWNER TO postgres;
