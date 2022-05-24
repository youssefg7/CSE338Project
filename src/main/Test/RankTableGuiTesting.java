import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@ExtendWith(ApplicationExtension.class)
public class RankTableGuiTesting {


    @Start
    private void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RankTable.fxml")))));
        stage.show();
    }

    @Test
    void Running(FxRobot robot) {
        Awaitility.await().atMost(2, TimeUnit.MINUTES).until(() -> robot.lookup("#rankingTable").queryAs(TableView.class).getItems().size()!=0);
    }

    @Test
    void continentsComboBoxTests(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#continentFilter").queryAs(ComboBox.class)).hasExactlyNumItems(7);
    }


    /**
     * @param robot - Will be injected by the test runner.
     */
//    @Test
//    void should_contain_button_with_text(FxRobot robot) {
//        Assertions.assertThat(button).hasText("click me!");
//        // or (lookup by css id):
//        Assertions.assertThat(robot.lookup("#myButton").queryAs(Button.class)).hasText("click me!");
//        // or (lookup by css class):
//        Assertions.assertThat(robot.lookup(".button").queryAs(Button.class)).hasText("click me!");
//        // or (query specific type):
//        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("click me!");
//    }

    /**
     * @param robot - Will be injected by the test runner.
     */
//    @Test
//    void when_button_is_clicked_text_changes(FxRobot robot) {
//        // when:
//        robot.clickOn(".button");
//
//        // then:
//        Assertions.assertThat(button).hasText("clicked!");
//        // or (lookup by css id):
//        Assertions.assertThat(robot.lookup("#myButton").queryAs(Button.class)).hasText("clicked!");
//        // or (lookup by css class):
//        Assertions.assertThat(robot.lookup(".button").queryAs(Button.class)).hasText("clicked!");
//        // or (query specific type)
//        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("clicked!");
//    }
}
