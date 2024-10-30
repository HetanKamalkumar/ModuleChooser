package view;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

//P2589546
public class ModuleChooserRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private SelectModulesViewPane smp;
	private ReserveModulesPane rmmp;
	private ReserveModulePane2 rmmp2;
	private Accordion acc;
	private OverviewSelectionPane osp;
	private TitledPane t3,t4;
	private ModuleChooserMenuBar mstmb;
	private TabPane tp;
	
	public ModuleChooserRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		smp = new SelectModulesViewPane();
		rmmp = new ReserveModulesPane();
		rmmp2 = new ReserveModulePane2();
		osp = new OverviewSelectionPane();
		//rmmp = 
		t3 = new TitledPane("Term 1 Modules", rmmp);
		t4 = new TitledPane("Term 2 Modules", rmmp2);
		t3.setPrefWidth(600);
		t4.setPrefWidth(600);
		
		acc = new Accordion();
		acc.setPadding(new Insets(20,20,20,20));
		acc.setPrefSize(600, 700);
		acc.getPanes().addAll(t3,t4);
		acc.setExpandedPane(t3);
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Modules", smp);
		Tab t3 = new Tab("Reserve Modules", acc);
		Tab t5 = new Tab("Overview Selection", osp);
		//Tab t3 = new Tab("Reserve Modules", );
		//Tab t3 = new Tab("Reserve Modules", rmmp);
		//Tab t4 = new Tab("Overview Selection", osp);
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t5);
		//this.getChildren().addAll(mstmb,tp);
		
		//create menu bar
		mstmb = new ModuleChooserMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	
	public SelectModulesViewPane getSelectModulePane() {
		return smp;
	}
	
	public ReserveModulesPane getReserveModulesPane() {
		return rmmp;
	}
	
	public ReserveModulePane2 getReserveModulePane2() {
		return rmmp2;
	}
	
	public ModuleChooserMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}
	
	public OverviewSelectionPane getOverviewSelectionPane() {
		return osp;
	}
	
	public void changePane() {
		acc.setExpandedPane(t4);
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}


