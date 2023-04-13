package kontaktlisteapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class nyTest extends ApplicationTest {
    private nyTest nytest;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        AnchorPane root = loader.load();
        nytest = loader.getController();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> new KontaktlisteApp());
    }

    @Test
    public void testContactListEmptyWhenNoContactsLoaded() {
        TableView<Contact> tableView = nytest.getTableView();
        assertEquals(0, tableView.getItems().size(), "Contact list should be empty when no contacts are loaded.");
    }

    // Add more test methods for other functionalities here
}