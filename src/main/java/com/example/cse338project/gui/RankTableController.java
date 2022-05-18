package com.example.cse338project.gui;

import Scrapping.Scrapping;
import com.example.cse338project.classes.NatTeam;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RankTableController implements Initializable {

    @FXML
    private TableView<NatTeam> rankingTable;

    @FXML
    private TableColumn<NatTeam,Integer> rankColumn;
    @FXML
    private TableColumn<NatTeam, ImageView> flagColumn;
    @FXML
    private TableColumn<NatTeam, String> teamColumn;
    @FXML
    private TableColumn<NatTeam, Float> pointsColumn;
    @FXML
    private TableColumn<NatTeam, Float> previousColumn;
    @FXML
    private TableColumn<NatTeam, String> diffColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<NatTeam> li;
        rankingTable.getItems().clear();
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        flagColumn.setCellValueFactory(new PropertyValueFactory<>("flag"));
        diffColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        previousColumn.setCellValueFactory(new PropertyValueFactory<>("prevPoints"));


        try {
            li = Scrapping.getRanking(13603);
            rankingTable.setItems(li);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
