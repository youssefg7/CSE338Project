package Performance;

import Scrapping.NatScrapping;
import Scrapping.UefScrapping;
import com.example.cse338project.gui.RankTableController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class PerformanceTests {

    RankTableController rtc;

    @Start
    public void start(Stage stage) throws Exception {
        rtc = new RankTableController();
        rtc.datesFilter = new ComboBox<>();
        rtc.dates = NatScrapping.getDates();
        rtc.rankingTable = new TableView<>();
        rtc.continentFilter = new ComboBox<>();
        rtc.yearFilter = new ComboBox<>();
        rtc.countries = UefScrapping.getCountries();
        rtc.UefarankingTable = new TableView<>();
        rtc.countryFilter = new ComboBox<>();
        rtc.labelLastUp = new Label();
        rtc.Utcp = new TableColumn<>();
        rtc.Utcp1 = new TableColumn<>();
        rtc.Utcp2 = new TableColumn<>();
        rtc.Utcp3 = new TableColumn<>();
        rtc.Utcp4 = new TableColumn<>();
    }

    @Test
    void testingNatPopulatePerf(){

        long startTime = System.currentTimeMillis();
        rtc.populateTable(1);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println(time);
        assertTrue(time < 30000);

    }

    @Test
    void testingUefPopulatePerf(){

        long startTime = System.currentTimeMillis();
        rtc.populateUefTable(2022);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println(time);
        assertTrue(time < 60000);

    }



}
