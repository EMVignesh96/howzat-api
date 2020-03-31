package com.vignesh.howzat.model;

import java.util.ArrayList;
import java.util.List;

public class UserKeys {
    private List<String> keys;

    public UserKeys() {
        keys = new ArrayList<>();
    }

    public UserKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public boolean addKey(String key) {
        return keys.add(key);
    }
}
