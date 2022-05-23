
package Scrapping;

import com.example.cse338project.classes.NatTeam;

import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

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

    public static ObservableList<NatTeam> getPure(List<NatTeamJSON> arr) {
        ObservableList<NatTeam> li = observableArrayList();
        for (int i = 0; i < arr.size(); i++) {
            li.add(new NatTeam(arr.get(i)));
        }
        return li;

    }

    public static ObservableList<NatTeam> getRanking(int i) throws IOException, JSONException, IllegalArgumentException {
        String url = "https://www.fifa.com/api/ranking-overview?locale=en&dateId=id" + i;
        JSONObject json = readJsonFromUrl(url);
        JSONArray arr = (JSONArray) json.get("rankings");
        ObservableList<NatTeam> ls = observableArrayList();
        for (int j = 0; j < arr.length(); j++) {
            JSONObject obj = arr.getJSONObject(j);
            NatTeam temp = new NatTeam(obj.getJSONObject("rankingItem").getString("name"), obj.getJSONObject("tag").getString("text"), obj.getJSONObject("rankingItem").getString("countryCode"), obj.getJSONObject("rankingItem").getDouble("totalPoints"), obj.getDouble("previousPoints"), obj.getJSONObject("rankingItem").getInt("previousRank"), obj.getJSONObject("rankingItem").getInt("rank"), obj.getJSONObject("rankingItem").getJSONObject("flag").getString("src"));
            ls.add(temp);
        }
        return ls;
    }

    public static ObservableList<String> getDates() throws FileNotFoundException {
        String workingDirectory = System.getProperty("user.dir");
        String s = File.separator;
        String path = workingDirectory + s + "src" + s + "main" + s + "resources" + s + "dates.txt";
        System.out.println(path);
        File file = new File(path);
        Scanner sc = new Scanner(file);
        ObservableList<String> dates = observableArrayList();
        while (sc.hasNextLine())
            dates.add(sc.nextLine());
        return dates;
    }

}



