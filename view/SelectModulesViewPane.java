package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

//P2589546
public class SelectModulesViewPane extends VBox{

	private Button reset, submit, add1, add2, remove1, remove2;
	private ListView<Module> unselectedTerm2Modules, unselectedTerm1Modules, selectedYearLongModules, selectedTerm1Modules, selectedTerm2Modules;	
	private TextField currentTerm1Credits;
	private TextField currentTerm2Credits;
	private Label t1;
	private Label t2;
	private ObservableList<Module> unselectedTerm2ModulesList, unselectedTerm1ModulesList, selectedYearLongModulesList, selectedTerm1ModulesList, selectedTerm2ModulesList;


	public SelectModulesViewPane() { 

		GridPane grid = new GridPane();
		//grid.setHgap(20);
		//grid.setVgap(20);	
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(19,19,19,19));	
		this.getChildren().addAll(grid);


		unselectedTerm1ModulesList = FXCollections.observableArrayList();	
		unselectedTerm2ModulesList = FXCollections.observableArrayList();	
		selectedYearLongModulesList = FXCollections.observableArrayList();	
		selectedTerm1ModulesList = FXCollections.observableArrayList();	
		selectedTerm2ModulesList = FXCollections.observableArrayList();	


		VBox vbox1 = new VBox();
		Label unterm1 = new Label("Unselected Term 1 Modules");
		unselectedTerm1Modules = new ListView<Module>(unselectedTerm1ModulesList);
		unselectedTerm1Modules.setPrefSize(900, 300);
		unselectedTerm1Modules.setMinWidth(200);
		unselectedTerm1Modules.setMinHeight(50);


		vbox1.setPadding((new Insets(20,20,20,20)));
		vbox1.getChildren().addAll(unterm1, unselectedTerm1Modules);
		vbox1.prefWidthProperty().bind(this.widthProperty());



		add1 = new Button("Add");
		add1.setPrefSize(65, 0);
		t1 = new Label("Term 1");
		remove1 = new Button("Remove");
		remove1.setPrefSize(75, 0);
		HBox buttons1 = new HBox();
		buttons1.setSpacing(15);
		buttons1.setAlignment(Pos.CENTER);
		buttons1.getChildren().addAll(t1, add1, remove1);
		buttons1.setPrefSize(50, 20);
		buttons1.prefWidthProperty().bind(this.widthProperty());
		buttons1.setPadding(new Insets(0,0,0,10));
		

		VBox vbox2 = new VBox();
		Label unterm2 = new Label("Unselected Term 2 Modules");
		unselectedTerm2Modules = new ListView<Module>(unselectedTerm2ModulesList);
		unselectedTerm2Modules.setPrefSize(900, 300);
		unselectedTerm2Modules.setMinWidth(200);
		unselectedTerm2Modules.setMinHeight(50);
		vbox2.setPadding((new Insets(5,20,20,20)));
		vbox2.getChildren().addAll(unterm2, unselectedTerm2Modules);
		vbox2.prefWidthProperty().bind(this.widthProperty());



		add2 = new Button("Add");
		add2.setPrefSize(65, 0);		
		t2 = new Label("Term 2");
		remove2 = new Button("Remove");
		remove2.setPrefSize(75, 0);		
		HBox buttons2 = new HBox(); 
		buttons2.setSpacing(15);
		buttons2.setAlignment(Pos.CENTER);
		buttons2.getChildren().addAll(t2, add2, remove2);
		buttons2.setPrefSize(50, 20);
		//buttons2.setPrefWidth(50);
		//buttons2.setPrefHeight(0);



		Label currterm1 = new Label("Current Term 1 Credits: ");
		currentTerm1Credits = new TextField("0");
		HBox term1cred = new HBox();
		currentTerm1Credits.setEditable(false);
		currentTerm1Credits.setPrefSize(60, 40);
		term1cred.setAlignment(Pos.CENTER);
		term1cred.getChildren().addAll(currterm1, currentTerm1Credits);
		term1cred.setPrefSize(60, 20);
		term1cred.setSpacing(10);
		term1cred.setPadding(new Insets(10,0,10,0));

		VBox unselectBox = new VBox(20,vbox1, buttons1, vbox2, buttons2,term1cred);


		VBox vbox3 = new VBox();
		Label selyear = new Label("Selected Year Long Modules");
		selectedYearLongModules = new ListView<Module>(selectedYearLongModulesList);
		selectedYearLongModules.setPrefSize(400, 50);
		selectedYearLongModules.setMinWidth(200);
		selectedYearLongModules.setMinHeight(50);
		vbox3.setPadding((new Insets(20,20,10,0)));
		vbox3.getChildren().addAll(selyear, selectedYearLongModules);
		vbox3.prefWidthProperty().bind(this.widthProperty());

		
		VBox vbox4 = new VBox();
		Label selterm1 = new Label("Selected Term 1 Modules");
		selectedTerm1Modules = new ListView<Module>(selectedTerm1ModulesList);
		selectedTerm1Modules.setPrefSize(900, 300);
		selectedTerm1Modules.setMinWidth(200);
		selectedTerm1Modules.setMinHeight(50);
		vbox4.setPadding((new Insets(5,20,10,0)));
		vbox4.getChildren().addAll(selterm1, selectedTerm1Modules);
		vbox4.prefWidthProperty().bind(this.widthProperty());


		VBox vbox5 = new VBox();
		Label selterm2 = new Label("Selected Term 2 Modules");
		selectedTerm2Modules = new ListView<Module>(selectedTerm2ModulesList);
		selectedTerm2Modules.setPrefSize(900, 300);
		selectedTerm2Modules.setMinWidth(200);
		selectedTerm2Modules.setMinHeight(50);
		vbox5.setPadding((new Insets(5,20,15,0)));
		vbox5.getChildren().addAll(selterm2, selectedTerm2Modules);
		vbox5.prefWidthProperty().bind(this.widthProperty());

		
		Label currterm2 = new Label("Current Term 2 Credits: ");
		currentTerm2Credits = new TextField("0");
		HBox term2cred = new HBox();
		currentTerm2Credits.setEditable(false);
		currentTerm2Credits.setPrefSize(60, 40);
		term2cred.setAlignment(Pos.CENTER);
		term2cred.setPadding(new Insets(15,20,20,20));
		term2cred.getChildren().addAll(currterm2, currentTerm2Credits);
		term2cred.setPrefSize(60, 20);
		term2cred.setSpacing(10);


		VBox selectBox = new VBox(20,vbox3, vbox4, vbox5,term2cred);
		HBox modules = new HBox(20,unselectBox, selectBox);



		HBox buttons3 = new HBox(20); 
		reset = new Button("Reset");
		reset.setPrefSize(75, 0);
		submit = new Button("Submit");
		submit.setPrefSize(75, 0);
		buttons3.getChildren().addAll(reset,submit);
		buttons3.setAlignment(Pos.BOTTOM_CENTER);
		buttons3.setPrefSize(30, 15);
		buttons3.setSpacing(30);
		buttons3.setPadding(new Insets(0,0,5,0));
        buttons3.prefWidthProperty().bind(this.widthProperty());
		
		this.getChildren().addAll(modules, buttons3);
	}

	// get methods


	public ListView<Module> getUnselectedTerm1Modules() {
		return unselectedTerm1Modules;
	}
	public ListView<Module> getUnselectedTerm2Modules() {
		return unselectedTerm2Modules;
	}
	public ListView<Module> getSelectedTerm1Modules() {
		return selectedTerm1Modules;
	}
	public ListView<Module> getSelectedTerm2Modules() {
		return selectedTerm2Modules;
	}
	public ListView<Module> getSelectedYearLongModules() {
		return selectedYearLongModules;
	}
	
	public void addUnselectedTerm1Modules(Module m) {
		unselectedTerm1ModulesList.add(m);
	}

	public void addUnselectedTerm2Modules(Module m) {
		unselectedTerm2ModulesList.add(m);
	}

	public void addSelectedTerm1Modules(Module m) {
		selectedTerm1ModulesList.add(m);
	}

	public void addSelectedTerm2Modules(Module m) {
		selectedTerm2ModulesList.add(m);
	}

	public void addSelectedYearLongModules(Module m) {
		selectedYearLongModulesList.add(m);
	}
	

	public Module getUnselectedTerm1() {
		return unselectedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getUnselectedTerm2() {
		return unselectedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedTerm1() {
		return selectedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedTerm2() {
		return selectedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedLongYearModules() {
		return selectedYearLongModules.getSelectionModel().getSelectedItem();
	}


	public void setcredit1(int credit1) {
		currentTerm1Credits.setText(String.valueOf(credit1)); 
	}

	public void setcredit2(int credit2) {
		currentTerm2Credits.setText(String.valueOf(credit2)); 
	}

	public String getcredit1() {
		return currentTerm1Credits.getText();
	}

	public String getcredit2() {
		return currentTerm2Credits.getText();
	}


	public ObservableList<Module> returnUnselectedTerm1() {
		return unselectedTerm1ModulesList;
	}

	public ObservableList<Module> returnUnselectedTerm2() {
		return unselectedTerm2ModulesList;
	}

	public ObservableList<Module> returnSelectedTerm1Modules() {
		return selectedTerm1ModulesList;
	}

	public ObservableList<Module> returnSelectedTerm2Modules() {
		return selectedTerm2ModulesList;
	}

	public ObservableList<Module> returnSelectedYearLongModules() {
		return selectedYearLongModulesList;
	}

	public ObservableList<Module> getEverySelectedModule() { // get all the selected modules 
		
		ObservableList<Module> m =	selectedYearLongModules.getItems(); // gets the selected year long modules
		
		m.addAll(selectedTerm1Modules.getItems()); // gets the selected term 1 modules
		
		m.addAll(selectedTerm2Modules.getItems()); // gets the selected term 2 modules
		
		return m;
	}

	//event handling

	public void addTerm1AddHandler(EventHandler<ActionEvent> handler) {
		add1.setOnAction(handler);
	}

	public void addTerm2AddHandler(EventHandler<ActionEvent> handler) {
		add2.setOnAction(handler);
	}

	public void addTerm1RemoveHandler(EventHandler<ActionEvent> handler) {
		remove1.setOnAction(handler);
	}

	public void addTerm2RemoveHandler(EventHandler<ActionEvent> handler) {
		remove2.setOnAction(handler);
	}

	public void addResetHandler(EventHandler<ActionEvent> handler) {
		reset.setOnAction(handler);
	}

	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		submit.setOnAction(handler);
	}


	public void clearListViews() {
		unselectedTerm1Modules.getItems().clear();
		unselectedTerm2Modules.getItems().clear();
		selectedTerm1Modules.getItems().clear();
		selectedTerm2Modules.getItems().clear();
		selectedYearLongModules.getItems().clear();
	}

	public void clearSelection() {
		unselectedTerm1Modules.getSelectionModel().clearSelection();
		unselectedTerm2Modules.getSelectionModel().clearSelection();
		selectedTerm1Modules.getSelectionModel().clearSelection();
		selectedTerm2Modules.getSelectionModel().clearSelection();
		selectedYearLongModules.getSelectionModel().clearSelection();
	}
}










