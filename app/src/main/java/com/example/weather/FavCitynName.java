package com.example.weather;

public class FavCitynName {

    private String name;
    private String nickname;

    public FavCitynName(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }
    public FavCitynName (){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
