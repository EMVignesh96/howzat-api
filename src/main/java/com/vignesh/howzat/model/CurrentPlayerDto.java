package com.vignesh.howzat.model;

public class CurrentPlayerDto {
    Player player;
    boolean allPlayersVisited;

    public CurrentPlayerDto(Player player) {
        this.player = player;
        allPlayersVisited = player == null;
    }

    public CurrentPlayerDto(Player player, boolean allPlayersVisited) {
        this.player = player;
        this.allPlayersVisited = allPlayersVisited;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isAllPlayersVisited() {
        return allPlayersVisited;
    }

    public void setAllPlayersVisited(boolean allPlayersVisited) {
        this.allPlayersVisited = allPlayersVisited;
    }
}
