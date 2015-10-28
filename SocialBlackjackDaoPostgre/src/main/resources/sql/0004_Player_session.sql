CREATE TABLE player_session
(
   id bigserial, 
   player_id bigint, 
   token character(32), 
   login_date timestamp without time zone, 
   logout_date timestamp without time zone, 
   CONSTRAINT player_session_pkey PRIMARY KEY (id), 
   CONSTRAINT player_session_player_fkey FOREIGN KEY (player_id) REFERENCES player (id) ON UPDATE CASCADE ON DELETE CASCADE
) 
WITH (
  OIDS = FALSE
)
;

CREATE INDEX player_session_token_idx
  ON player_session
  USING btree
  (token COLLATE pg_catalog."default" );
