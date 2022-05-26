package Integration;

import Scrapping.NatScrapping;
import com.example.cse338project.classes.NatTeam;
import com.example.cse338project.gui.RankTableController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
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
 * filterDate --> populateTable --> NatScrapping.getRanking
 */


class NatIntegrationTests {

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
            rtc.datesFilter = new ComboBox<>();
            rtc.dates = NatScrapping.getDates();
            rtc.rankingTable = new TableView<>();
            rtc.continentFilter = new ComboBox<>();
        }

        /**
         * Open the RankTableController Class in the filterDate function and
         * place an '_' after the populateTable function so that it
         * is written as "populateTable_(k)"
         */
        @Test
        void TestingFilterDate(){

            rtc.datesFilter.setValue("07 Feb 2019");
            try{
                rtc.filterDate();
            }catch (Exception e){
                assertEquals(e.getMessage(),"12455");
            }
            rtc.datesFilter.setValue("02 Sep 2009");
            try{
                rtc.filterDate();
            }catch (Exception e){
                assertEquals(e.getMessage(),"9010");
            }
            rtc.datesFilter.setValue("14 May 1997");
            try{
                rtc.filterDate();
            }catch (Exception e){
                assertEquals(e.getMessage(),"39");
            }
        }


        /**
         * Open the RankTableController Class in the populateTable function and
         * place an '_' after the NatScrapping.getRanking function so that it
         * is written as "NatScrapping.getRanking_(k)"
         * and return the populate function in the filterDate function as it was.
         */
        @Test
        void TestingWPopulate() throws Exception {

            rtc.datesFilter.setValue("07 Feb 2019");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"12455");

            rtc.datesFilter.setValue("02 Sep 2009");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"9010");

            rtc.datesFilter.setValue("14 May 1997");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"39");

        }


        /**
         * remove the '_' after all functions to test the system as a whole
         */
        @Test
        void TestingWScrape() throws Exception {
            rtc.datesFilter.setValue("07 Feb 2019");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Belgium");

            rtc.datesFilter.setValue("12 Jan 2011");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Spain");

            rtc.datesFilter.setValue("14 May 1997");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Brazil");
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
            rtc.datesFilter = new ComboBox<>();
            rtc.dates = NatScrapping.getDates();
            rtc.rankingTable = new TableView<>();
            rtc.continentFilter = new ComboBox<>();
        }


        @Test
        void TestingScrape() throws Exception {
            ObservableList<NatTeam> obsli = NatScrapping.getRanking(39);
            assertEquals(obsli.get(0).getName(),"Brazil");
            obsli.clear();
            obsli = NatScrapping.getRanking(9507);
            assertEquals(obsli.get(0).getName(),"Spain");
            obsli.clear();
            obsli = NatScrapping.getRanking(12455);
            assertEquals(obsli.get(0).getName(),"Belgium");
        }


        @Test
        void TestingPopulate(){
            rtc.populateTable(39);
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Brazil");

            rtc.populateTable(9507);
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Spain");

            rtc.populateTable(12455);
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Belgium");

        }


        @Test
        void TestingFilterDate() throws Exception {
            rtc.datesFilter.setValue("07 Feb 2019");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Belgium");

            rtc.datesFilter.setValue("12 Jan 2011");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Spain");

            rtc.datesFilter.setValue("14 May 1997");
            rtc.filterDate();
            Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> rtc.rankingTable.getItems().size()!=0);
            assertEquals(rtc.rankingTable.getItems().get(0).getName(),"Brazil");
        }

    }

}