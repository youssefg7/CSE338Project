package GUI;

import com.example.cse338project.classes.NatTeam;
import com.example.cse338project.classes.UefTeam;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class RankTableUefGuiTesting {


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
     and UEFA's official website
     https://www.uefa.com/nationalassociations/uefarankings/club/
     */
    @Test
    void _2017Test(FxRobot robot) {
        robot.clickOn("#uefTab");
        TableView<UefTeam> ttb =  robot.lookup("#UefarankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> mcb = robot.lookup("#yearFilter").queryAs(ComboBox.class);
        assertEquals(mcb.getItems().size(),18);
        assertEquals(mcb.getItems().get(5), "2017");
        robot.interact(() -> mcb.getSelectionModel().select(5));
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size() != 0);
        assertEquals(ttb.getItems().get(0).getName(),"Real Madrid CF");
        assertEquals(ttb.getItems().get(0).getTotalPoints(),"156.000");
    }

    /**
     * This Test will always Fail due to a thread error that can be disregarded.
     *      IF THE TEST BRINGS UP THE '!' SIGN THEN THE TESTS PASSED
     *      IF THE TEST BRINGS UP THE 'X' SIGN THEN THE TESTS FAILED
     The Following Results are Comparisons between the program
     and UEFA's official website
     https://www.uefa.com/nationalassociations/uefarankings/club/
     */
    @Test
    void _2010Test(FxRobot robot) {
        robot.clickOn("#uefTab");
        TableView<UefTeam> ttb =  robot.lookup("#UefarankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> mcb = robot.lookup("#yearFilter").queryAs(ComboBox.class);
        assertEquals(mcb.getItems().size(),18);
        assertEquals(mcb.getItems().get(12), "2010");
        robot.interact(() -> mcb.getSelectionModel().select(12));
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size() != 0);
        assertEquals(ttb.getItems().get(0).getName(),"FC Barcelona");
        assertEquals(ttb.getItems().get(0).getTotalPoints(),"121.000");
    }

    /**
     * This Test will always Fail due to a thread error that can be disregarded.
     *      IF THE TEST BRINGS UP THE '!' SIGN THEN THE TESTS PASSED
     *      IF THE TEST BRINGS UP THE 'X' SIGN THEN THE TESTS FAILED
     The Following Results are Comparisons between the program
     and UEFA's official website
     https://www.uefa.com/nationalassociations/uefarankings/club/
     */
    @Test
    void _2005Test(FxRobot robot) {
        robot.clickOn("#uefTab");
        TableView<UefTeam> ttb =  robot.lookup("#UefarankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> mcb = robot.lookup("#yearFilter").queryAs(ComboBox.class);
        assertEquals(mcb.getItems().size(),18);
        assertEquals(mcb.getItems().get(17), "2005");
        robot.interact(() -> mcb.getSelectionModel().select(17));
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size() != 0);
        assertEquals(ttb.getItems().get(1).getName(),"Valencia CF");
        assertEquals(ttb.getItems().get(1).getTotalPoints(),"99.000");
    }

    @Test
    void countriesComboBoxTests(FxRobot robot) {
        robot.clickOn("#uefTab");
        TableView<UefTeam> ttb =  robot.lookup("#UefarankingTable").queryAs(TableView.class);
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> ttb.getItems().size()!=0);
        ComboBox<String> cbb = robot.lookup("#countryFilter").queryAs(ComboBox.class);
        assertEquals(cbb.getItems().size(),56);
        int currtabsize = ttb.getItems().size();
        robot.interact(() -> cbb.getSelectionModel().select(0));
        assertEquals(currtabsize,ttb.getItems().size());
        for(int i = 1; i < 56; i++){
            final int k = i;
            robot.interact(() -> cbb.getSelectionModel().select(k));
            for(int j = 0; j < ttb.getItems().size(); j++){
                assertEquals(ttb.getItems().get(j).getCountry(),cbb.getItems().get(i));
            }
        }
    }

}
