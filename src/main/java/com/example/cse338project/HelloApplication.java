package com.example.cse338project;


import com.example.cse338project.classes.UefTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HelloApplication {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.uefa.com/nationalassociations/uefarankings/club/libraries//years/2022/").get();
        System.out.println(doc.getElementsByAttributeValue("class","standings-lastupdate").text());


    }
}