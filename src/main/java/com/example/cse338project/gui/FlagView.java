package com.example.cse338project.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlagView {
    private ImageView image;

    FlagView(String src) {
        this.image = new ImageView(new Image(src));
    }

    public void setImage(String value) {
        image.setImage(new Image(value));
    }

    public ImageView getImage() {
        return image;
    }
}
