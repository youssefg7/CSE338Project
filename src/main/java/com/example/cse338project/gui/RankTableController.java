package com.example.cse338project.gui;

import com.example.cse338project.classes.RankingItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RankTableController implements Initializable {

    @FXML
    private TableView<RankingItem> rankingTable;

    @FXML
    private TableColumn<RankingItem,Integer> rankColumn;
    @FXML
    private TableColumn<RankingItem, FlagView> flagColumn;
    @FXML
    private TableColumn<RankingItem, String> teamColumn;
    @FXML
    private TableColumn<RankingItem, Float> pointsColumn;
    @FXML
    private TableColumn<RankingItem, Float> previousColumn;
    @FXML
    private TableColumn<RankingItem, Float> diffColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<RankingItem> imgList = FXCollections.observableArrayList();
        FlagView flag1 = new FlagView("https://cloudinary.fifa.com/api/v1/picture/flags-sq-2/ENG?tx=c_fill,g_auto,q_auto,w_70");
        FlagView flag2 = new FlagView("https://cloudinary.fifa.com/api/v1/picture/flags-sq-2/FRA?tx=c_fill,g_auto,q_auto,w_70");
        imgList.addAll(flag1, flag2);
        flagColumn = new TableColumn<RankingItem, FlagView>("Flag");
        flagColumn.setCellValueFactory(new PropertyValueFactory<RankingItem, FlagView>("Flag"));
        flagColumn.setPrefWidth(60);

        /* add column to the tableview and set its items */
        rankingTable.getColumns().add(flagColumn);
        rankingTable.setItems(imgList);
    }


}
