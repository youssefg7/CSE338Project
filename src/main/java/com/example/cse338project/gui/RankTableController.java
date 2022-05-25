package com.example.cse338project.gui;

import Scrapping.NatScrapping;
import Scrapping.UefScrapping;
import com.example.cse338project.classes.NatTeam;
import com.example.cse338project.classes.UefTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.ImageView;


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
    private ComboBox<String> countryFilter;
    @FXML
    private ComboBox<String> yearFilter;

    @FXML
    private TableView<NatTeam> rankingTable;
    @FXML
    private TableView<UefTeam> UefarankingTable;

    @FXML
    private TableColumn<UefTeam, Integer> UrankColumn;
    @FXML
    private TableColumn<UefTeam, ImageView> UflagColumn;
    @FXML
    private TableColumn<UefTeam, String> UteamColoumn;
    @FXML
    private TableColumn<UefTeam, String> UteamCodeCol;
    @FXML
    private TableColumn<UefTeam, String> UcountryColoumn;
    @FXML
    private TableColumn<UefTeam, String> Utcp4;
    @FXML
    private TableColumn<UefTeam, String> Utcp3;
    @FXML
    private TableColumn<UefTeam, String> Utcp2;
    @FXML
    private TableColumn<UefTeam, String> Utcp1;
    @FXML
    private TableColumn<UefTeam, String> Utcp;
    @FXML
    private TableColumn<UefTeam,String> utotal;

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

    @FXML
    private Label labelLastUp;

    private ArrayList<NatTeam> MainLi;
    private ArrayList<UefTeam> MainUefLi;
    private ObservableList<String> dates;
    private ObservableList<String> countries;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rankingTable.getItems().clear();
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        flagColumn.setCellValueFactory(new PropertyValueFactory<>("flag"));
        diffColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        previousColumn.setCellValueFactory(new PropertyValueFactory<>("prevPoints"));

        UefarankingTable.getItems().clear();
        UrankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        UflagColumn.setCellValueFactory(new PropertyValueFactory<>("flag"));
        UteamColoumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        UcountryColoumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        Utcp4.setCellValueFactory(new PropertyValueFactory<>("c4Points"));
        Utcp3.setCellValueFactory(new PropertyValueFactory<>("c3Points"));
        Utcp2.setCellValueFactory(new PropertyValueFactory<>("c2Points"));
        Utcp1.setCellValueFactory(new PropertyValueFactory<>("c1Points"));
        Utcp.setCellValueFactory(new PropertyValueFactory<>("curPoints"));
        utotal.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        UteamCodeCol.setCellValueFactory(new PropertyValueFactory<>("teamCode"));
        ArrayList<String> continents = new ArrayList<>();
        ArrayList<String> years = new ArrayList<>();
        Collections.addAll(continents, "All", "AFC", "UEFA", "CAF", "OFC", "CONCACAF", "CONMEBOL");
        for(int i = 22; i >= 5 ; i--){
            years.add(String.valueOf(2000+i));
        }
        continentFilter.getItems().addAll(continents);
        yearFilter.getItems().addAll(years);

        try {
            dates = NatScrapping.getDates();
            datesFilter.setItems(dates);
            countries = UefScrapping.getCountries();
            countryFilter.setItems(countries);
        } catch (Exception e) {
            e.printStackTrace();
        }

        populateUefTable(2022);
        populateTable(13603);
    }

    public void populateUefTable(int i){
        try{
            labelLastUp.setText(UefScrapping.getUp(i));
        }catch (Exception e){
            labelLastUp.setText("Error");
        }
        Utcp.setText(String.valueOf(i));
        Utcp1.setText(String.valueOf(i-1));
        Utcp2.setText(String.valueOf(i-2));
        Utcp3.setText(String.valueOf(i-3));
        Utcp4.setText(String.valueOf(i-4));
        Task task = new Task() {
            @Override
            protected Object call() {
                try{
                    UefarankingTable.getItems().clear();
                    System.out.println("befff");
                    UefarankingTable.setPlaceholder(new Label("Loading..."));
                    yearFilter.setDisable(true);
                    countryFilter.setDisable(true);
                    ObservableList<UefTeam> li = UefScrapping.getUefRank(i);
                    yearFilter.setDisable(false);
                    countryFilter.setDisable(false);
                    System.out.println("afttt");
                    MainUefLi = new ArrayList<>(li);
                    UefarankingTable.setItems(li);
                }catch (Exception e){
                    e.printStackTrace();
                    UefarankingTable.setPlaceholder(new Label("Make sure you have a stable internet connection."));
                    labelLastUp.setText("###");
                    yearFilter.setDisable(false);
                    countryFilter.setDisable(false);
                    Utcp.setText("-");
                    Utcp1.setText("-");
                    Utcp2.setText("-");
                    Utcp3.setText("-");
                    Utcp4.setText("-");
                }
                return null;
            }
        };

        new Thread(task).start();
    }

    public void populateTable(int i){
        Task task = new Task() {
            @Override
            protected Object call() {
                try{
                    rankingTable.getItems().clear();
                    System.out.println("bef");
                    rankingTable.setPlaceholder(new Label("Loading..."));
                    datesFilter.setDisable(true);
                    continentFilter.setDisable(true);
                    ObservableList<NatTeam> li = NatScrapping.getRanking(i);
                    datesFilter.setDisable(false);
                    continentFilter.setDisable(false);
                    System.out.println("aft");
                    MainLi = new ArrayList<>(li);
                    rankingTable.setItems(li);
                }catch (Exception e){
                    e.printStackTrace();
                    rankingTable.setPlaceholder(new Label("Make sure you have a stable internet connection."));
                    datesFilter.setDisable(false);
                    continentFilter.setDisable(false);
                }
                return null;
            }
        };

        new Thread(task).start();

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

    public void filterCountry(){
        String filter = countryFilter.getValue();
        System.out.println(filter);
        System.out.println(MainUefLi);
        ArrayList<UefTeam> cli = new ArrayList<>(MainUefLi);
        if(!filter.equals("All")){
            cli.removeIf(t -> !t.getCountry().equals(filter));
        }
        System.out.println("filteredTeams" + cli);
        UefarankingTable.getItems().clear();
        UefarankingTable.setItems(FXCollections.observableList(cli));
    }

    public void filterYear(){
        int yr = Integer.parseInt(yearFilter.getValue());
        populateUefTable(yr);
    }

    public void filterDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate dateChoice = LocalDate.parse(datesFilter.getValue(), dtf);
        LocalDate feb2007 = LocalDate.parse("14 Feb 2007", dtf);
        LocalDate dec2002 = LocalDate.parse("18 Dec 2002", dtf);
        ArrayList<String> arr = new ArrayList<>(dates);
        int index = arr.indexOf(datesFilter.getValue());
        int datediff1 = Math.toIntExact(ChronoUnit.DAYS.between(feb2007, dateChoice));
        int datediff2 = Math.toIntExact(ChronoUnit.DAYS.between(dec2002, dateChoice));
        System.out.println("here");
        if (datediff1 >= 0) {
            populateTable(datediff1 + 8079);
        } else if (datediff2 > 0) {
            System.out.println(arr.size() - index + 1);
            populateTable(arr.size() - index + 1);
        } else {
            System.out.println(arr.size() - index);
            populateTable(arr.size() - index);
        }

    }

}
