package com.example.cse338project.gui;

import Scrapping.Scrapping;
import com.example.cse338project.classes.NatTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private ObservableList<NatTeam> li;
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
        Collections.addAll(continents, "AFC", "UEFA", "CAF", "OFC", "CONCACAF", "CONMEBOL");
        continentFilter.getItems().addAll(continents);

        try {
            dates = Scrapping.getDates();
            datesFilter.getItems().addAll(dates);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            populateTable(13603);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateTable(int i) throws JSONException, IOException {
        rankingTable.getItems().clear();
        li = Scrapping.getRanking(i);
        rankingTable.setItems(li);
        dates = Scrapping.getDates();
        datesFilter.getItems().addAll(dates);
    }

    public void filterContinent() {
        String filter = continentFilter.getValue();
        System.out.println(filter);
        ObservableList<NatTeam> toBeFiltered = FXCollections.observableArrayList(li);
        System.out.println("tobefiltered " + toBeFiltered);
        System.out.println("tobefiltered.filtered" + toBeFiltered.filtered(t -> t.getLocation().equals(filter)));
        FilteredList<NatTeam> filteredTeams = toBeFiltered.filtered(t -> t.getLocation().equals(filter));//li.stream().filter(t->t.getLocation().equals(filter)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        System.out.println("filteredTeams" + filteredTeams);
        toBeFiltered.stream().close();
        rankingTable.getItems().clear();
        rankingTable.getItems().addAll((ObservableList) filteredTeams);
    }

    public void filterDate() throws ParseException, JSONException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate feb2007 = LocalDate.parse("14 Feb 2007", dtf);
        System.out.println("here");
        LocalDate dateChoice = LocalDate.parse(datesFilter.getValue(), dtf);
        populateTable(Math.toIntExact(ChronoUnit.DAYS.between(feb2007, dateChoice))+8079);
    }

}
