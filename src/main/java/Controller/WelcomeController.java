package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeController {
    @FXML
    private TextField player1;
    private TextField player2;

    @FXML
    private Label errorLabel;

    @FXML
    private void switchToPrimary() throws IOException {
        //App.setRoot("primary");
    }
}
