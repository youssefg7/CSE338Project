package com.example.cse338project.classes;

import Scrapping.NatTeamJSON;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NatTeam {

    private String name, location, countryCode;
    private float totalPoints, prevPoints;
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

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public float getTotalPoints() {
        return totalPoints;
    }

    public float getPrevPoints() {
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
