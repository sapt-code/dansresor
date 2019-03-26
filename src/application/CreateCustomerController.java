package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCustomerController {

	private Controller controller;

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
	private ChoiceBox<String> danceChoiceBox;
	@FXML
	private Button createButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label warningLabel;

	public void initialize() {
		danceChoiceBox.setItems(FXCollections.observableArrayList("Novice", "Intermediate", "Advanced"));
	}

	@FXML
	void cancelButton_click(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void createButton_click(ActionEvent event) {
		if (nameField.getText().isEmpty()  || danceChoiceBox.getValue()==null) {
			warningLabel.setText("Name and dance skill is required");
			
		} else {
			
			String name = nameField.getText().trim();
			String personal = personalField.getText().trim();//Bara skriva personnummer?
			String email = emailField.getText().trim();
			String phone = phoneField.getText().trim();
			String address = addressField.getText().trim();
			//***ArrayList till allergies***
			ArrayList<String> allergies = new ArrayList<String>(Arrays.asList(allergiesField.getText().split("[,\\s]"))); 
			String danceSkill = danceChoiceBox.getValue();
			
			Customer c = controller.createCustomer(name, danceSkill);
			c.setPersNr(personal);
			c.setEmail(email);
			c.setPhoneNr(phone);
			c.setAddress(address);
			c.setAllergies(allergies);
			
			//Close
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}

	}

	public void init(Controller contr) {
		controller = contr;

	}

}
