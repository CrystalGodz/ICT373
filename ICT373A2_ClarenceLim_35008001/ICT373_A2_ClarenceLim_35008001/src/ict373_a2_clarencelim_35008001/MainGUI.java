package ict373_a2_clarencelim_35008001;
import java.io.File;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainGUI{
    private GridPane root;
    private TextArea infoBox;
    private Stage primaryStage;
    private Button viewButton, createButton, editButton, createMagazineButton, loadMagazineButton, saveMagazineButton, addSupplementButton, editSupplementButton,
            deleteSupplementButton, addCustomerButton, editCustomerButton, deleteCustomerButton, submitButton, removeButton, addButton;
    private ListView<Supplement> supplementListView;
    private ListView<Customer> customerListView;
    private Label payingCustomerListLabel, paymentTypeLabel, accountLabel, selectedMagazineLabel;
    private TextField magazineNameTextField, magazineWeeklyCostTextField, supplementNameTextField, supplementWeeklyCostTextField, customerNameTextField,
            customerEmailTextField, streetNumberTextField, streetNameTextField, suburbTextField, postalCodeTextField, accountTextField;
    private ComboBox<String> magazineSelectionComboBox, customerTypeComboBox, paymentTypeComboBox;
    private ComboBox<Supplement> supplementListComboBox;
    private ComboBox<PayingCustomer> payingCustomerListComboBox;
    private List<File> selectedFile;
    
    public MainGUI(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    
    
    //define default GUI layout
    public void homePage(){
        this.root = new GridPane();
        this.root.setStyle("-fx-background-color: grey;");
        
        //define labels
        Label headerLabel = new Label("Weekly Programming Magazine");
        headerLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        
        //define buttons on home page
        this.viewButton = new Button("View");
        this.viewButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-style: italic;");
        this.viewButton.setPrefSize(80, 20);
        this.viewButton.setFocusTraversable(false);
        
        this.createButton = new Button("Create");
        this.createButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-style: italic;");
        this.createButton.setPrefSize(80, 20);
        this.createButton.setFocusTraversable(false);
        
        this.editButton = new Button("Edit");
        this.editButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-style: italic;");
        this.editButton.setPrefSize(80, 20);
        this.editButton.setFocusTraversable(false);
        
        this.submitButton = new Button("Submit");
        this.submitButton.setStyle("-fx-font-weight: bold;");
        this.submitButton.setPrefSize(100, 25);
        this.submitButton.setFocusTraversable(false);
        this.submitButton.setVisible(false);
        
        
        //layout of the tab
        this.root.setMinSize(560,800);
        this.root.setPadding(new Insets(10));
        this.root.setAlignment(Pos.TOP_CENTER);
        this.root.setHgap(10);
        this.root.setVgap(15);
        
        
        //define columns
        ColumnConstraints column1 = new ColumnConstraints(180);
        column1.setHalignment(HPos.CENTER);
        ColumnConstraints column2 = new ColumnConstraints(180);
        column2.setHalignment(HPos.CENTER);
        ColumnConstraints column3 = new ColumnConstraints(180);
        column3.setHalignment(HPos.CENTER);
        this.root.getColumnConstraints().addAll(column1, column2, column3);
        
        
        //define variables' position on GridPane
        this.root.add(headerLabel, 0, 0, 4, 1);
        this.root.add(this.viewButton, 0, 1, 1, 1);
        this.root.add(this.createButton, 1, 1, 1, 1);
        this.root.add(this.editButton, 2, 1, 1, 1);
        this.root.add(this.submitButton, 1, 22);
        
        
        //set the stage
        Scene scene = new Scene(root);
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Magazine Records Service");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    
    
    //define view magazine GUI layout
    public void viewSetup(){
        homePage();
        
        //supplement's ListView
        this.supplementListView = new ListView<>();
        this.supplementListView.setStyle("-fx-pref-height: 370px");
        
        
        //customer's ListView
        this.customerListView = new ListView<>();
        this.customerListView.setStyle("-fx-pref-height: 370px");
        
        
        //define labels
        this.selectedMagazineLabel = new Label("");
        this.selectedMagazineLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label infoLabel = new Label("Information Panel:");
        infoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label supplementLabel = new Label("List of Supplements:");
        supplementLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label customerLabel = new Label("List of Subscribed Customers:");
        customerLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        
        //set information display TextArea
        this.infoBox = new TextArea();
        this.infoBox.setStyle("-fx-pref-height: 780px");
        this.infoBox.setEditable(false);
        
        
        //define variables' position on GridPane
        this.root.add(this.selectedMagazineLabel, 0, 3, 4, 1);
        this.root.add(supplementLabel, 0, 4);
        this.root.add(this.supplementListView, 0, 5, 1, 12);
        this.root.add(customerLabel, 0, 17);
        this.root.add(this.customerListView, 0, 18, 1, 10);
        this.root.add(infoLabel, 1, 4);
        this.root.add(this.infoBox, 1, 5, 2, 23);
    }
    
    
    //define 'create' GUI layout
    public void createPage(){
        homePage();
        this.createButton.setDisable(true);
        Label createHeaderLabel = new Label("Select an option:");
        createHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        //define buttons
        this.createMagazineButton = new Button("Add New Magazine");
        this.createMagazineButton.setStyle("-fx-font-weight: bold;");
        this.createMagazineButton.setPrefSize(150, 20);
        this.createMagazineButton.setFocusTraversable(false);
        
        this.loadMagazineButton = new Button("Load Magazine");
        this.loadMagazineButton.setStyle("-fx-font-weight: bold;");
        this.loadMagazineButton.setPrefSize(150, 20);
        this.loadMagazineButton.setFocusTraversable(false);
        
        this.saveMagazineButton = new Button("Save Magazine");
        this.saveMagazineButton.setStyle("-fx-font-weight: bold;");
        this.saveMagazineButton.setPrefSize(150, 20);
        this.saveMagazineButton.setFocusTraversable(false);
        
        
        //define variables' position on GridPane
        this.root.add(createHeaderLabel, 0, 2, 4, 1);
        this.root.add(this.createMagazineButton, 1, 4);
        this.root.add(this.loadMagazineButton, 1, 6);
        this.root.add(this.saveMagazineButton, 1, 8);
    }
    
    
    //define add magazine GUI layout
    public void addMagazineSetup(){
        homePage();
        
        //define labels
        Label addMagazineHeaderLabel = new Label("Currently Adding New magazine");
        addMagazineHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label magazineNameLabel = new Label("Magazine's name:");
        magazineNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label magazineWeeklyCostLabel = new Label("Magazine's Weekly Cost:");
        magazineWeeklyCostLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        
        //define TextFields
        this.magazineNameTextField = new TextField();
        this.magazineWeeklyCostTextField = new TextField();
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(addMagazineHeaderLabel, 0, 2, 4, 1);
        this.root.add(magazineNameLabel, 0, 6);
        this.root.add(this.magazineNameTextField, 1, 6);
        this.root.add(magazineWeeklyCostLabel, 0, 8);
        this.root.add(this.magazineWeeklyCostTextField, 1, 8);
    }
    
    
    //method to load magazine from .ser file
    public void loadMagazineSetup(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Magazine File");
        
        //allow only '.ser' file to be chosen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized Files (*.ser)", "*.ser");
        chooser.getExtensionFilters().add(extFilter);
        
        Stage tempStage = new Stage();
        this.selectedFile = chooser.showOpenMultipleDialog(tempStage);
    }
    
    
    //method to save magazine to .ser file
    public void saveMagazineSetup(){
        homePage();
        
        //define labels
        Label saveMagazineHeaderLabel = new Label("Select Magazine to Save");
        saveMagazineHeaderLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        
        //define combobox to select magazine
        this.magazineSelectionComboBox = new ComboBox<>();
        this.magazineSelectionComboBox.setPromptText("Magazine Options.");
        this.magazineSelectionComboBox.setStyle("-fx-pref-width: 180px");
        this.magazineSelectionComboBox.setFocusTraversable(false);
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(saveMagazineHeaderLabel, 0, 3, 4, 1);
        this.root.add(this.magazineSelectionComboBox, 1, 4);
    }
    
    
    //get methods to recieve inputs from user for magazine details
    public TextField getMagazineName(){
        return this.magazineNameTextField;
    }
    
    public TextField getMagazineWeeklyCost(){
        return this.magazineWeeklyCostTextField;
    }
    
    
    //define edit magazine GUI layout
    public void editSetup(){
        homePage();
        
        //define labels
        Label editHeaderLabel = new Label("Select an action");
        editHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        this.selectedMagazineLabel = new Label("");
        this.selectedMagazineLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        
        //define buttons
        this.addSupplementButton = new Button("Add New Supplement");
        this.addSupplementButton.setStyle("-fx-font-weight: bold;");
        this.addSupplementButton.setPrefSize(150, 30);
        this.addSupplementButton.setFocusTraversable(false);
        
        this.editSupplementButton = new Button("Edit Supplement's Info");
        this.editSupplementButton.setStyle("-fx-font-weight: bold;");
        this.editSupplementButton.setPrefSize(150, 30);
        this.editSupplementButton.setFocusTraversable(false);
        
        this.deleteSupplementButton = new Button("Delete Supplement");
        this.deleteSupplementButton.setStyle("-fx-font-weight: bold;");
        this.deleteSupplementButton.setPrefSize(150, 30);
        this.deleteSupplementButton.setFocusTraversable(false);
        
        this.addCustomerButton = new Button("Add New Subscriber");
        this.addCustomerButton.setStyle("-fx-font-weight: bold;");
        this.addCustomerButton.setPrefSize(150, 30);
        this.addCustomerButton.setFocusTraversable(false);
        
        this.editCustomerButton = new Button("Edit Customer's Info");
        this.editCustomerButton.setStyle("-fx-font-weight: bold;");
        this.editCustomerButton.setPrefSize(150, 30);
        this.editCustomerButton.setFocusTraversable(false);
        
        this.deleteCustomerButton = new Button("Delete Subscriber");
        this.deleteCustomerButton.setStyle("-fx-font-weight: bold;");
        this.deleteCustomerButton.setPrefSize(150, 30);
        this.deleteCustomerButton.setFocusTraversable(false);
        
        
        //define variables' position on GridPane
        this.root.add(editHeaderLabel, 0, 2, 4, 1);
        this.root.add(this.selectedMagazineLabel, 0, 3, 4, 1);
        this.root.add(this.addSupplementButton, 0, 4, 1, 2);
        this.root.add(this.addCustomerButton, 2, 4, 1, 2);
        this.root.add(this.editSupplementButton, 0, 6, 1, 2);
        this.root.add(this.editCustomerButton, 2, 6, 1, 2);
        this.root.add(this.deleteSupplementButton, 0, 8, 1, 2);
        this.root.add(this.deleteCustomerButton, 2, 8, 1, 2);
    }
    
    
    //define add supplement GUI layout
    public void addSupplementSetup(){
        homePage();
        
        //define labels
        Label addSupplementHeaderLabel = new Label("Currently Adding New Supplement");
        addSupplementHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label supplementNameLabel = new Label("Supplement's Name:");
        supplementNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label supplementWeeklyCostLabel = new Label("Supplement's Weekly Cost:");
        supplementWeeklyCostLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        
        //define TextFields
        this.supplementNameTextField = new TextField();
        this.supplementWeeklyCostTextField = new TextField();
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(addSupplementHeaderLabel, 0, 2, 4, 1);
        this.root.add(supplementNameLabel, 0, 4);
        this.root.add(this.supplementNameTextField, 1, 4);
        this.root.add(supplementWeeklyCostLabel, 0, 6);
        this.root.add(this.supplementWeeklyCostTextField, 1, 6);
    }
    
    
    //define add customer GUI layout
    public void addCustomerSetup(){
        homePage();
        
        //define labels
        Label addCustomerHeaderLabel = new Label("Currently Adding New Customer");
        addCustomerHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label customerTypeLabel = new Label("Customer's Type:");
        customerTypeLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerNameLabel = new Label("Customer's Name:");
        customerNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerEmailLabel = new Label("Email:");
        customerEmailLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerStreetNumberLabel = new Label("Street Number");
        customerStreetNumberLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label cutomerStreetNameLabel = new Label("Street Name:");
        cutomerStreetNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerSuburbLabel = new Label("Suburb:");
        customerSuburbLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerPostalCodeLabel = new Label("Postal Code:");
        customerPostalCodeLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label supplementListLabel = new Label("Select a Supplement to Subscribe:");
        supplementListLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        this.payingCustomerListLabel = new Label("Select Paying Customer:");
        this.payingCustomerListLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        this.payingCustomerListLabel.setVisible(false);
        
        this.paymentTypeLabel = new Label("Select Payment Type:");
        this.paymentTypeLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        this.paymentTypeLabel.setVisible(false);
        
        this.accountLabel = new Label("Card/Bank Number:");
        this.accountLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        this.accountLabel.setVisible(false);
        
        
        //define combo boxes
        this.customerTypeComboBox = new ComboBox<>();
        this.customerTypeComboBox.setPromptText("Customer Options.");
        this.customerTypeComboBox.setStyle("-fx-pref-width: 180px");
        
        this.supplementListComboBox = new ComboBox<>();
        this.supplementListComboBox.setPromptText("Supplement Options.");
        this.supplementListComboBox.setStyle("-fx-pref-width: 180px");
        
        this.payingCustomerListComboBox = new ComboBox<>();
        this.payingCustomerListComboBox.setPromptText("");
        this.payingCustomerListComboBox.setStyle("-fx-pref-width: 180px");
        this.payingCustomerListComboBox.setVisible(false);
        
        this.paymentTypeComboBox = new ComboBox<>();
        this.paymentTypeComboBox.setPromptText("Payment Options.");
        this.paymentTypeComboBox.getItems().addAll("Visa", "Mastercard", "American Express", "OCBC", "DBS", "POSB", "UOB");
        this.paymentTypeComboBox.setStyle("-fx-pref-width: 180px");
        this.paymentTypeComboBox.setVisible(false);
        
        
        //define TextFields
        this.customerNameTextField = new TextField();
        this.customerEmailTextField = new TextField();
        this.streetNumberTextField = new TextField();
        this.streetNameTextField = new TextField();
        this.suburbTextField = new TextField();
        this.postalCodeTextField = new TextField();
        
        this.accountTextField = new TextField();
        this.accountTextField.setVisible(false);
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(addCustomerHeaderLabel, 0, 2, 4, 1);
        
        this.root.add(customerTypeLabel, 2, 4);
        this.root.add(this.customerTypeComboBox, 2, 5);
        
        this.root.add(customerNameLabel, 0, 5);
        this.root.add(this.customerNameTextField, 1, 5);
        
        this.root.add(customerEmailLabel, 0, 6);
        this.root.add(this.customerEmailTextField, 1, 6);
        
        this.root.add(supplementListLabel, 2, 6);
        this.root.add(this.supplementListComboBox, 2, 7);
        
        this.root.add(customerStreetNumberLabel, 0, 7);
        this.root.add(this.streetNumberTextField, 1, 7);
        
        this.root.add(cutomerStreetNameLabel, 0, 8);
        this.root.add(this.streetNameTextField, 1, 8);
        
        this.root.add(customerSuburbLabel, 0, 9);
        this.root.add(this.suburbTextField, 1, 9);
        
        this.root.add(customerPostalCodeLabel, 0, 10);
        this.root.add(this.postalCodeTextField, 1, 10);
        
        this.root.add(this.paymentTypeLabel, 2, 11);
        this.root.add(this.paymentTypeComboBox, 2, 12);
        this.root.add(this.accountLabel, 0, 12);
        this.root.add(this.accountTextField, 1, 12);
        
        this.root.add(this.payingCustomerListLabel, 0, 12);
        this.root.add(this.payingCustomerListComboBox, 1, 12);
    }
    
    
    //define edit supplement GUI layout
    public void editSupplementSetup(){
        homePage();
        
        //define labels
        Label editSupplementHeaderLabel = new Label("Currently Editing Supplements");
        editSupplementHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label editSupplementLabel = new Label("Supplement Selection:");
        editSupplementLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label supplementNameLabel = new Label("Selected Supplement's Name:");
        supplementNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label supplementWeeklyCostLabel = new Label("Selected Supplement's Weekly Cost:");
        supplementWeeklyCostLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        
        //define TextFields
        this.supplementNameTextField = new TextField();
        //disabled editing to prevent UID duplication
        this.supplementNameTextField.setDisable(true);
        this.supplementWeeklyCostTextField = new TextField();
        
        
        //define supplement's ListView for user selection
        this.supplementListView = new ListView<>();
        this.supplementListView.setStyle("-fx-pref-height: 300px");
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(editSupplementHeaderLabel, 0, 2, 4, 1);
        this.root.add(editSupplementLabel, 2, 3);
        this.root.add(this.supplementListView, 2, 4, 1, 16);
        this.root.add(supplementNameLabel, 0, 4);
        this.root.add(this.supplementNameTextField, 1, 4);
        this.root.add(supplementWeeklyCostLabel, 0, 6);
        this.root.add(this.supplementWeeklyCostTextField, 1, 6);
    }
    
    
    //define edit customer GUI layout
    public void editCustomerSetup(){
        homePage();
        
        //define labels
        Label editCustomerHeaderLabel = new Label("Currently Editing Customers");
        editCustomerHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label customerTypeLabel = new Label("Customer's Type:");
        customerTypeLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerNameLabel = new Label("Customer's Name:");
        customerNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerEmailLabel = new Label("Email:");
        customerEmailLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerStreetNumberLabel = new Label("Street Number");
        customerStreetNumberLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label cutomerStreetNameLabel = new Label("Street Name:");
        cutomerStreetNameLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerSuburbLabel = new Label("Suburb:");
        customerSuburbLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerPostalCodeLabel = new Label("Postal Code:");
        customerPostalCodeLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label customerSelectionLabel = new Label("Customer Selection:");
        customerSelectionLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label supplementListLabel = new Label("Current Supplements:");
        supplementListLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label supplementComboBoxLabel = new Label("Add Supplement Selection:");
        supplementComboBoxLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        this.payingCustomerListLabel = new Label("Select Paying Customer:");
        this.payingCustomerListLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        this.payingCustomerListLabel.setVisible(false);
        
        this.paymentTypeLabel = new Label("Select Payment Type:");
        this.paymentTypeLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        this.paymentTypeLabel.setVisible(false);
        
        this.accountLabel = new Label("Card/Bank Number:");
        this.accountLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        this.accountLabel.setVisible(false);
        
        
        //define combo boxes
        this.customerTypeComboBox = new ComboBox();
        this.customerTypeComboBox.setPromptText("");
        this.customerTypeComboBox.getItems().addAll("Paying Customer", "Associate");
        this.customerTypeComboBox.setStyle("-fx-pref-width: 180px");
        //disabled customer type assuming that customer cannot change types
        this.customerTypeComboBox.setFocusTraversable(false);
        this.customerTypeComboBox.setDisable(true);
        
        this.payingCustomerListComboBox = new ComboBox<>();
        this.payingCustomerListComboBox.setPromptText("");
        this.payingCustomerListComboBox.setStyle("-fx-pref-width: 180px");
        this.payingCustomerListComboBox.setVisible(false);
        
        this.paymentTypeComboBox = new ComboBox<>();
        this.paymentTypeComboBox.setPromptText("Payment Options.");
        this.paymentTypeComboBox.getItems().addAll("Visa", "Mastercard", "American Express", "OCBC", "DBS", "POSB", "UOB");
        this.paymentTypeComboBox.setStyle("-fx-pref-width: 180px");
        this.paymentTypeComboBox.setVisible(false);
        
        this.supplementListComboBox = new ComboBox<>();
        this.supplementListComboBox.setPromptText("Supplement Options.");
        this.supplementListComboBox.setStyle("-fx-pref-width: 180px");
        
        
        //define TextFields
        this.customerNameTextField = new TextField();
        this.customerEmailTextField = new TextField();
        //disabled editing to prevent UID duplication
        this.customerEmailTextField.setDisable(true);
        this.streetNumberTextField = new TextField();
        this.streetNameTextField = new TextField();
        this.suburbTextField = new TextField();
        this.postalCodeTextField = new TextField();
        
        this.accountTextField = new TextField();
        this.accountTextField.setVisible(false);
        
        
        //define customer's ListView for user selection
        this.customerListView = new ListView<>();
        this.customerListView.setStyle("-fx-pref-height: 200px");
        
        this.supplementListView = new ListView<>();
        this.supplementListView.setStyle("-fx-pref-height: 100px");
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        this.removeButton = new Button("Remove Supplement");
        this.removeButton.setStyle("-fx-font-weight: bold;");
        this.removeButton.setPrefSize(100, 25);
        this.removeButton.setFocusTraversable(false);
        
        this.addButton = new Button("Add Supplement");
        this.addButton.setStyle("-fx-font-weight: bold;");
        this.addButton.setPrefSize(100, 25);
        this.addButton.setFocusTraversable(false);
        
        
        //define variables' position on GridPane
        this.root.add(editCustomerHeaderLabel, 0, 2, 4, 1);
        
        this.root.add(customerTypeLabel, 0, 4);
        this.root.add(this.customerTypeComboBox, 1, 4);
        
        this.root.add(customerSelectionLabel, 2, 3);
        this.root.add(this.customerListView, 2, 4, 1, 6);
        
        this.root.add(customerNameLabel, 0, 5);
        this.root.add(customerNameTextField, 1, 5);
        
        this.root.add(customerEmailLabel, 0, 6);
        this.root.add(this.customerEmailTextField, 1, 6);
        
        this.root.add(customerStreetNumberLabel, 0, 7);
        this.root.add(this.streetNumberTextField, 1, 7);
        
        this.root.add(cutomerStreetNameLabel, 0, 8);
        this.root.add(this.streetNameTextField, 1, 8);
        
        this.root.add(customerSuburbLabel, 0, 9);
        this.root.add(this.suburbTextField, 1, 9);
        
        this.root.add(customerPostalCodeLabel, 0, 10);
        this.root.add(this.postalCodeTextField, 1, 10);
        
        this.root.add(this.paymentTypeLabel, 2, 11);
        this.root.add(this.paymentTypeComboBox, 2, 12);
        this.root.add(this.accountLabel, 0, 12);
        this.root.add(this.accountTextField, 1, 12);
        
        this.root.add(this.payingCustomerListLabel, 0, 12);
        this.root.add(this.payingCustomerListComboBox, 1, 12);
        
        this.root.add(supplementListLabel, 0, 13);
        this.root.add(this.supplementListView, 0, 14, 1, 3);
        this.root.add(supplementComboBoxLabel, 1, 15);
        this.root.add(supplementListComboBox, 1, 16);
        this.root.add(this.removeButton,0, 17);
        this.root.add(this.addButton,1, 17);
    }
    
    
    //get methods for remove and add button
    public Button getRemoveButton(){
        return this.removeButton;
    }
    
    public Button getAddButton(){
        return this.addButton;
    }
    
    
    //define delete supplement GUI layout
    public void deleteSupplementSetup(){
        homePage();
        
        //define labels
        Label deleteSupplementHeaderLabel = new Label("Currently Deleting Supplement");
        deleteSupplementHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label editSupplementLabel = new Label("Select Supplement you wish to delete below:");
        editSupplementLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label infoLabel = new Label("Supplement's Details:");
        infoLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        
        //set information display TextArea
        this.infoBox = new TextArea();
        this.infoBox.setEditable(false);
        
        
        //define supplement's ListView for user selection
        this.supplementListView = new ListView<>();
        this.supplementListView.setStyle("-fx-pref-height: 400px");
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(deleteSupplementHeaderLabel, 0, 2, 4, 1);
        this.root.add(editSupplementLabel, 0, 4);
        this.root.add(supplementListView, 0, 5, 1, 16);
        this.root.add(infoLabel, 1, 4);
        this.root.add(infoBox, 1, 5, 2, 16);
    }
    
    
    //get methods to recieve inputs from user for supplement details
    public TextField getSupplementNameTextField(){
        return this.supplementNameTextField;
    }
    
    public TextField getSupplementWeeklyCostTextField(){
        return this.supplementWeeklyCostTextField;
    }
    
    
    //define delete customer GUI layout
    public void deleteCustomerSetup(){
        homePage();
        
        //define labels
        Label deleteCustomerHeaderLabel = new Label("Currently Deleting Customer");
        deleteCustomerHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        Label editCustomerLabel = new Label("Select Customer you wish to delete below:");
        editCustomerLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        Label infoLabel = new Label("Customer's Details:");
        infoLabel.setStyle("-fx-font-size: 12px; -fx-wrap-text: true;");
        
        
        //set information display TextArea
        this.infoBox = new TextArea();
        this.infoBox.setEditable(false);
        
        
        //define customer's ListView for user selection
        this.customerListView = new ListView<>();
        this.customerListView.setStyle("-fx-pref-height: 400px");
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(deleteCustomerHeaderLabel, 0, 2, 4, 1);
        this.root.add(editCustomerLabel, 0, 4);
        this.root.add(this.customerListView, 0, 5, 1, 16);
        this.root.add(infoLabel, 1, 4);
        this.root.add(this.infoBox, 1, 5, 2, 16);
    }
    
    
    //get methods to recieve inputs from user for customer details
    public TextField getCustomerName(){
        return this.customerNameTextField;
    }
    
    public TextField getCustomerEmail(){
        return this.customerEmailTextField;
    }
    
    public TextField getCustomerStreetNumber(){
        return this.streetNumberTextField;
    }
    
    public TextField getCustomerStreetName(){
        return this.streetNameTextField;
    }
    
    public TextField getCustomerSuburb(){
        return this.suburbTextField;
    }
    
    public TextField getCustomerPostalCode(){
        return this.postalCodeTextField;
    }
    
    public TextField getAccountNumberTextField(){
        return this.accountTextField;
    }
    
    
    //define magazine view selection GUI Layout
    public void magazineSelectionSetup(){
        homePage();
        
        //define labels
        Label magazineSelectionHeaderLabel = new Label("Select a Magazine to start.");
        magazineSelectionHeaderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true;");
        
        
        //define ComboBox
        this.magazineSelectionComboBox = new ComboBox<>();
        this.magazineSelectionComboBox.setPromptText("Magazine Options.");
        this.magazineSelectionComboBox.setStyle("-fx-pref-width: 180px");
        
        
        //define buttons
        this.submitButton.setVisible(true);
        
        
        //define variables' position on GridPane
        this.root.add(magazineSelectionHeaderLabel, 0, 2, 4, 1);
        this.root.add(this.magazineSelectionComboBox, 1, 4);
    }
    
    
    //define get method for buttons
    public Button getViewButton(){
        return this.viewButton;
    }
    
    public Button getCreateButton(){
        return this.createButton;
    }
    
    public Button getEditButton(){
        return this.editButton;
    }
    
    public Button getCreateMagazineButton(){
        return this.createMagazineButton;
    }
    
    public Button getLoadMagazineButton(){
        return this.loadMagazineButton;
    }
    
    public Button getSaveMagazineButton(){
        return this.saveMagazineButton;
    }
    
    public Button getAddSupplementButton(){
        return this.addSupplementButton;
    }
    
    public Button getEditSupplementButton(){
        return this.editSupplementButton;
    }
    
    public Button getDeleteSupplementButton(){
        return this.deleteSupplementButton;
    }
    
    public Button getAddCustomerButton(){
        return this.addCustomerButton;
    }
    
    public Button getEditCustomerButton(){
        return this.editCustomerButton;
    }
    
    public Button getDeleteCustomerButton(){
        return this.deleteCustomerButton;
    }
    
    public Button getSubmitButton(){
        return this.submitButton;
    }
    
    
    //get methods for ListViews
    public ListView<Supplement> getSupplementListView(){
        return this.supplementListView;
    }
    
    public ListView<Customer> getCustomerListView(){
        return this.customerListView;
    }
    
    
    //get methods for ComboBox    
    public ComboBox<String> getCustomerTypeComboBox(){
        return this.customerTypeComboBox;
    }
    
    public ComboBox<String> getPaymentTypeComboBox(){
        return this.paymentTypeComboBox;
    }
    
    public ComboBox<String> getMagazineSelectionComboBox(){
        return this.magazineSelectionComboBox;
    }
    
    public ComboBox<Supplement> getSupplementListComboBox(){
        return this.supplementListComboBox;
    }
    
    public ComboBox<PayingCustomer> getPayingCustomerListComboBox(){
        return this.payingCustomerListComboBox;
    }
    
    
    //get methods for hidden Labels
    public Label getPayingCustomerListLabel(){
        return this.payingCustomerListLabel;
    }
    
    public Label getPaymentTypeLabel(){
        return this.paymentTypeLabel;
    }
    
    public Label getAccountLabel(){
        return this.accountLabel;
    }
    
    
    //get method for undefined Label
    public Label getSelectedMagazineLabel(){
        return this.selectedMagazineLabel;
    }
    
    
    //get method for TextArea
    public TextArea getInfoBox(){
        return this.infoBox;
    }
    
    
    //get method for ListFile
    public List<File> getSelectedFile(){
        return this.selectedFile;
    }
}
