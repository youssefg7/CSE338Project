package Integration;

import Scrapping.NatScrapping;
import Scrapping.UefScrapping;
import com.example.cse338project.classes.NatTeam;
import com.example.cse338project.classes.UefTeam;
import com.example.cse338project.gui.RankTableController;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


/** Functions Hierarchy
 * filterYear --> populateUefTable --> (UefScrapping.getUefRank && UefScrapping.getUp)
 */


class UefIntegrationTests {

    /**
     * TopDown Testing requires stubs. Stubs are named the same as the regular functions
     * but with an additional '_' at the end. Ex: function() --> function_()
     */

    @Nested
    @ExtendWith(ApplicationExtension.class)
    class TopDown{

        RankTableController rtc;

        @Start
        public void start(Stage stage) throws Exception {
            rtc = new RankTableController();
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

        /**
         * Open the RankTableController Class in the filterYear function and
         * place an '_' after the populateUefTable function so that it
         * is written as "populateUefTable_(k)"
         */
        @Test
        void TestingFilterYear(){

            rtc.yearFilter.setValue("2020");
            try{
                rtc.filterYear();
            }catch (Exception e){
                assertEquals(e.getMessage(),"2020");
            }
            rtc.yearFilter.setValue("2010");
            try{
                rtc.filterYear();
            }catch (Exception e){
                assertEquals(e.getMessage(),"2010");
            }
            rtc.yearFilter.setValue("2005");
            try{
                rtc.filterYear();
            }catch (Exception e){
                assertEquals(e.getMessage(),"2005");
            }
        }


        /**
         * Open the RankTableController Class in the populateUefTable function and
         * place an '_' after the UefScrapping.getUefRank and the UefScrapping.getUp function so that it
         * is written as "UefScrapping.getUefRank_(k)" and "UefScrapping.getUp_(k)"
         * and return the populate function in the filterYear function as it was.
         */
        @Test
        void TestingWPopulate() throws Exception {

            rtc.yearFilter.setValue("2020");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"2020");
            assertEquals(rtc.labelLastUp.getText(),"2020");

            rtc.yearFilter.setValue("2010");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"2010");
            assertEquals(rtc.labelLastUp.getText(),"2010");

            rtc.yearFilter.setValue("2005");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"2005");
            assertEquals(rtc.labelLastUp.getText(),"2005");

        }


        /**
         * remove the '_' after all functions to test the system as a whole
         */
        @Test
        void TestingWScrape() throws Exception {
            rtc.yearFilter.setValue("2017");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"Real Madrid CF");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:52");

            rtc.yearFilter.setValue("2010");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"FC Barcelona");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:32");

            rtc.yearFilter.setValue("2005");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(1).getName(),"Valencia CF");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:34");

        }

    }


    /**
     * BottomUp Testing requires drivers.
     * If a driver is required for a test then
     * it is created within that test below.
     */

    @Nested
    @ExtendWith(ApplicationExtension.class)
    class BottomUp{

        RankTableController rtc;


        @Start
        public void start(Stage stage) throws Exception {
            rtc = new RankTableController();
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
        void TestingScrape() throws Exception {
            ObservableList<UefTeam> obsli = UefScrapping.getUefRank(2017);
            assertEquals(obsli.get(0).getName(),"Real Madrid CF");
            obsli.clear();
            obsli = UefScrapping.getUefRank(2010);
            assertEquals(obsli.get(0).getName(),"FC Barcelona");
            obsli.clear();
            obsli = UefScrapping.getUefRank(2005);
            assertEquals(obsli.get(1).getName(),"Valencia CF");
            assertEquals(UefScrapping.getUp(2017),"Last updated: 19/06/2018 09:52");
            assertEquals(UefScrapping.getUp(2010),"Last updated: 19/06/2018 09:32");
            assertEquals(UefScrapping.getUp(2005),"Last updated: 19/06/2018 09:34");
        }


        @Test
        void TestingPopulate(){
            rtc.populateUefTable(2017);
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"Real Madrid CF");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:52");

            rtc.populateUefTable(2010);
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"FC Barcelona");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:32");

            rtc.populateUefTable(2005);
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(1).getName(),"Valencia CF");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:34");

        }


        @Test
        void TestingFilterYear() throws Exception {
            rtc.yearFilter.setValue("2017");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"Real Madrid CF");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:52");

            rtc.yearFilter.setValue("2010");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(0).getName(),"FC Barcelona");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:32");

            rtc.yearFilter.setValue("2005");
            rtc.filterYear();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.UefarankingTable.getItems().size()!=0);
            assertEquals(rtc.UefarankingTable.getItems().get(1).getName(),"Valencia CF");
            assertEquals(rtc.labelLastUp.getText(),"Last updated: 19/06/2018 09:34");

        }

    }

}