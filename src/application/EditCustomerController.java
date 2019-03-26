package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Controller;

public class EditCustomerController {

	private Controller controller;
	private Customer customer;

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
	private Button editButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label warningLabel;

	public void initialize() {
		danceChoiceBox.setItems(FXCollections.observableArrayList("Novice", "Intermediate", "Advanced"));
	}

	@FXML
	void cancelButton_click(ActionEvent event) {
		closeStage(event);
	}

	@FXML
	void editButton_click(ActionEvent event) {
		if (!nameField.getText().isEmpty() && !danceChoiceBox.getValue().isEmpty()) {
			customer.setName(nameField.getText());
			customer.setPersNr(personalField.getText());
			customer.setEmail(emailField.getText());
			customer.setPhoneNr(phoneField.getText());
			customer.setAddress(addressField.getText());
			// customer.setAllergies(allergiesField.getText());
			customer.setDanceSkill(danceChoiceBox.getValue());
			
			closeStage(event);
		} else {
			warningLabel.setText("Name and dance skill is required");
		}
	}

	public void init(Controller contr, Customer c) {
			controller = contr;
			customer = c;
			warningLabel.setText(customer.getName() + " is selected");

			nameField.setText(customer.getName());
			personalField.setText(customer.getPersNr());
			emailField.setText(customer.getEmail());
			phoneField.setText(customer.getPhoneNr());
			addressField.setText(customer.getAddress());
			danceChoiceBox.setValue(customer.getDanceSkill());
			// allergiesField;
		}
	
	// Close stage and return to Controller
	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
}
