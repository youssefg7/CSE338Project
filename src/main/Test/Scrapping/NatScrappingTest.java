package Scrapping;

import com.example.cse338project.classes.NatTeam;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NatScrappingTest {


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
    class GetRankingTests {
        static ObservableList<NatTeam> teamsObservableList;

        @BeforeAll
        public static void beforeAll() throws JSONException, IOException, RuntimeException {
            teamsObservableList = NatScrapping.getRanking(13603);
        }

        @Test
        public void firstTeamTest() {
            assertEquals("Brazil", teamsObservableList.get(0).getName());
        }
    }

}