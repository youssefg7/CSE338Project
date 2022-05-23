package com.example.cse338project.gui;

import Scrapping.Scrapping;
import com.example.cse338project.classes.NatTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class RankTableController implements Initializable {

    @FXML
    private ComboBox<String> continentFilter;
    @FXML
    private ComboBox<String> datesFilter;

    @FXML
    private TableView<NatTeam> rankingTable;

    @FXML
    private TableColumn<NatTeam, Integer> rankColumn;
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

    private ArrayList<NatTeam> MainLi;
    private ObservableList<String> dates;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rankingTable.getItems().clear();
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        flagColumn.setCellValueFactory(new PropertyValueFactory<>("flag"));
        diffColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        previousColumn.setCellValueFactory(new PropertyValueFactory<>("prevPoints"));
        ArrayList<String> continents = new ArrayList<>();
        Collections.addAll(continents, "All","AFC", "UEFA", "CAF", "OFC", "CONCACAF", "CONMEBOL");
        continentFilter.getItems().addAll(continents);

        try {
            dates = Scrapping.getDates();
            datesFilter.setItems(dates);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            populateTable(13603);
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Make sure you have a stable Internet Connection", ButtonType.OK);
            a.setTitle("Error");
            Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("error.png"));
            a.showAndWait();
            e.printStackTrace();
        }
    }

    public void populateTable(int i) throws JSONException, IOException {
        rankingTable.getItems().clear();
        ObservableList<NatTeam> li = Scrapping.getRanking(i);
        MainLi = new ArrayList<>(li);
        rankingTable.setItems(li);
    }

    public void filterContinent() {
        String filter = continentFilter.getValue();
        System.out.println(filter);
        System.out.println(MainLi);
        ArrayList<NatTeam> cli = new ArrayList<>(MainLi);
        if(!filter.equals("All")){
            cli.removeIf(t -> !t.getLocation().equals(filter));
        }
        System.out.println("filteredTeams" + cli);
        rankingTable.getItems().clear();
        rankingTable.setItems(FXCollections.observableList(cli));
    }

    public void filterDate() throws JSONException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate dateChoice = LocalDate.parse(datesFilter.getValue(), dtf);
        LocalDate feb2007 = LocalDate.parse("14 Feb 2007", dtf);
        LocalDate dec2002 = LocalDate.parse("18 Dec 2002", dtf);
        ArrayList<String> arr = new ArrayList<>(dates);
        int index = arr.indexOf(datesFilter.getValue());
        int datediff1 = Math.toIntExact(ChronoUnit.DAYS.between(feb2007, dateChoice));
        int datediff2 = Math.toIntExact(ChronoUnit.DAYS.between(dec2002, dateChoice));
        System.out.println("here");
        if(datediff1 >= 0){
            populateTable(datediff1+8079);
        }else if(datediff2 > 0){
            System.out.println(arr.size() - index + 1);
            populateTable(arr.size() - index + 1);
        }else{
            System.out.println(arr.size() - index);
            populateTable(arr.size() - index);
        }

    }

}
