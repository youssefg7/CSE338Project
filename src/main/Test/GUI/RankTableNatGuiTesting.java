package GUI;

import com.example.cse338project.classes.NatTeam;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import org.awaitility.Awaitility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@ExtendWith(ApplicationExtension.class)
public class RankTableNatGuiTesting {


    @Start
    private void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RankTable.fxml")))));
        stage.show();
    }

    /**
     * This Test will always Fail due to a thread error that can be disregarded.
     *      IF THE TEST BRINGS UP THE '!' SIGN THEN THE TESTS PASSED
     *      IF THE TEST BRINGS UP THE 'X' SIGN THEN THE TESTS FAILED
     The Following Results are Comparisons between the program
     and Fifa's official website
     https://www.fifa.com/fifa-world-ranking/men
     */
    @Test
    void Feb10_2022Test(FxRobot robot) {
        TableView<NatTeam> ttb =  robot.lookup("#rankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> mcb = robot.lookup("#datesFilter").queryAs(ComboBox.class);
        assertEquals(mcb.getItems().size(),319);
        assertEquals(mcb.getItems().get(1), "10 Feb 2022");
        robot.interact(() -> mcb.getSelectionModel().select(1));
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size() != 0);
        assertEquals(ttb.getItems().get(0).getCountryCode(),"BEL");
        assertEquals(ttb.getItems().get(0).getTotalPoints(),1828.45);
    }

    /**
     * This Test will always Fail due to a thread error that can be disregarded.
     *      IF THE TEST BRINGS UP THE '!' SIGN THEN THE TESTS PASSED
     *      IF THE TEST BRINGS UP THE 'X' SIGN THEN THE TESTS FAILED
     The Following Results are Comparisons between the program
     and Fifa's official website
     https://www.fifa.com/fifa-world-ranking/men
     */
    @Test
    void Jul14_2010Test(FxRobot robot) {
        TableView<NatTeam> ttb =  robot.lookup("#rankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> mcb = robot.lookup("#datesFilter").queryAs(ComboBox.class);
        assertEquals(mcb.getItems().size(),319);
        assertEquals(mcb.getItems().get(127), "14 Jul 2010");
        robot.interact(() -> mcb.getSelectionModel().select(127));
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size() != 0);
        assertEquals(ttb.getItems().get(8).getCountryCode(),"EGY");
        assertEquals(ttb.getItems().get(8).getTotalPoints(),1053);
    }

    /**
     * This Test will always Fail due to a thread error that can be disregarded.
     *      IF THE TEST BRINGS UP THE '!' SIGN THEN THE TESTS PASSED
     *      IF THE TEST BRINGS UP THE 'X' SIGN THEN THE TESTS FAILED
     The Following Results are Comparisons between the program
     and Fifa's official website
     https://www.fifa.com/fifa-world-ranking/men
     */
    @Test
    void Jan17_2001Test(FxRobot robot) {
        TableView<NatTeam> ttb =  robot.lookup("#rankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> mcb = robot.lookup("#datesFilter").queryAs(ComboBox.class);
        assertEquals(mcb.getItems().size(),319);
        assertEquals(mcb.getItems().get(238), "17 Jan 2001");
        robot.interact(() -> mcb.getSelectionModel().select(238));
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size() != 0);
        assertEquals(ttb.getItems().get(8).getCountryCode(),"YUG");
        assertEquals(ttb.getItems().get(8).getTotalPoints(),707);
    }

    @Test
    void continentsComboBoxTests(FxRobot robot) {
        TableView<NatTeam> ttb =  robot.lookup("#rankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> cbb = robot.lookup("#continentFilter").queryAs(ComboBox.class);
        assertEquals(cbb.getItems().size(),7);
        int currtabsize = ttb.getItems().size();
        robot.interact(() -> cbb.getSelectionModel().select(0));
        assertEquals(currtabsize,ttb.getItems().size());
        for(int i = 1; i < 7; i++){
            final int k = i;
            robot.interact(() -> cbb.getSelectionModel().select(k));
            for(int j = 0; j < ttb.getItems().size(); j++){
                assertEquals(ttb.getItems().get(j).getLocation(),cbb.getItems().get(i));
            }
        }
    }

}
