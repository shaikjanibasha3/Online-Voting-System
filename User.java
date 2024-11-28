package com.letsVote;

public class User {
    private String name;
    private String voterId;
    private String password;
    private int age;

    public User(String name, String voterId, String password, int age) {
        this.name = name;
        this.voterId = voterId;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getVoterId() {
        return voterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }
}



