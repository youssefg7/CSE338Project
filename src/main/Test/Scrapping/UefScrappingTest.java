package Scrapping;

import com.example.cse338project.classes.UefTeam;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UefScrappingTest {

    @Nested
    class GetUpTests {

        @DisplayName("Testing latest year 2022")
        @Test
        public void getUpTestLatest() throws IOException {
            assertEquals("Last updated: 24/05/2022 10:17", UefScrapping.getUp(2022));
        }

        @DisplayName("Testing 2021")
        @Test
        public void getUpTest() throws IOException {
            assertEquals("Last updated: 20/10/2021 09:14", UefScrapping.getUp(2021));
        }

        @DisplayName("Testing 2007")
        @Test
        public void getUpTest2() throws IOException {
            assertEquals("Last updated: 19/06/2018 09:33", UefScrapping.getUp(2007));
        }
    }

    @Nested
    class GetCountriesTests{
        static ObservableList<String> countriesObservableList;

        @BeforeAll
        public static void beforeAll() throws FileNotFoundException {
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

        @AfterAll
        public static void afterAll() {
            countriesObservableList = null;
        }
    }

    @Nested
    class GetUefRankTests{
        static ObservableList<UefTeam> teamsObservableList;

        @BeforeAll
        public static void beforeAll() throws IOException {
            System.out.println("BeforeAll");
            teamsObservableList = UefScrapping.getUefRank(2007);
            System.out.println(teamsObservableList);
        }

        @Test
        public void test(){
            System.out.println("test");
        }
    }
}