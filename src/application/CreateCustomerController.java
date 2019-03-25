package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreateCustomerController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField personalField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField allergiesField;

    @FXML
    private ChoiceBox<?> danceChoiceBox;

    @FXML
    private Button createButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancelButton_click(ActionEvent event) {

    }

    @FXML
    void createButton_click(ActionEvent event) {

    }

}
