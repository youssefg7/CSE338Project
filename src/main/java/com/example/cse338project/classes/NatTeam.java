package com.example.cse338project.classes;

import Scrapping.NatTeamJSON;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NatTeam {

    private String name, location, countryCode;
    private double totalPoints, prevPoints;
    private int prevRank, rank;
    private ImageView flag;

    public NatTeam(NatTeamJSON team) {

        this.name = team.getName();
        this.location = team.getLocation();
        this.flag = new ImageView(new Image(team.getFlagSrc())) ;
        this.countryCode = team.getCountryCode();
        this.totalPoints = team.getTotalPoints();
        this.prevPoints = team.getPrevPoints();
        this.prevRank = team.getPrevRank();
        this.rank = team.getRank();

    }
    public NatTeam(String name, String location, String countryCode, double totalPoints, double prevPoints, int prevRank, int rank, String flag) {
        this.name = name;
        this.location = location;
        this.countryCode = countryCode;
        this.totalPoints = totalPoints;
        this.prevPoints = prevPoints;
        this.prevRank = prevRank;
        this.rank = rank;
        this.flag = new ImageView(new Image(flag));
    }
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public double getPrevPoints() {
        return prevPoints;
    }

    public int getPrevRank() {
        return prevRank;
    }

    public int getRank() {
        return rank;
    }


    public ImageView getFlag() {
        return flag;
    }
}
