package Scrapping;


import com.example.cse338project.classes.UefTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static javafx.collections.FXCollections.observableArrayList;

public class UefScrapping {

    public static ObservableList<UefTeam> getUefRank(int year) throws IOException {
        Document doc = Jsoup.connect("https://www.uefa.com/nationalassociations/uefarankings/club/libraries//years/" + String.valueOf(year) + "/").get();
        ArrayList<Element> arr = doc.getElementsByTag("tr");
        arr.remove(0);
        ObservableList<UefTeam> li = FXCollections.observableArrayList();
        for(int i = 0; i < arr.size(); i++){
            Attributes atrarr =  arr.get(i).attributes();
            int trank = Integer.parseInt(atrarr.get("data-rank"));
            ArrayList<Element> tdarr = arr.get(i).getElementsByTag("td");
            Element imgtag = tdarr.get(1).getElementsByTag("img").get(0);
            String imgsrc = imgtag.attributes().get("data-srcset");
            String tname = imgtag.attributes().get("title");
            String tcode = tdarr.get(1).getElementsByTag("span").get(0).getElementsByTag("span").get(1).text();
            String tcoun = tdarr.get(2).text();
            String tpc4 = tdarr.get(3).text();
            String tpc3 = tdarr.get(4).text();
            String tpc2 = tdarr.get(5).text();
            String tpc1 = tdarr.get(6).text();
            String tpc = tdarr.get(7).text();
            String ttotal = tdarr.get(8).text();
            li.add(new UefTeam(tname,tcoun,tcode,tpc,tpc1,tpc2,tpc3,tpc4,ttotal,imgsrc,trank));
        }
        return li;
    }

    public static String getUp(int year) throws IOException {
        Document doc = Jsoup.connect("https://www.uefa.com/nationalassociations/uefarankings/club/libraries//years/" + String.valueOf(year) + "/").get();
        return doc.getElementsByAttributeValue("class","standings-lastupdate").text();
    }

    public static ObservableList<String> getCountries() throws FileNotFoundException {
        String workingDirectory = System.getProperty("user.dir");
        String s = File.separator;
        String path = workingDirectory + s + "src" + s + "main" + s + "resources" + s + "countries.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);
        ObservableList<String> countries = observableArrayList();
        while (sc.hasNextLine())
            countries.add(sc.nextLine());
        return countries;
    }

}
