DROP TABLE IF EXISTS game_table;

CREATE TABLE game_table
(
  id serial NOT NULL,
  minimum_bet double precision NOT NULL DEFAULT 0,
  maximum_bet double precision NOT NULL DEFAULT 0,
  max_players integer NOT NULL DEFAULT 6,
  "name" character varying(25) NOT NULL,
  CONSTRAINT table_pkey PRIMARY KEY (id),
  CONSTRAINT table_name_unique UNIQUE ("name"),
  CONSTRAINT max_bet_higher_than_min_bet CHECK (maximum_bet > minimum_bet)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE game_table OWNER TO postgres;

INSERT INTO game_table (minimum_bet, maximum_bet, max_players, name)  VALUES (5, 50, 6, 'Frodo');
INSERT INTO game_table (minimum_bet, maximum_bet, max_players, name)  VALUES (5, 50, 6, 'Sam');
INSERT INTO game_table (minimum_bet, maximum_bet, max_players, name)  VALUES (25, 500, 6, 'Gandalf');
INSERT INTO game_table (minimum_bet, maximum_bet, max_players, name)  VALUES (1000, 100000, 6, 'Sauron');