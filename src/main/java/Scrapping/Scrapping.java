
package Scrapping;
import com.example.cse338project.classes.NatTeam;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class Scrapping {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static ObservableList<NatTeam> getPure(List<NatTeamJSON> arr){
        ObservableList<NatTeam> li = observableArrayList();
        for(int i = 0; i< arr.size(); i++){
            li.add(new NatTeam(arr.get(i)));
        }
        return li;

    }

    public static ObservableList<NatTeam> getRanking(int i) throws IOException, JSONException, IllegalArgumentException {

        String url = "https://www.fifa.com/api/ranking-overview?locale=en&dateId=id" + String.valueOf(i);
        JSONObject json = readJsonFromUrl(url);

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        List<NatTeamJSON> natTeamJSONS = objectMapper.readValue(json.get("rankings").toString(), new TypeReference<List<NatTeamJSON>>(){});

        return getPure(natTeamJSONS);
    }
}



