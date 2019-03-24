package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
	@FXML private TextField searchField;
	@FXML private Button createButton;
	@FXML private Button editButton;
	@FXML private Button deleteButton;
	//Table variables
	@FXML private TableView<Customer> customerTable;
	@FXML private TableColumn<Customer, String> customerNrCol;
	@FXML private TableColumn<Customer, String> customerNameCol;
	@FXML private TableColumn<Customer, String> customerPersNrCol;
	@FXML private TableColumn<Customer, String> customerEmailCol;
	@FXML private TableColumn<Customer, String> customerTelCol;
	@FXML private TableColumn<Customer, String> customerAddressCol;
	@FXML private TableColumn<Customer, String> customerAllergiesCol;
	@FXML private TableColumn<Customer, String> customerDanceSkillCol;
	
	//Other variables
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();;
	int customerNr = 1;
	
	//Test variables
	String[] testNames = {"Irma Guice",
							"Cecil Helper",
							"Jaunita Morrow",
							"Lucina Seymour",  
							"Roberta Beecroft",
							"Edmund Pershall",
							"Brenna Mistretta",
							"Kaleigh Lacayo",
							"Monserrate Goforth",
							"Clemencia Duffey"};
	
	@FXML public void initialize( ) {
		//Init reg
		Register register = new Register();
		
		//Test data
		for (int i = 0; i < testNames.length; i++) {
			createCustomer(testNames[i], Integer.toString(customerNr), "Novice", register);
		}
		
		//Init table
		customerNrCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerNr"));
		customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		customerPersNrCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("persNr"));
		customerEmailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		customerTelCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNr"));
		customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		customerAllergiesCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("allergies"));
		customerDanceSkillCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("danceSkill"));
		customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//Set table
		customerTable.setItems(customerList);
	}
	
	//Methods
	private void createCustomer(String name, String customerNbr, String danceSkill, Register register) {
		Customer c = new Customer(name, customerNbr, danceSkill);
		customerNr++;
		register.addCustomer(c);
		customerList = FXCollections.observableArrayList(register.getCustomers());
	}
}