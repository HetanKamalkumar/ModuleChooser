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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

//P2589546
public class ReserveModulesPane extends VBox {

	private ListView<Module> unselectedTerm1Modules, reserveTerm1Modules;
	Label unselec1;
	Label reserve;
	Label reserveamount;
	Label reservecredlbl;
	TextField reservecredits;
	private Button add, remove, confirm;
	private ObservableList<Module> unselectedTerm1ModuleList, reserveTerm1ModuleList;

	public ReserveModulesPane() {

		//GridPane accordgridpane = new GridPane();
		//accordgridpane.setPrefSize(700, 700);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20,20,20,20));
		//this.getChildren().addAll(accordgridpane);


		unselectedTerm1ModuleList = FXCollections.observableArrayList();
		reserveTerm1ModuleList = FXCollections.observableArrayList();


		VBox vbox1 = new VBox();
		Label unselec1 = new Label("Unselected Term 1 Modules");
		unselectedTerm1Modules = new ListView<Module>(unselectedTerm1ModuleList);
		unselectedTerm1Modules.setPrefSize(1000, 735);

		vbox1.setPadding(new Insets(20,20,20,20));
		vbox1.getChildren().addAll(unselec1, unselectedTerm1Modules);
		vbox1.prefWidthProperty().bind(this.widthProperty());


		add = new Button("Add");
		remove = new Button("Remove");
		confirm = new Button("Confirm");
		add.setPrefSize(100, 0);
		remove.setPrefSize(100, 0);
		confirm.setPrefSize(100, 0);
		HBox buttons = new HBox();
		buttons.setSpacing(10);
		buttons.setAlignment(Pos.CENTER_LEFT);
		buttons.setPadding(new Insets(5,5,5,5));
		buttons.getChildren().addAll(add,remove,confirm);

		//HBox lbl = new HBox();
		//Label reserveamount = new Label("Reserve 30 credits worth of term 1 modules");
		//lbl.setSpacing(10);
		//lbl.setAlignment(Pos.CENTER_RIGHT);
		///lbl.setPadding(new Insets(2,2,2,2));
		//lbl.getChildren().addAll(reserveamount);

		HBox lbl = new HBox();
		Label reservecredlbl = new Label("Credits for Reserving Term 1 Modules: ");
		reservecredits = new TextField("0");
		reservecredits.setEditable(false);
		reservecredits.setPrefSize(60,40);
		lbl.setSpacing(10);
		lbl.setAlignment(Pos.CENTER_RIGHT);
		lbl.setPadding(new Insets(2,2,2,2));
		lbl.getChildren().addAll(reservecredlbl,reservecredits);


		VBox vbox2 = new VBox();
		Label reserve = new Label("Reserved Term 1 Modules");
		reserveTerm1Modules = new ListView<Module>(reserveTerm1ModuleList);
		reserveTerm1Modules.setPrefSize(1000, 980);
		vbox2.setPadding(new Insets(20,20,20,20));
		vbox2.getChildren().addAll(reserve,reserveTerm1Modules);
		vbox2.prefWidthProperty().bind(this.widthProperty());


		VBox leftside = new VBox(20,vbox1,lbl);

		VBox rightside = new VBox(20,vbox2,buttons);

		HBox term1accord = new HBox(20,leftside,rightside);

		this.getChildren().addAll(term1accord);
	}

	//gets
	public Module getTerm1ReserveUnselectedModule() {
		return unselectedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getReserveTerm1Module() {
		return reserveTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public void addTerm1ReserveUnselectedModules(Module m) {
		unselectedTerm1ModuleList.add(m);
	}

	public void addTerm1ReserveModules(Module m) {
		reserveTerm1ModuleList.add(m);
	}

	public ListView<Module> getReserveTerm1UnselectedModules() {
		return unselectedTerm1Modules;
	}

	public ListView<Module> getReserveTerm1Modules() {
		return reserveTerm1Modules;
	}

	public ObservableList<Module> returnReserveUnselectedTerm1() {
		return unselectedTerm1ModuleList;
	}

	public ObservableList<Module> returnReserveTerm1() {
		return reserveTerm1ModuleList;
	}

	public void setreservecredit1(int credit1) { //set reserve credits
		reservecredits.setText(String.valueOf(credit1)); 
	}

	public String getreservecredit1() { // get reserve credits
		return reservecredits.getText();
	}

	public ObservableList<Module> getEveryReserveTerm1Modules() {

		ObservableList<Module> m =	reserveTerm1Modules.getItems(); // get reserve term 1 modules

		return m;
	}

	public void clearSelection() {
		unselectedTerm1Modules.getSelectionModel().clearSelection();
		reserveTerm1Modules.getSelectionModel().clearSelection();
	}

	//event handlers 

	public void addTerm1ReserveAddHandler(EventHandler<ActionEvent> handler) {
		add.setOnAction(handler);
	}

	public void addTerm1ReserveRemoveHandler(EventHandler<ActionEvent> handler) {
		remove.setOnAction(handler);
	}

	public void addTerm1ReserveConfirmHandler(EventHandler<ActionEvent> handler) {
		confirm.setOnAction(handler);
	}
}
