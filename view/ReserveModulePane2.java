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
public class ReserveModulePane2 extends VBox {

	private ListView<Module> unselectedTerm2Modules, reserveTerm2Modules;
	Label unselec2;
	Label reserve2;
	Label reserveamount2;
	TextField reservecredits2;
	private Button add2, remove2, confirm2;
	private ObservableList<Module> unselectedTerm2ModuleList, reserveTerm2ModuleList;

	public ReserveModulePane2() {

		//GridPane accordgridpane = new GridPane();
		//accordgrid.setPrefSize(700, 700);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20,20,20,20));
		//this.getChildren().addAll(accordgridpane);

		unselectedTerm2ModuleList = FXCollections.observableArrayList();
		reserveTerm2ModuleList = FXCollections.observableArrayList();


		VBox vbox1 = new VBox();
		Label unselec1 = new Label("Unselected Term 2 Modules");
		unselectedTerm2Modules = new ListView<Module>(unselectedTerm2ModuleList);
		unselectedTerm2Modules.setPrefSize(1000, 735);
		vbox1.setPadding(new Insets(20,20,20,20));
		vbox1.getChildren().addAll(unselec1, unselectedTerm2Modules);
		vbox1.prefWidthProperty().bind(this.widthProperty());


		add2 = new Button("Add");
		remove2 = new Button("Remove");
		confirm2 = new Button("Confirm");
		add2.setPrefSize(100, 0);
		remove2.setPrefSize(100, 0);
		confirm2.setPrefSize(100, 0);
		HBox buttons2 = new HBox();
		buttons2.setSpacing(10);
		buttons2.setAlignment(Pos.CENTER_LEFT);
		buttons2.setPadding(new Insets(5,5,5,5));
		buttons2.getChildren().addAll(add2,remove2,confirm2);

		//HBox lbl2 = new HBox();
		//Label reserveamount = new Label("Reserve 30 credits worth of term 2 modules");
		///lbl2.setSpacing(10);
		//lbl2.setAlignment(Pos.CENTER_RIGHT);
		//lbl.setPadding(new Insets(2,2,2,2));
		//lbl2.getChildren().addAll(reserveamount);


		HBox lbl2 = new HBox();
		Label reservecredlbl = new Label("Credits for Reserving Term 2 Modules: ");
		reservecredits2 = new TextField("0");
		reservecredits2.setEditable(false);
		reservecredits2.setPrefSize(60,40);
		lbl2.setSpacing(10);
		lbl2.setAlignment(Pos.CENTER_RIGHT);
		lbl2.setPadding(new Insets(2,2,2,2));
		lbl2.getChildren().addAll(reservecredlbl,reservecredits2);


		VBox vbox2 = new VBox();
		Label reserve2 = new Label("Reserve Term 2 Modules");
		reserveTerm2Modules = new ListView<Module>(reserveTerm2ModuleList);
		reserveTerm2Modules.setPrefSize(1000, 980);
		vbox2.setPadding(new Insets(20,20,20,20));
		vbox2.getChildren().addAll(reserve2,reserveTerm2Modules);
		vbox2.prefWidthProperty().bind(this.widthProperty());


		VBox leftside = new VBox(20,vbox1,lbl2);

		VBox rightside = new VBox(20,vbox2,buttons2);

		HBox term2accord = new HBox(20,leftside,rightside);

		this.getChildren().addAll(term2accord);
	}

	public void addTerm2ReserveUnselectedModules(Module m) {
		unselectedTerm2ModuleList.add(m);

	}

	public void addTerm2ReserveModules(Module m) {
		reserveTerm2ModuleList.add(m);
	}

	public Module getTerm2ReserveUnselectedModule() {
		return unselectedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public Module getReserveTerm2Module() {
		return reserveTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public ObservableList<Module> returnReserveUnselectedTerm2() {
		return unselectedTerm2ModuleList;
	}

	public ObservableList<Module> returnReserveTerm2() {
		return reserveTerm2ModuleList;
	}

	public ListView<Module> getReserveTerm2UnselectedModules() {
		return unselectedTerm2Modules;
	}
	public ListView<Module> getReserveTerm2Modules() {
		return reserveTerm2Modules;
	}		    

	public void setreservecredit2(int reservecredit2) { // get reserve credit 2 (term 2)
		reservecredits2.setText(String.valueOf(reservecredit2)); 
	}

	public String getreservecredit2() { // get term 2 reserve credits
		return reservecredits2.getText();
	}

	public ObservableList<Module> getEveryReserveTerm2Modules() {

		ObservableList<Module> m =	reserveTerm2Modules.getItems(); // get reserve term 2 modules

		return m;
	}
	
	public void clearSelection() {
		unselectedTerm2Modules.getSelectionModel().clearSelection();
		reserveTerm2Modules.getSelectionModel().clearSelection();
	}

	//event handling

	public void addTerm2ReserveAddHandler(EventHandler<ActionEvent> handler) {
		add2.setOnAction(handler);
	}

	public void addTerm2ReserveRemoveHandler(EventHandler<ActionEvent> handler) {
		remove2.setOnAction(handler);
	}

	public void addTerm2ReserveConfirmHandler(EventHandler<ActionEvent> handler) {
		confirm2.setOnAction(handler);

	}
}
