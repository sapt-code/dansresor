package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
	@FXML private TextField searchField;
	@FXML private Button createButton;
	@FXML private Button editButton;
	@FXML private Button deleteButton;
	//Table
	@FXML private TableView<Customer> customerTable;
	@FXML private TableColumn<Customer, String> customerNameCol;
	@FXML private TableColumn<Customer, String> customerNrCol;
	@FXML private TableColumn<Customer, String> customerPersNrCol;
	@FXML private TableColumn<Customer, String> customerEmailCol;
	@FXML private TableColumn<Customer, String> customerTelCol;
	@FXML private TableColumn<Customer, String> customerAddressCol;
	@FXML private TableColumn<Customer, String> customerAllergiesCol;
	@FXML private TableColumn<Customer, String> customerCol;
}
