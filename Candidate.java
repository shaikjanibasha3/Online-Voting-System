package com.letsVote;

public class Candidate {
    private String name;
    private String party;
    private String symbol;
    private int votes;

    public Candidate(String name, String party, String symbol) {
        this.name = name;
        this.party = party;
        this.symbol = symbol;
        this.votes = 0; // Initial votes are 0
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void incrementVotes() {
        this.votes++;
    }

    public int getVotes() {
        return votes;
    }

    
    public String Party() {
        return name + " (" + party + ") - " + symbol;
    }
}




