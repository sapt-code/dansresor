package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Controller;

public class CreateCustomerController {
	
	private Controller controller;

    @FXML private TextField nameField;
    @FXML private TextField personalField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextField allergiesField;
    @FXML private ChoiceBox<String> danceChoiceBox;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private Label warningLabel;
    
    public void initialize() {
        danceChoiceBox.setItems(FXCollections.observableArrayList("Novice", "Intermediate", "Advanced"));
    }
    
    
    @FXML void cancelButton_click(ActionEvent event) {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void createButton_click(ActionEvent event) {
    	if (!nameField.getText().isEmpty() && !danceChoiceBox.getValue().isEmpty()) {
    		controller.createCustomer("123", "344");
    	} else {
    		warningLabel.setText("Name and dance skill is required");
    	}
    	
    	
    	
    }

	public void init(Controller contr) {
		controller = contr;
		
	}

}
