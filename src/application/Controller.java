package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	int customerNr = 1;
	//Init reg
	Register register = new Register();
	
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
	
	@FXML public void initialize() {
		//Test data
		for (int i = 0; i < testNames.length; i++) {
			createCustomer(testNames[i], customerNr, "Novice", register);
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
		
		//Filter
		FilteredList<Customer> filteredCustomerList = new FilteredList<>(customerList, p -> true);
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredCustomerList.setPredicate(customer -> {
				if (newValue == null || newValue.isEmpty()) { //Sökfältet är tomt
					return true;
				} else { //Sökfältet inte tomt
					String lowerCaseFilter = newValue.toLowerCase();
					return customer.getCustomerNr().toLowerCase().contains(lowerCaseFilter) || customer.getName().toLowerCase().contains(lowerCaseFilter);
				}
			});
		});
		
		SortedList<Customer> sortedCustomerList = new SortedList<>(filteredCustomerList);
		sortedCustomerList.comparatorProperty().bind(customerTable.comparatorProperty());
		customerTable.setItems(sortedCustomerList);
		
		//Listener when a 
		customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			refreshButtons();
		});
	}
	
	//Buttons 
	@FXML public void deleteButton_click(ActionEvent e) {
		if (customerTable.getSelectionModel().getSelectedItem() != null) {
			//markerad kund tas bort
			Customer customer = customerTable.getSelectionModel().getSelectedItem();
			removeCustomer(customer, register);
		}
	}
	
	@FXML public void createButton_click(ActionEvent e) {
		
	}
	
	@FXML public void editButton_click(ActionEvent e) {
		
	}
	
	//Methods
	private void createCustomer(String name, int customerNbr, String danceSkill, Register reg) {
		Customer c = new Customer(name, String.format("%07d", customerNbr), danceSkill);
		customerNr++;
		reg.addCustomer(c);
		customerList.add(c);
		customerTable.refresh();
	}
	
	private void removeCustomer(Customer c, Register reg) {
		reg.removeCustomer(c);
		customerList.remove(c);
		customerTable.refresh();
	}
	
	private void refreshButtons() {
		if (customerTable.getSelectionModel().getSelectedItem() != null) {
			deleteButton.setDisable(false);
			editButton.setDisable(false);
		} else {
			deleteButton.setDisable(true);
			editButton.setDisable(true);
		}
	}
}
