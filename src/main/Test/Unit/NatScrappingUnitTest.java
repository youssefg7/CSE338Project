package Unit;

import Scrapping.NatScrapping;
import com.example.cse338project.classes.NatTeam;

import javafx.collections.ObservableList;

import javafx.stage.Stage;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;


class NatScrappingUnitTest {

    @Nested
    class GetDatesTests {

        static ObservableList<String> datesObservableList;

        @BeforeAll
        public static void beforeAll() throws FileNotFoundException {
            datesObservableList = NatScrapping.getDates();
        }

        @Test
        @DisplayName("First date in list")
        public void firstDateTest() {
            assertEquals("31 Mar 2022", datesObservableList.get(0));
        }

        @Test
        @DisplayName("Another date in list")
        public void anotherDateTest() {
            assertEquals("03 Oct 2012", datesObservableList.get(100));
        }

        @Test
        @DisplayName("Last date in list")
        public void lastDateTest() {
            assertEquals("31 Dec 1992", datesObservableList.get(318));
        }

        @AfterAll
        public static void afterAll() {
            datesObservableList = null;
        }
    }

    @Nested
    @ExtendWith(ApplicationExtension.class)
    class GetRankingTests {

        static ObservableList<NatTeam> teamsObservableList;

        @Start
        public void start(Stage stage) throws Exception {
            teamsObservableList = NatScrapping.getRanking(13603);
        }

        @Test
        public void TeamTest() throws JSONException, IOException {
            assertEquals("Brazil", teamsObservableList.get(0).getName());
        }

    }

}