package Unit;

import Scrapping.UefScrapping;
import com.example.cse338project.classes.UefTeam;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UefScrappingUnitTest {

    @Nested
    class GetUpTests {

        @DisplayName("Testing 2021")
        @Test
        public void getUpTest() throws IOException {
            Assertions.assertEquals("Last updated: 20/10/2021 09:14", UefScrapping.getUp(2021));
        }

        @DisplayName("Testing 2007")
        @Test
        public void getUpTest2() throws IOException {
            assertEquals("Last updated: 19/06/2018 09:33", UefScrapping.getUp(2007));
        }

        @DisplayName("Testing 2010")
        @Test
        public void getUpTest3() throws IOException {
            assertEquals("Last updated: 19/06/2018 09:32", UefScrapping.getUp(2010));
        }
        @DisplayName("Wrong Testing 2010")
        @Test
        public void getUpTest4() throws IOException {
            assertNotEquals("Last updated: 19/06/2010 09:32", UefScrapping.getUp(2010));
        }

    }

    @Nested
    @ExtendWith(ApplicationExtension.class)
    class GetCountriesTests{
        static ObservableList<String> countriesObservableList;

        @Start
        public static void start(Stage stage) throws FileNotFoundException {
            countriesObservableList = UefScrapping.getCountries();
        }

        @Test
        @DisplayName("First country in list")
        public void firstCountryTest() {
            assertEquals("All", countriesObservableList.get(0));
        }

        @Test
        @DisplayName("Another country in list")
        public void anotherCountryTest() {
            assertEquals("ENG", countriesObservableList.get(14));
        }

        @Test
        @DisplayName("Last country in list")
        public void lastCountryTest() {
            assertEquals("WAL", countriesObservableList.get(55));
        }

        @Test
        @DisplayName("Country not in list")
        public void countryNotInListTest(){
            assertFalse(countriesObservableList.contains("KKK"));
        }

        @AfterAll
        public static void afterAll() {
            countriesObservableList = null;
        }
    }

    @Nested
    @ExtendWith(ApplicationExtension.class)
    class GetUefRankTests{
        static ObservableList<UefTeam> teamsObservableList;

        @Start
        public static void start(Stage stage) throws IOException {
            teamsObservableList = UefScrapping.getUefRank(2007);

        }

        @Test
        public void TeamTest(){
            assertEquals("AC Milan", teamsObservableList.get(0).getName());
        }
    }
}