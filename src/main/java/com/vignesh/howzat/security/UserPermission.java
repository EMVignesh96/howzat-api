package com.vignesh.howzat.security;

public enum UserPermission {
    AUCTION_READ("auction:read"),
    AUCTION_WRITE("auction:write"),
    PLAYER_READ("player:read"),
    PLAYER_UPDATE("player:update"),
    TEAM_READ("team:read"),
    TEAM_WRITE("team:write"),
    DASHBOARD_READ("dashboard:read");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
