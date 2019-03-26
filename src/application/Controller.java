package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private TextField searchField;
	@FXML
	private Button createButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button skillSortButton;
	@FXML
	private Button copyButton;
	@FXML
	private ComboBox<String> skillComboBox;
	// Table variables
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, String> customerNrCol;
	@FXML
	private TableColumn<Customer, String> customerNameCol;
	@FXML
	private TableColumn<Customer, String> customerPersNrCol;
	@FXML
	private TableColumn<Customer, String> customerEmailCol;
	@FXML
	private TableColumn<Customer, String> customerTelCol;
	@FXML
	private TableColumn<Customer, String> customerAddressCol;
	@FXML
	private TableColumn<Customer, String> customerAllergiesCol;
	@FXML
	private TableColumn<Customer, String> customerDanceSkillCol;

	// Other variables
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	int customerNr = 1;

	// Init reg
	Register register = new Register();

	// Test variables
	String[] testNames = { "Irma Guice", "Cecil Helper", "Jaunita Morrow", "Lucina Seymour", "Roberta Beecroft",
			"Edmund Pershall", "Brenna Mistretta", "Kaleigh Lacayo", "Monserrate Goforth", "Clemencia Duffey" };
	String[] emailAddress = {"a@abc.se","b@abc.se",  "c@abc.se",  "d@abc.se",  "e@abc.se",  "f@abc.se",  "g@abc.se",  "h@abc.se",  "i@abc.se",  "j@abc.se"};
	String[] phoneNumbers = {"0712345670","0712345679",  "0712345678",  "0712345677",  "0712345676",  "0712345675",  "0712345674",  "0712345673",  "0712345672",  "0712345671"};
	String[] adresses = {"1","2",  "3",  "4",  "5",  "6",  "7",  "8",  "9",  "10"};
	String[] allergies = {"peanut", "lactose", "dogs", "vaccines"};
	String[] personalTest = {"500417", "500417", "500417", "500417", "500417", "500417", "500417", "500417", "500417", "500417"};
	
	@FXML
	public void initialize() {
		// Test data
		for (int i = 0; i < testNames.length; i++) {
			if (i <= testNames.length / 3) {
				Customer c = createCustomer(testNames[i], "Novice");
				c.setEmail(emailAddress[i]);
				c.setPhoneNr(phoneNumbers[i]);
				c.setAddress(adresses[i]);
				c.getArrayListAllergies().add(allergies[0]);
				c.getArrayListAllergies().add(allergies[1]);
				c.setPersNr(personalTest[i]);
			} else if (i <= testNames.length / 3 * 2) {
				Customer c = createCustomer(testNames[i], "Intermediate");
				c.setEmail(emailAddress[i]);
				c.setPhoneNr(phoneNumbers[i]);
				c.setAddress(adresses[i]);
				c.setPersNr(personalTest[i]);
				
			} else {
				Customer c = createCustomer(testNames[i], "Advanced");
				c.setEmail(emailAddress[i]);
				c.setPhoneNr(phoneNumbers[i]);
				c.setAddress(adresses[i]);
				c.getArrayListAllergies().add(allergies[3]);
				c.setPersNr(personalTest[i]);
			}
			
		}

		// Set ComboBox
		skillComboBox.setItems(FXCollections.observableArrayList("Novice", "Intermediate", "Advanced", "All"));

		// Init table
		customerNrCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerNr"));
		customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		customerPersNrCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("persNr"));
		customerEmailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		customerTelCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNr"));
		customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		customerAllergiesCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("allergies"));
		customerDanceSkillCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("danceSkill"));
		customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Set table
		customerTable.setItems(customerList);

		// Filter
		FilteredList<Customer> filteredCustomerList = new FilteredList<>(customerList, p -> true);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredCustomerList.setPredicate(customer -> {
				if (newValue == null || newValue.isEmpty()) { // Sökfältet är tomt
					return true;
				} else { // Sökfältet inte tomt
					String lowerCaseFilter = newValue.toLowerCase();
					return customer.getCustomerNr().toLowerCase().contains(lowerCaseFilter)
							|| customer.getName().toLowerCase().contains(lowerCaseFilter);
				}
			});
		});

		SortedList<Customer> sortedCustomerList = new SortedList<>(filteredCustomerList);
		sortedCustomerList.comparatorProperty().bind(customerTable.comparatorProperty());
		customerTable.setItems(sortedCustomerList);

		// Listener when a customer is selected
		customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			refreshButtons();
		});
	}

	// Buttons
	@FXML
	public void deleteButton_click(ActionEvent e) {
		if (customerTable.getSelectionModel().getSelectedItem() != null) {
			// markerad kund tas bort
			Customer customer = customerTable.getSelectionModel().getSelectedItem();
			removeCustomer(customer);
		}
	}

	@FXML
	public void createButton_click(ActionEvent e) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Create.fxml"));
			Parent parent = fxmlLoader.load();

			CreateCustomerController createCustomerController = fxmlLoader.<CreateCustomerController>getController();
			createCustomerController.init(this);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(parent));
			stage.setTitle("Create Customer");
			stage.centerOnScreen();
			stage.show();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	@FXML
	public void editButton_click(ActionEvent e) {
		if (customerTable.getSelectionModel().getSelectedItem() != null) {
			Customer c = customerTable.getSelectionModel().getSelectedItem();
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
				Parent parent = fxmlLoader.load();
				EditCustomerController editCustomerController = fxmlLoader.<EditCustomerController>getController();
				editCustomerController.init(this, c);

				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(parent));
				stage.setTitle("Edit Customer");
				stage.centerOnScreen();
				stage.showAndWait();
				customerTable.refresh();
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}

	@FXML
	public void sortDanceSkillButton_click(ActionEvent e) {
		ArrayList<Customer> skillSortedList = new ArrayList<Customer>();
		for (Customer c : register.getCustomers()) {
			if (c.getDanceSkill().equals(skillComboBox.getValue())) {
				skillSortedList.add(c);
			}
		}
		if (skillComboBox.getValue().equals("All")) {
			skillSortedList.addAll(register.getCustomers());
		}
		customerList.clear();
		customerTable.refresh();
		customerList.addAll(skillSortedList);
	}

	@FXML
	public void copyButton_click(ActionEvent e) {
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		String emails = "";

		for (Customer c : customerTable.getItems()) {
			emails += c.getEmail() + " ";
		}
		content.putString(emails);
		clipboard.setContent(content);
	}

//	public int getCustomerNr() {
//		return customerNr;
//	}
//
//	public Register getRegister() {
//		return register;
//	}

	// Methods
	public Customer createCustomer(String name, String danceSkill) {
		Customer c = new Customer(name, String.format("%07d", customerNr), danceSkill);
		customerNr++;
		register.addCustomer(c);
		customerList.add(c);
		customerTable.refresh();
		return c;
	}

	private void removeCustomer(Customer c) {
		register.removeCustomer(c);
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
