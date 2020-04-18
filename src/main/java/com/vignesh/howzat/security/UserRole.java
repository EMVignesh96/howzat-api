package com.vignesh.howzat.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.vignesh.howzat.security.UserPermission.*;

public enum UserRole {
    VIEWER(Sets.newHashSet()),
    BUYER(Sets.newHashSet(AUCTION_READ, PLAYER_READ, PLAYER_UPDATE, TEAM_READ, TEAM_WRITE, DASHBOARD_READ)),
    AUCTIONEER(Sets.newHashSet(AUCTION_WRITE, PLAYER_READ, DASHBOARD_READ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
