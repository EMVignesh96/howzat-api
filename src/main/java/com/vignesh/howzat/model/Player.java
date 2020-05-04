package com.vignesh.howzat.model;

import java.util.UUID;

public class Player {
    private UUID playerId;
    private String firstName;
    private String lastName;
    private String role;
    private int matches;
    private int runs;
    private double strikeRate;
    private int highest;
    private boolean highestNotOut;
    private int wickets;
    private double economy;
    private int bestSpellWickets;
    private int bestSpellRuns;
    private int price;
    private String bidder;

    public Player(UUID playerId,
                  String firstName,
                  String lastName,
                  String role,
                  int matches,
                  int runs,
                  double strikeRate,
                  int highest,
                  boolean highestNotOut,
                  int wickets,
                  double economy,
                  int bestSpellWickets,
                  int bestSpellRuns,
                  int price,
                  String bidder) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.strikeRate = strikeRate;
        this.highest = highest;
        this.highestNotOut = highestNotOut;
        this.wickets = wickets;
        this.economy = economy;
        this.bestSpellWickets = bestSpellWickets;
        this.bestSpellRuns = bestSpellRuns;
        this.price = price;
        this.bidder = bidder;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public boolean isHighestNotOut() {
        return highestNotOut;
    }

    public void setHighestNotOut(boolean highestNotOut) {
        this.highestNotOut = highestNotOut;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public int getBestSpellWickets() {
        return bestSpellWickets;
    }

    public void setBestSpellWickets(int bestSpellWickets) {
        this.bestSpellWickets = bestSpellWickets;
    }

    public int getBestSpellRuns() {
        return bestSpellRuns;
    }

    public void setBestSpellRuns(int bestSpellRuns) {
        this.bestSpellRuns = bestSpellRuns;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }
}
