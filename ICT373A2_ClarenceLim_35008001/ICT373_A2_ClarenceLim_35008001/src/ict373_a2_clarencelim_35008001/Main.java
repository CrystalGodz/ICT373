package ict373_a2_clarencelim_35008001;
/**
 * ICT373 Assignment 2
 * Name: Lim Xing Yu Clarence
 * Student No: 35008001
 * Module: ICT373 Software Architectures(A)
 * Tutor's Name: Poh Kok Loo
 * Date Submitted: 02/04/2024
 */

/**
 * Assumptions:
 * 0. The assignment requires the program to manage the ability to save only the magazine's data
 * 1. A customer must be subscribed to at least 1 supplement.
 * 2. Deleting customer and supplement records is fully deleted
 * 3. Editing of customer and supplement records prevents user from changing their UID
 *    (Customer UID: Email, Supplement UID: Name)
 * 4. Editing customer type is not allowed
 * 5. Deleting a customer cannot be performed when the customer has an existing associate
 * 6. Deleting a supplement can only be done if there are no subscribed customers
 */

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    private MainGUI gui;
    private final AlertGUI alert = new AlertGUI();
    private final MagazineService magazineService = new MagazineService();
    private Magazine magazine = new Magazine();
    private String magazineName;
    private double magazineWeeklyCost;
    ArrayList<Supplement> editSupplementList = new ArrayList<>();
    
    public static void main(String args[]){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
        this.gui = new MainGUI(primaryStage);
        this.gui.homePage();
        homePageButtonSetup();
    }
    
    //magazine selection for viewMode
    private void viewModeSelection(){
        this.gui.magazineSelectionSetup();
        this.gui.getViewButton().setDisable(true);
        this.gui.getMagazineSelectionComboBox().getItems().addAll(this.magazineService.getMagazineName());
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            this.magazineName = this.gui.getMagazineSelectionComboBox().getSelectionModel().getSelectedItem();
            if(this.magazineName != null){
                viewMode(this.magazineName);
                //reset ComboBox
                this.gui.getMagazineSelectionComboBox().setValue(null);
            }
            else{
                this.alert.displayAlert("Select a Magazine first!");
                //reset ComboBox
                this.gui.getMagazineSelectionComboBox().setValue(null);
            }
        });
    }
    
    
    //page to view selected magazine details
    private void viewMode(String magazineName){
        this.gui.viewSetup();
        this.magazine = this.magazineService.getMagazineService(magazineName);
        
        //get all supplements in the magazine
        this.gui.getSupplementListView().getItems().setAll(this.magazine.getSupplement());
        //checking user selection
        this.gui.getSupplementListView().getSelectionModel().selectedItemProperty().addListener((ob, oldSupplement, newSupplement) -> {
            if(newSupplement != null){
                this.gui.getCustomerListView().getSelectionModel().clearSelection();
                displaySupplementInfo(newSupplement);
            }
        });
        
        //get all customers in the magazine
        this.gui.getCustomerListView().getItems().setAll(this.magazine.getCustomer());
        //checking user selection
        this.gui.getCustomerListView().getSelectionModel().selectedItemProperty().addListener((ob, oldCustomer, newCustomer) -> {
            if(newCustomer != null){
                this.gui.getSupplementListView().getSelectionModel().clearSelection();
                displayCustomerInfo(newCustomer);
            }
        });
        
        //button action setup
        homePageButtonSetup();
        
        this.gui.getSelectedMagazineLabel().setText("You are currently viewing: '" + magazineName + "' Magazine");
    }
    
    
    //page for creating new magazines
    private void createMode(){
        this.gui.createPage();
        
        //button action setup
        homePageButtonSetup();
        this.gui.getCreateMagazineButton().setOnAction(e -> addMagazine());
        this.gui.getLoadMagazineButton().setOnAction(e -> loadMagazine());
        this.gui.getSaveMagazineButton().setOnAction(e -> saveMagazine());
    }
    
    
    //method to create a new magazine
    private void addMagazine(){
        this.gui.addMagazineSetup();
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            this.magazineName = this.gui.getMagazineName().getText();
            if(!this.magazineName.trim().isEmpty()){
                if(this.magazineService.compareMagazineName(this.magazineName) == false){
                    try{
                        this.magazineWeeklyCost = Double.parseDouble(gui.getMagazineWeeklyCost().getText());
                        if(magazineWeeklyCost > 0){
                            Magazine temp = new Magazine(this.magazineName, this.magazineWeeklyCost);
                            this.magazineService.addService(temp);
                            this.alert.displayAlert("Magazine, '" + this.magazineName + "'," + " has been created.");
                            //redirect back to createMode
                            createMode();
                        }
                        else{
                            this.alert.displayAlert("Magazine weekly cost cannot be 0 or negative!");
                            this.gui.getMagazineWeeklyCost().clear();
                        }
                    }
                    catch(NumberFormatException ex){
                        this.alert.displayAlert("Please enter numerical values only for Weekly Costs of Magazine!");
                        this.gui.getMagazineWeeklyCost().clear();
                    }
                }
                else{
                    this.alert.displayAlert("'" + this.magazineName + "'" + " is already in the records, try another name.");
                    this.gui.getMagazineName().clear();
                }
            }
            else{
                this.alert.displayAlert("Magazine name cannot be empty!");
            }
        });
    }
    
    
    //method to load a magazine '.ser' file
    private void loadMagazine(){
        this.gui.loadMagazineSetup();
        
        //check file selection
        if(this.gui.getSelectedFile() != null){
            for(File file : gui.getSelectedFile()){
                this.magazineName = file.getName().replace(".ser", "");
                this.magazineService.loadMagazineFromFile(this.magazineName);
                
                this.magazine = this.magazineService.getMagazineService(magazineName);
                if(this.magazine != null){
                    //redirect to viewMode to view added magazine info
                    viewMode(this.magazineName);
                }
                else{
                    createMode();
                }
            }
        }
        else{
            this.alert.displayAlert("Error, file not found.");
        }
    }
    
    
    //method to save a magazine as a '.ser' file
    private void saveMagazine(){
        this.gui.saveMagazineSetup();
        this.magazine = this.magazineService.getMagazineService(this.magazineName);
        
        this.gui.getMagazineSelectionComboBox().getItems().addAll(this.magazineService.getMagazineName());
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            this.magazineName = this.gui.getMagazineSelectionComboBox().getSelectionModel().getSelectedItem();
            if(this.magazineName != null){
                this.magazineService.saveMagazine(this.magazineName);
                //redirect back to createMode
                createMode();
            }
            else{
                this.alert.displayAlert("No Magazine was selected!");
                //reset ComboBox
                this.gui.getMagazineSelectionComboBox().setValue(null);
            }
        });
    }
    
    
    //magazine selection for editMode
    private void editModeSelection(){
        this.gui.magazineSelectionSetup();
        this.gui.getEditButton().setDisable(true);
        this.gui.getMagazineSelectionComboBox().getItems().addAll(this.magazineService.getMagazineName());
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            this.magazineName = this.gui.getMagazineSelectionComboBox().getSelectionModel().getSelectedItem();
            if(this.magazineName != null){
                editMode(this.magazineName);
                //reset ComboBox
                gui.getMagazineSelectionComboBox().setValue(null);
            }
            else{
                this.alert.displayAlert("Select a Magazine first!");
                //reset ComboBox
                this.gui.getMagazineSelectionComboBox().setValue(null);
            }
        });
    }
    
    
    //page for editing selected magazine
    private void editMode(String magazineName){
        this.gui.editSetup();
        
        //button action setup
        homePageButtonSetup();
        this.gui.getAddSupplementButton().setOnAction(e -> addSupplement(this.magazineName));
        this.gui.getAddCustomerButton().setOnAction(e -> addCustomer(this.magazineName));
        this.gui.getEditSupplementButton().setOnAction(e -> editSupplement(this.magazineName));
        this.gui.getEditCustomerButton().setOnAction(e -> editCustomer(this.magazineName));
        this.gui.getDeleteSupplementButton().setOnAction(e -> deleteSupplement(this.magazineName));
        this.gui.getDeleteCustomerButton().setOnAction(e -> deleteCustomer(this.magazineName));
        
        this.gui.getSelectedMagazineLabel().setText("You are currently viewing: '" + magazineName + "' Magazine");
    }
    
    
    //method to create new supplement
    private void addSupplement(String magazineName){
        this.gui.addSupplementSetup();
        this.magazine = this.magazineService.getMagazineService(magazineName);
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            String supplementName = this.gui.getSupplementNameTextField().getText();
            if(!supplementName.trim().isEmpty()){
                boolean existingSupplement = true;
                for(int i=0; i<this.magazine.getSupplement().size(); i++){
                    if(this.magazine.getSupplement().get(i).getSupplementName().equalsIgnoreCase(supplementName)){
                        existingSupplement = false;
                    }
                }
                if(existingSupplement){
                    try{
                        double supplementWeeklyCost = Double.parseDouble(this.gui.getSupplementWeeklyCostTextField().getText());
                        if(supplementWeeklyCost > 0){
                            Supplement temp = new Supplement(supplementName, supplementWeeklyCost);
                            this.magazine.addSupplement(temp);
                            this.alert.displayAlert("Supplement, '" + supplementName + "'," + " has been added to " + magazineName + ".");
                            //redirect back to editMode
                            editMode(this.magazineName);
                        }
                        else{
                            this.alert.displayAlert("Supplement's weekly cost cannot be 0 or negative!");
                            //reset TextBox
                            this.gui.getSupplementWeeklyCostTextField().clear();
                        }
                    }
                    catch(Exception ex){
                        this.alert.displayAlert("Supplement's weekly cost cannot be 0 or negative!");
                        //reset TextBox
                        this.gui.getSupplementWeeklyCostTextField().clear();
                    }
                }
                else{
                    this.alert.displayAlert("Supplement name already exist, try another name!");
                    //reset TextBox
                    this.gui.getSupplementNameTextField().clear();
                }
            }
            else{
                this.alert.displayAlert("You need to fill in a valid supplement name!");
                //reset TextBox
                this.gui.getSupplementNameTextField().clear();
            }
        });
    }
    
    
    //method to add customer to subscriber list
    private void addCustomer(String magazineName){
        this.gui.addCustomerSetup();
        getCustomerSelectionFields(this.magazine);
        
        this.gui.getCustomerTypeComboBox().setOnAction(e -> {
            switch(this.gui.getCustomerTypeComboBox().getValue()){
                case "Paying Customer":
                    setPayingCustomerVariablesVisible();
                    break;
                case "Associate":
                    setAssociateCustomerVariablesVisible();
                    break;
            }
        });
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            ArrayList<Supplement>[] newSupplementList = new ArrayList[]{new ArrayList<>()};
            ArrayList<Magazine>[] thisMagazine = new ArrayList[]{new ArrayList<>()};
            ArrayList<Boolean> validList = new ArrayList<>();
            validList = validateAddCustomer(validList);
            
            newSupplementList[0].add(this.gui.getSupplementListComboBox().getValue());
            thisMagazine[0].add(this.magazine);
            
            Boolean validCustomer = isValid(validList);
            if(validCustomer){
                switch(this.gui.getCustomerTypeComboBox().getValue()){
                    case "Paying Customer":
                        PayingCustomer newPayingCustomer = new PayingCustomer();
                        setCustomerInfo(newPayingCustomer, thisMagazine[0], newSupplementList[0]);
                        newPayingCustomer.setPaymentMethod(new PaymentMethod(this.gui.getPaymentTypeComboBox().getValue(),
                                                        Long.parseLong(this.gui.getAccountNumberTextField().getText())));
                        this.magazine.addCustomer(newPayingCustomer);
                        this.alert.displayAlert("Paying Customer, '" + newPayingCustomer.getName() + "' has been added as a subscriber of: " + magazineName + " Magazine");
                        break;

                    case "Associate":
                        AssociateCustomer newAssociateCustomer = new AssociateCustomer();
                        PayingCustomer payer = new PayingCustomer();
                        payer = this.gui.getPayingCustomerListComboBox().getValue();
                        setCustomerInfo(newAssociateCustomer, thisMagazine[0], newSupplementList[0]);
                        newAssociateCustomer.setPayingCustomer(payer);
                        payer.addAssociateCustomer(newAssociateCustomer);
                        this.magazine.addCustomer(newAssociateCustomer);
                        this.alert.displayAlert("Associate Customer, '" + newAssociateCustomer.getName() + "' has been added as a subscriber of: " + magazineName + " Magazine");
                        break;
                }
                //redirect back to editMode
                editMode(this.magazineName);
            }
        });
    }
    
    //customer isValid ArrayList<Boolean>
    private ArrayList<Boolean> validateAddCustomer(ArrayList<Boolean> customerValidation){
        //initialise 8 values to customerValidation ArrayList<>
        for(int i=0; i<8; i++){
            customerValidation.add(Boolean.FALSE);
        }
        //check customer's variable one by one
        if(!this.gui.getCustomerTypeComboBox().getSelectionModel().isEmpty()){
            customerValidation.set(0, Boolean.TRUE);
            if(!this.gui.getCustomerName().getText().trim().isEmpty()){
                customerValidation.set(1, Boolean.TRUE);
                if(validEmail(this.gui.getCustomerEmail().getText()) && !duplicateEmail(this.gui.getCustomerEmail().getText())){
                    customerValidation.set(2, Boolean.TRUE);
                    if(!this.gui.getCustomerStreetNumber().getText().trim().isEmpty()){
                        customerValidation.set(3, Boolean.TRUE);
                        if(!this.gui.getCustomerStreetName().getText().trim().isEmpty()){
                            customerValidation.set(4, Boolean.TRUE);
                            if(!this.gui.getCustomerSuburb().getText().trim().isEmpty()){
                                customerValidation.set(5, Boolean.TRUE);
                                if(!this.gui.getCustomerPostalCode().getText().trim().isEmpty()){
                                    customerValidation.set(6, Boolean.TRUE);
                                    if(!gui.getSupplementListComboBox().getSelectionModel().isEmpty()){
                                        customerValidation.set(7, Boolean.TRUE);
                                    }
                                    else{
                                        this.alert.displayAlert("Please select a supplement to subscribe.");
                                    }
                                }
                                else{
                                    this.alert.displayAlert("PostalCode cannot be empty!");
                                }
                            }
                            else{
                                this.alert.displayAlert("Suburb cannot be empty.");
                            }
                        }
                        else{
                            this.alert.displayAlert("Street name cannot be empty.");
                        }
                    }
                    else{
                        this.alert.displayAlert("Street number cannot be empty.");
                    }
                }
                else{
                    this.alert.displayAlert("Customer's email is invalid.");
                    this.gui.getCustomerEmail().clear();
                }
            }
            else{
                this.alert.displayAlert("Customer's name cannot be empty.");
            }
        }
        else{
            this.alert.displayAlert("Please indicate if customer is a 'Paying Customer' or 'Associate'.");
        }
        
        
        //validating Paying Customer fields
        String thisCustomer = this.gui.getCustomerTypeComboBox().getValue();
        if(thisCustomer != null && thisCustomer.equalsIgnoreCase("Paying customer")){
            try{
                Long.valueOf(this.gui.getAccountNumberTextField().getText());
                customerValidation.add(Boolean.TRUE);
            }
            catch(NumberFormatException ex){
                this.alert.displayAlert("Bank/Card number can only contain numeric.");
                customerValidation.add(Boolean.FALSE);
            }
            if(!this.gui.getPaymentTypeComboBox().getSelectionModel().isEmpty()){
                customerValidation.add(Boolean.TRUE);
            }
            else{
                this.alert.displayAlert("You need to select a payment method.");
                customerValidation.add(Boolean.FALSE);
            }
        }
        //validating Associate Customer fields
        else if(thisCustomer != null && thisCustomer.equalsIgnoreCase("Associate")){
            if(!this.gui.getPayingCustomerListComboBox().getSelectionModel().isEmpty()){
                customerValidation.add(Boolean.TRUE);
            }
            else{
                this.alert.displayAlert("You need to select a Paying Customer.");
                customerValidation.add(Boolean.FALSE);
            }
        }
        return customerValidation;
    }
    
    
    //method to edit existing supplements
    private void editSupplement(String magazineName){
        this.gui.editSupplementSetup();
        this.magazine = this.magazineService.getMagazineService(magazineName);
        this.gui.getSupplementListView().getItems().addAll(magazine.getSupplement());
        
        //checking user TextField changes
        this.gui.getSupplementListView().getSelectionModel().selectedItemProperty().addListener((ob, oldSupplement, newSupplement) -> {
            this.gui.getSupplementNameTextField().setText(newSupplement.getSupplementName());
            this.gui.getSupplementWeeklyCostTextField().setText(String.valueOf((double) newSupplement.getS_WeeklyCost()));
        });
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            Supplement supplement = gui.getSupplementListView().getSelectionModel().getSelectedItem();
            if(supplement != null){
                String supplementName = this.gui.getSupplementNameTextField().getText();
                if(!supplementName.trim().isEmpty()){
                    try{
                        double supplementWeeklyCost = Double.parseDouble(gui.getSupplementWeeklyCostTextField().getText());
                        if(supplementWeeklyCost > 0){
                            supplement.setSupplementName(supplementName);
                            supplement.setS_WeeklyCost(supplementWeeklyCost);
                            this.alert.displayAlert("Supplement, '" + supplementName + "'," + " has been updated.");
                            //redirect back to editMode
                            editMode(this.magazineName);
                        }
                        else{
                            this.alert.displayAlert("Supplement's weekly cost cannot be 0 or negative!");
                        }
                    }
                    catch(Exception ex){
                        this.alert.displayAlert("Supplement's weekly cost cannot be 0 or negative!");
                    }
                }
                else{
                    this.alert.displayAlert("You need to fill in a valid supplement name!");
                }
            }
            else{
                this.alert.displayAlert("You need to select a supplement!");
            }
        });
    }
    
    
    //method to edit existing customers
    private void editCustomer(String magazineName){
        this.gui.editCustomerSetup();
        this.magazine = this.magazineService.getMagazineService(magazineName);
        this.gui.getCustomerListView().getItems().addAll(this.magazine.getCustomer());
        this.gui.getSupplementListComboBox().getItems().addAll(this.magazine.getSupplement());
        
        
        //adding paying customers to paying customer ComboBox
        ArrayList<PayingCustomer> tempPayerList = new ArrayList<>();
        for(int i=0; i<this.magazine.getCustomer().size(); i++){
            if(this.magazine.getCustomer().get(i) instanceof PayingCustomer){
                tempPayerList.add((PayingCustomer)this.magazine.getCustomer().get(i));
            }
        }
        this.gui.getPayingCustomerListComboBox().getItems().addAll(tempPayerList);
        
        //checking user TextField changes
        this.gui.getCustomerListView().getSelectionModel().selectedItemProperty().addListener((ob, oldCustomer, newCustomer) -> {
            displayEditingCustomerInfo(newCustomer);
            this.editSupplementList.clear();
            this.editSupplementList.addAll(newCustomer.getSupplement());
            this.gui.getSupplementListView().getItems().setAll(this.editSupplementList);
        });
        
        //button action setup
        homePageButtonSetup();
        this.gui.getRemoveButton().setOnAction(e -> {
            Supplement removeSupplement = this.gui.getSupplementListView().getSelectionModel().getSelectedItem();
            this.editSupplementList.remove(removeSupplement);
            this.gui.getSupplementListView().getItems().setAll(this.editSupplementList);
        });
        
        this.gui.getAddButton().setOnAction(e -> {
            boolean sameSupplement = false;
            Customer customer = this.gui.getCustomerListView().getSelectionModel().getSelectedItem();
            Supplement addSupplement = this.gui.getSupplementListComboBox().getSelectionModel().getSelectedItem();
            for(int i=0; i<customer.getSupplement().size(); i++){
                if(customer.getSupplement().get(i).equals(addSupplement)){
                    sameSupplement = true;
                }
            }
            if(!sameSupplement){
                this.editSupplementList.add(addSupplement);
                this.gui.getSupplementListView().getItems().setAll(this.editSupplementList);
            }
            else{
                this.alert.displayAlert("Supplement, '" + addSupplement.getSupplementName() + "' is already subscribed to, try another supplement.");
            }
        });
        
        this.gui.getSubmitButton().setOnAction(e -> {
            ArrayList<Magazine>[] thisMagazine = new ArrayList[]{new ArrayList<>()};
            ArrayList<Boolean> validList = new ArrayList<>();
            validList = validateEditCustomer(validList);
            
            thisMagazine[0].add(this.magazine);
            
            boolean validCustomer = isValid(validList);
            if(validCustomer){
                Customer customer = this.gui.getCustomerListView().getSelectionModel().getSelectedItem();
                if(this.gui.getCustomerTypeComboBox().getValue().equals("Paying Customer")){
                    PayingCustomer chosenPayingCustomer = (PayingCustomer)customer;
                    setCustomerInfo(chosenPayingCustomer, thisMagazine[0], this.editSupplementList);
                    chosenPayingCustomer.setPaymentMethod(new PaymentMethod(this.gui.getPaymentTypeComboBox().getValue(),
                                                        Long.parseLong(this.gui.getAccountNumberTextField().getText())));
                    this.alert.displayAlert("Paying Customer, '" + chosenPayingCustomer.getName() + "' has been updated");
                }
                else if(this.gui.getCustomerTypeComboBox().getValue().equals("Associate")){
                    AssociateCustomer chosenAssociateCustomer = (AssociateCustomer)customer;
                    setCustomerInfo(chosenAssociateCustomer, thisMagazine[0], this.editSupplementList);
                    PayingCustomer choiceOfPayer = this.gui.getPayingCustomerListComboBox().getValue();
                    this.alert.displayAlert("Associate Customer, '" + chosenAssociateCustomer.getName() + "' has been updated");
                    if(!chosenAssociateCustomer.getPayingCustomer().equals(choiceOfPayer)){
                        deleteAssociateCustomer(customer, this.magazine);
                        choiceOfPayer.addAssociateCustomer(chosenAssociateCustomer);
                        chosenAssociateCustomer.setPayingCustomer(choiceOfPayer);
                    }
                }
                //redirect back to editMode
                editMode(this.magazineName);
            }
        });
    }
    
    
    //set editing customer fields
    private void displayEditingCustomerInfo(Customer setCustomer){
        if(setCustomer instanceof PayingCustomer){
            setPayingCustomerVariablesVisible();
            PayingCustomer payingCustomer = (PayingCustomer)setCustomer;
            this.gui.getCustomerTypeComboBox().setValue("Paying Customer");
            this.gui.getAccountNumberTextField().setText(String.valueOf(payingCustomer.getPaymentMethod().getSerialNo()));
            this.gui.getPaymentTypeComboBox().setValue(payingCustomer.getPaymentMethod().getPaymentType());
        }
        else if(setCustomer instanceof AssociateCustomer){
            setAssociateCustomerVariablesVisible();
            AssociateCustomer associateCustomer = (AssociateCustomer)setCustomer;
            this.gui.getCustomerTypeComboBox().setValue("Associate");
            this.gui.getPayingCustomerListComboBox().setValue(associateCustomer.getPayingCustomer());
        }
        this.gui.getCustomerName().setText(setCustomer.getName());
        this.gui.getCustomerEmail().setText(setCustomer.getEmail());
        this.gui.getCustomerStreetNumber().setText(setCustomer.getAddress().getStreetNumber());
        this.gui.getCustomerStreetName().setText(setCustomer.getAddress().getStreetName());
        this.gui.getCustomerSuburb().setText(setCustomer.getAddress().getSuburb());
        this.gui.getCustomerPostalCode().setText(setCustomer.getAddress().getPostalCode());
    }
    
    
    //method to set customer's info
    private void setCustomerInfo(Customer customer, ArrayList<Magazine> magazines, ArrayList<Supplement> supplements){
        customer.setName(this.gui.getCustomerName().getText());
        customer.setEmail(this.gui.getCustomerEmail().getText());
        customer.setAddress(new Address(this.gui.getCustomerStreetNumber().getText(),
                                           this.gui.getCustomerStreetName().getText(), 
                                           this.gui.getCustomerSuburb().getText(),
                                           this.gui.getCustomerPostalCode().getText()));
        customer.setMagazine(magazines);
        customer.setSupplement(supplements);
    }
    
    
    //check if new customer's matches the ArrayList<Customer> fields
    private boolean isValid(ArrayList<Boolean> isValidList){
        boolean valid = true;
        for(int i=0; i<isValidList.size(); i++){
            if(!isValidList.get(i)){
                valid = false;
                break;
            }
        }
        return valid;
    }
    
    
    //customer isValid ArrayList<Boolean>
    private ArrayList<Boolean> validateEditCustomer(ArrayList<Boolean> customerValidation){
        //initialise 8 values to customerValidation ArrayList<>
        for(int i=0; i<8; i++){
            customerValidation.add(false);
        }
        //check customer's variable one by one
        if(!this.gui.getCustomerTypeComboBox().getSelectionModel().isEmpty()){
            customerValidation.set(0, Boolean.TRUE);
            if(!this.gui.getCustomerName().getText().trim().isEmpty()){
                customerValidation.set(1, Boolean.TRUE);
                if(validEmail(this.gui.getCustomerEmail().getText())){
                    customerValidation.set(2, Boolean.TRUE);
                    if(!this.gui.getCustomerStreetNumber().getText().trim().isEmpty()){
                        customerValidation.set(3, Boolean.TRUE);
                        if(!this.gui.getCustomerStreetName().getText().trim().isEmpty()){
                            customerValidation.set(4, Boolean.TRUE);
                            if(!this.gui.getCustomerSuburb().getText().trim().isEmpty()){
                                customerValidation.set(5, Boolean.TRUE);
                                if(!this.gui.getCustomerPostalCode().getText().trim().isEmpty()){
                                    customerValidation.set(6, Boolean.TRUE);
                                    if(!gui.getSupplementListView().getItems().isEmpty()){
                                        customerValidation.set(7, Boolean.TRUE);
                                    }
                                    else{
                                        this.alert.displayAlert("Must be subscribed to at least 1 supplement.");
                                    }
                                }
                                else{
                                    this.alert.displayAlert("PostalCode cannot be empty.");
                                }
                            }
                            else{
                                this.alert.displayAlert("Suburb cannot be empty.");
                            }
                        }
                        else{
                            this.alert.displayAlert("Street name cannot be empty.");
                        }
                    }
                    else{
                        this.alert.displayAlert("Street number cannot be empty.");
                    }
                }
                else{
                    this.alert.displayAlert("Customer's email is invalid.");
                    this.gui.getCustomerEmail().clear();
                }
            }
            else{
                this.alert.displayAlert("Customer's name cannot be empty.");
            }
        }
        else{
            this.alert.displayAlert("Please indicate if customer is a 'Paying Customer' or 'Associate'.");
        }
        
        //validating Paying Customer fields
        String thisCustomerType = this.gui.getCustomerTypeComboBox().getValue();
        if(thisCustomerType != null && thisCustomerType.equalsIgnoreCase("Paying customer")){
            try{
                Long.valueOf(this.gui.getAccountNumberTextField().getText());
                customerValidation.add(Boolean.TRUE);
            }
            catch(NumberFormatException ex){
                this.alert.displayAlert("Bank/Card number can only contain numerics.");
                customerValidation.add(Boolean.FALSE);
            }
            if(!this.gui.getPaymentTypeComboBox().getSelectionModel().isEmpty()){
                customerValidation.add(Boolean.TRUE);
            }
            else{
                this.alert.displayAlert("You need to select a payment method.");
                customerValidation.add(Boolean.FALSE);
            }
        }
        //validating Associate Customer fields
        else if(thisCustomerType != null && thisCustomerType.equalsIgnoreCase("Associate")){
            if(!this.gui.getPayingCustomerListComboBox().getSelectionModel().isEmpty()){
                customerValidation.add(Boolean.TRUE);
            }
            else{
                this.alert.displayAlert("You need to select a Paying Customer.");
                customerValidation.add(Boolean.FALSE);
            }
        }
        return customerValidation;
    }
    
    
    //email isValid
    private static boolean validEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9_\\-\\.]+@[A-Za-z0-9_\\-\\.]+\\.[A-Za-z]{2,6}$");
        Matcher emailPatternMatcher = emailPattern.matcher(email);
        return emailPatternMatcher.matches();
    }
    
    
    //email isDuplicate
    private boolean duplicateEmail(String email){
        boolean isDuplicate = false;
        for(int i=0; i<this.magazine.getCustomer().size(); i++){
            if(this.magazine.getCustomer().get(i).getEmail().equalsIgnoreCase(email)){
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }
    
    
    //method to delete an existing supplement record
    private void deleteSupplement(String magazineName){
        this.gui.deleteSupplementSetup();
        this.magazine = this.magazineService.getMagazineService(magazineName);
        this.gui.getSupplementListView().getItems().setAll(magazine.getSupplement());
        
        //checking user TextField changes
        this.gui.getSupplementListView().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            displaySupplementInfo(newValue);
        });
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            Supplement supplement = gui.getSupplementListView().getSelectionModel().getSelectedItem();
            if(supplement != null){
                boolean exisingSubscriber = false;
                for(int i=0; i<this.magazine.getCustomer().size(); i++){
                    Customer tempCustomer = this.magazine.getCustomer().get(i);
                    for(int ii = 0; ii<tempCustomer.getSupplement().size(); ii++){
                        if(tempCustomer.getSupplement().get(ii).equals(supplement)){
                            exisingSubscriber = true;
                        }
                    }
                }
                if(!exisingSubscriber){
                    this.alert.displayAlert("This supplement '" + supplement.getSupplementName() + "' has been removed.");
                    this.magazine.removeSupplement(supplement);
                    //redirect back to editMode
                    editMode(this.magazineName);
                }
                else{
                    this.alert.displayAlert("This supplement '" + supplement.getSupplementName() + "' cannot be deleted at the moment.");
                    editMode(this.magazineName);
                    
                }
            }
            else{
                this.alert.displayAlert("You need to select a supplement!");
            }
        });
    }
    
    
    //method to delete an existing customer record
    private void deleteCustomer(String magazineName){
        this.gui.deleteCustomerSetup();
        this.magazine = this.magazineService.getMagazineService(magazineName);
        this.gui.getCustomerListView().getItems().setAll(this.magazine.getCustomer());
        
        //checking user TextField changes
        this.gui.getCustomerListView().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            displayCustomerInfo(newValue);
        });
        
        //button action setup
        homePageButtonSetup();
        this.gui.getSubmitButton().setOnAction(e -> {
            Customer customer = this.gui.getCustomerListView().getSelectionModel().getSelectedItem();
            if(customer != null){
                if(customer instanceof PayingCustomer){
                    PayingCustomer currPaying = (PayingCustomer)customer;
                    if(currPaying.getAssociateCustomer().isEmpty()){
                        this.magazine.removeCustomer(currPaying);
                        this.alert.displayAlert("Customer, '" + currPaying.getName() + "' ,has been removed.");
                        //redirect back to editMode
                        editMode(this.magazineName);
                    }
                    else{
                        this.alert.displayAlert("Customer, '" + currPaying.getName() + "' ,has an existing associate, and cannot be deleted.");
                    }
                }
                else if(customer instanceof AssociateCustomer){
                    deleteAssociateCustomer(customer, this.magazine);
                    this.magazine.removeCustomer(customer);
                    this.alert.displayAlert("Customer, '" + customer.getName() + "' ,has been removed.");
                    //redirect back to editMode
                    editMode(this.magazineName);
                }
            }
        });
    }
    
    
    //method to remove an associate customer
    private void deleteAssociateCustomer(Customer thisCustomer, Magazine thisMagazine){
        for(int i=0; i<thisMagazine.getCustomer().size(); i++){
            if(thisMagazine.getCustomer().get(i) instanceof PayingCustomer){
                PayingCustomer temp = (PayingCustomer)thisMagazine.getCustomer().get(i);
                if(temp.associateCustomerIsEqual(thisCustomer.getName())){
                    temp.removeAssociateCustomer(thisCustomer);
                }
            }
        }
    }
    
    
    //toString methods
    private void displaySupplementInfo(Supplement supplement){
        //text to set in getInfoBox
        String text = "Supplement: " +  supplement.getSupplementName() +
                "\nCost: $" + String.format("%.2f", supplement.getS_WeeklyCost());
        
        //set text in getInfoBox
        gui.getInfoBox().setText(text);
    }
    
    private void displayCustomerInfo(Customer customer){
        String supplements = "";
        double totalCost;
        
        //get list of supplements customer subscribed to
        for(int i=0; i<customer.getSupplement().size(); i++){
            int supplementNo = i + 1; 
            supplements += ("\n" + supplementNo + ".[ " + customer.getSupplement().get(i).getSupplementName()) + " ] $" +
                    String.format("%.2f", customer.getSupplement().get(i).getS_WeeklyCost());
        }
        
        //text to set in getInfoBox
        String text = "Name: " + customer.getName() +
                "\nEmail: "+ customer.getEmail() + "\n" +
                customer.getAddress().toString() +
                "\nSubscribed Magazine:\n[ " + this.magazineName + " ] $" + String.format("%.2f", this.magazine.getM_WeeklyCost()) +
                "\n\nSubscribed Supplements:" + supplements;
        
        //check if customer is PayingCustomer or AssoicateCustomer
        if(customer instanceof PayingCustomer){
            String associates = "";
            PayingCustomer currPaying = (PayingCustomer)customer;
            for(int ii=0; ii<currPaying.getAssociateCustomer().size(); ii++){
                int associateCount = ii + 1;
                associates += ("\n" + associateCount + ".[ " + currPaying.getAssociateCustomer().get(ii).getName() + " ]");
            }
            totalCost = (currPaying.getTotalMagazineCost(customer) + currPaying.getTotalSupplementCost(customer) + 
                    currPaying.getAssociatesTotalMagazineCost(customer) + currPaying.getAssociatesTotalSupplementCost(customer));
            text += "\n\nYou are a Paying Customer." +
                    "\n\nPaid with: " + currPaying.getPaymentMethod() +
                    "\n\nList of Associates" + associates +
                    "\n\nMonthly Subscription Bill: $" + String.format("%.2f", totalCost) + 
                    "\n\nBreakdown of total fee:" + 
                    "\nCost for subscribed Magazine: $" + String.format("%.2f", currPaying.getTotalMagazineCost(customer)) + 
                    "\nCost for subscribed Supplements: $" + String.format("%.2f", currPaying.getTotalSupplementCost(customer)) + 
                    "\nCost for all Associate's subscribed Magazine: $" + String.format("%.2f", currPaying.getAssociatesTotalMagazineCost(customer)) +
                    "\nCost for all Associate's subscribed Supplements: $" + String.format("%.2f", currPaying.getAssociatesTotalSupplementCost(customer));
        }
        else if(customer instanceof AssociateCustomer){
            AssociateCustomer currAssociate = (AssociateCustomer)customer;
            text += "\n\nYou are an associate of Customer: '" + currAssociate.getPayingCustomer().getName() +"'.";
        }
        
        //set text in getInfoBox
        gui.getInfoBox().setText(text);
    }
    
    
    //method to get data filled by user for the new customer.
    private void getCustomerSelectionFields(Magazine thisMagazine){
        this.gui.getCustomerTypeComboBox().getItems().addAll("Paying Customer", "Associate");
        this.gui.getSupplementListComboBox().getItems().addAll(thisMagazine.getSupplement());
        
        //define customer ArrayList<>
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.addAll(thisMagazine.getCustomer());
        
        ArrayList<PayingCustomer> payingCustomerList = new ArrayList<>();
        for(int i=0; i<customerList.size(); i++){
            if(customerList.get(i) instanceof PayingCustomer){
                payingCustomerList.add((PayingCustomer) customerList.get(i));
            }
        }
        this.gui.getPayingCustomerListComboBox().getItems().addAll(payingCustomerList);
        this.gui.getPayingCustomerListComboBox().setPromptText("Select Payer.");
    }
    
    
    //method to display fields depending on customer's type
    private void setPayingCustomerVariablesVisible(){
        this.gui.getAccountLabel().setVisible(true);
        this.gui.getAccountNumberTextField().setVisible(true);
        this.gui.getPaymentTypeLabel().setVisible(true);
        this.gui.getPaymentTypeComboBox().setVisible(true);
        
        this.gui.getPayingCustomerListLabel().setVisible(false);
        this.gui.getPayingCustomerListComboBox().setVisible(false);
        this.gui.getPayingCustomerListComboBox().setValue(null);
    }
    
    private void setAssociateCustomerVariablesVisible(){
        this.gui.getAccountLabel().setVisible(false);
        this.gui.getAccountNumberTextField().setVisible(false);
        this.gui.getAccountNumberTextField().clear();
        this.gui.getPaymentTypeLabel().setVisible(false);
        this.gui.getPaymentTypeComboBox().setVisible(false);
        this.gui.getPaymentTypeComboBox().setValue(null);
        
        this.gui.getPayingCustomerListLabel().setVisible(true);
        this.gui.getPayingCustomerListComboBox().setVisible(true);
    }
    
    
    //homePage button setup
    private void homePageButtonSetup(){
        this.gui.getViewButton().setOnAction(e -> viewModeSelection());
        this.gui.getCreateButton().setOnAction(e -> createMode());
        this.gui.getEditButton().setOnAction(e -> editModeSelection());
    }
}
