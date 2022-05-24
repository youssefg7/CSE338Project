package com.example.cse338project.classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UefTeam {

    private String name, country, teamCode;
    private String curPoints, c1Points, c2Points, c3Points, c4Points, totalPoints;
    private ImageView flag;
    private int rank;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public String getCurPoints() {
        return curPoints;
    }

    public String getC1Points() {
        return c1Points;
    }

    public String getC2Points() {
        return c2Points;
    }

    public String getC3Points() {
        return c3Points;
    }

    public String getC4Points() {
        return c4Points;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public ImageView getFlag() {
        return flag;
    }

    public int getRank() {
        return rank;
    }

    public UefTeam(String name, String country, String teamCode, String curPoints, String c1Points, String c2Points, String c3Points, String c4Points, String totalPoints, String flag, int rank) {
        this.name = name;
        this.country = country;
        this.teamCode = teamCode;
        this.curPoints = curPoints;
        this.c1Points = c1Points;
        this.c2Points = c2Points;
        this.c3Points = c3Points;
        this.c4Points = c4Points;
        this.totalPoints = totalPoints;
        this.flag = new ImageView(new Image(flag));
        this.rank = rank;
    }
}
