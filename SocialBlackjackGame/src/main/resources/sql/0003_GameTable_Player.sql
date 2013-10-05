CREATE TABLE game_table_player
(
  id bigserial NOT NULL,
  game_table_id integer,
  user_id bigint,
  entry_date timestamp with time zone,
  exit_date timestamp with time zone,
  seat integer,
  player_status character(8),
  CONSTRAINT game_table_user_pkey PRIMARY KEY (id ),
  CONSTRAINT game_table_user_game_table_fkey FOREIGN KEY (game_table_id)
      REFERENCES game_table (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT game_table_user_user_fkey FOREIGN KEY (user_id)
      REFERENCES player (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE game_table_player OWNER TO postgres;