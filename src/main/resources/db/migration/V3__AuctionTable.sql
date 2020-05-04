CREATE TABLE auction (
    player_uuid uuid PRIMARY KEY REFERENCES players(player_uuid),
    state INTEGER NOT NULL,
    price INTEGER,
    bidder VARCHAR(50) REFERENCES users(username)
);

INSERT INTO auction (player_uuid, state, price) (SELECT player_uuid, 0, 20 FROM players);