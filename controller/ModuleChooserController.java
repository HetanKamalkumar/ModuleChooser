package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Schedule;
import model.Module;
import view.SelectModulesViewPane;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.ReserveModulePane2;
import view.ReserveModulesPane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;
import view.OverviewSelectionPane;

//P2589546
public class ModuleChooserController {

	//fields to be used throughout class
	private ModuleChooserRootPane view;
	private StudentProfile model;

	private CreateStudentProfilePane cspp;
	private SelectModulesViewPane smp;
	private ModuleChooserMenuBar mstmb;
	private ReserveModulesPane rmp;
	private ReserveModulePane2 rmp2;
	private OverviewSelectionPane osp;

	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;

		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		smp = view.getSelectModulePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		rmp = view.getReserveModulesPane();
		rmp2 = view.getReserveModulePane2();
		osp = view.getOverviewSelectionPane();
		//add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
		cspp.addCoursesToComboBox(generateAndGetCourses());
		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}


	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());

		smp.addTerm1AddHandler(new Add1Handler());
		smp.addTerm2AddHandler(new Add2Handler());
		smp.addTerm1RemoveHandler(new Remove1Handler());
		smp.addTerm2RemoveHandler(new Remove2Handler());
		smp.addResetHandler(new resetHandler());
		smp.addSubmitHandler(new submitHandler());

		rmp.addTerm1ReserveAddHandler(new ReserveAddHandler());
		rmp.addTerm1ReserveRemoveHandler(new ReserveRemoveHandler());
		rmp.addTerm1ReserveConfirmHandler(new ReserveConfirmHandler());


		rmp2.addTerm2ReserveRemoveHandler(new ReserveRemove2Handler());
		rmp2.addTerm2ReserveAddHandler(new ReserveAdd2Handler());
		rmp2.addTerm2ReserveConfirmHandler(new ReserveConfirm2Handler());

		osp.SaveOverviewHandler(new SaveOverviewHandler());
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addAboutHandler(new AboutHandler());
	}

	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			if(!cspp.getStudentEmail().matches("[a-zA-Z0-9._%=-]+@[a-zA-Z0-9.-]+\\.com"))  {
				alertDialogBuilder(AlertType.WARNING, "Email Error", "Is everything in the correct format..", "Add a valid email that contains a @ + ends with .com ");
				return;
			}

			if(!cspp.getStudentPnumber().matches("[P]+\\d{7}")) {
				alertDialogBuilder(AlertType.WARNING, "P-Number Error", "Is everything in the correct format..", "P-Number should contain P at the beginning and contain 7 numbers");
				return;
			}

			if(!cspp.getStudentName().getFirstName().matches("^[a-zA-Z]*$")) {
				alertDialogBuilder(AlertType.WARNING, "First Name Error", "Is everything in the correct format..", "Type a valid First Name correctly");
				return;
			}

			if(!cspp.getStudentName().getFamilyName().matches("^[a-zA-Z]*$")) {			
				alertDialogBuilder(AlertType.WARNING, "Surname Name Error", "Is everything in the correct format..", "Type a valid Surname Name correctly");
				return;
			}

			if(cspp.getStudentDate().equals(null)) {
				alertDialogBuilder(AlertType.WARNING, "Date Error", "Is everything in the correct format..", "Select a date");
				return;
			}

			//if(cspp.getSelectedCourse().equals(null)) {				
				//alertDialogBuilder(AlertType.WARNING, "Course Selection Error", "Is everything in the correct format..", "Select a course");
				//return;
			//}

			model.setStudentCourse(cspp.getSelectedCourse());
			model.setStudentName(cspp.getStudentName());
			model.setStudentCourse(cspp.getSelectedCourse());
			model.setStudentEmail(cspp.getStudentEmail());
			model.setStudentPnumber(cspp.getStudentPnumber());
			model.setSubmissionDate(cspp.getStudentDate());
          
			if (model.getStudentCourse().toString() == "Computer Science") {
				
				smp.setcredit1(30);
				smp.setcredit2(15);
			}

			else {
				
				smp.setcredit1(30);
				smp.setcredit2(30);
			}
			
            smp.clearListViews();
			populateListViews();
			view.changeTab(1);  

		}
	}

	private class Add1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			Module term1 = smp.getUnselectedTerm1Modules().getSelectionModel().getSelectedItem();
			int term1credits = Integer.parseInt(smp.getcredit1());

			if(term1 != null) {

				if( term1credits == 60) {
					alertDialogBuilder(AlertType.INFORMATION, "Max Amount Of Credits!!", "", "You can only have 60 credits in Term 1");
				}
				else {

					smp.clearSelection();
					smp.returnUnselectedTerm1().remove(term1);
					smp.setcredit1(term1credits + term1.getModuleCredits());
					smp.returnSelectedTerm1Modules().add(term1);
				}
			}
		}
	}

	private class Add2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			Module term2 = smp.getUnselectedTerm2Modules().getSelectionModel().getSelectedItem();
			int term2credits = Integer.parseInt(smp.getcredit2());

			if(term2 != null) {

				if( term2credits == 60) {
					alertDialogBuilder(AlertType.INFORMATION, "Max Amount Of Credits!!", "", "You can only have 60 credits in Term 2");
				}
				else {	
					smp.clearSelection();
					smp.returnUnselectedTerm2().remove(term2);
					smp.setcredit2(term2credits + term2.getModuleCredits());
					smp.returnSelectedTerm2Modules().add(term2);
				}
			}
		}
	}	

	private class Remove1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			Module term1 = smp.getSelectedTerm1Modules().getSelectionModel().getSelectedItem();
			int term1credits = Integer.parseInt(smp.getcredit1());

			if(term1 != null) {

				if(term1.isMandatory()) {
					alertDialogBuilder(AlertType.ERROR, "Selection Error", "", "You cannot remove a mandatory module");
				}
				else {	
					smp.clearSelection();
					smp.returnSelectedTerm1Modules().remove(term1);
					smp.setcredit1(term1credits - term1.getModuleCredits());
					smp.returnUnselectedTerm1().add(term1);
				}
			}
		}
	}	

	private class Remove2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			Module term2 = smp.getSelectedTerm2Modules().getSelectionModel().getSelectedItem();
			int term2credits = Integer.parseInt(smp.getcredit2());

			if(term2 != null) {

				if(term2.isMandatory()) {
					alertDialogBuilder(AlertType.ERROR, "Selection Error", "", "You cannot remove a mandatory module");
				}
				else {	
					smp.clearSelection();
					smp.returnSelectedTerm2Modules().remove(term2);
					smp.setcredit2(term2credits - term2.getModuleCredits());
					smp.returnUnselectedTerm2().add(term2);
				}
			}
		}
	}	

	private class resetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			smp.clearListViews();

			if (model.getStudentCourse().toString() == "Computer Science") {
				smp.setcredit1(30);
				smp.setcredit2(15);
			}
			else {
				smp.setcredit1(30);
				smp.setcredit2(30);
			}

			populateListViews();
			view.changeTab(1);  

		}
	}

	private class submitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			int credits = Integer.parseInt(smp.getcredit1());
			int credits2 = Integer.parseInt(smp.getcredit2());

			if(credits != 60 || credits2 != 60) {

				alertDialogBuilder(AlertType.ERROR, "Error", "", "You have not selected all modules and have credits left");
				return;
			}
			else {

				for(Module m: smp.returnUnselectedTerm1()) {
					rmp.addTerm1ReserveUnselectedModules(m);
				}
			}

			for(Module m: smp.returnUnselectedTerm2()) {
				rmp2.addTerm2ReserveUnselectedModules(m);
			}

			view.changeTab(2);
		}
	}

	private class ReserveAddHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {


			Module reserveterm1 = rmp.getReserveTerm1UnselectedModules().getSelectionModel().getSelectedItem();

			int reserveterm1credits = Integer.parseInt(rmp.getreservecredit1());

			if(reserveterm1 != null) {

				if( rmp.getReserveTerm1UnselectedModules().getItems().size() <= 3) {
					alertDialogBuilder(AlertType.ERROR, "Error", "", "You can only select one more (30 credits worth)");
				}
				else {	

					rmp.clearSelection();
					rmp.returnReserveUnselectedTerm1().remove(reserveterm1);
					rmp.setreservecredit1(reserveterm1credits + reserveterm1.getModuleCredits());
					rmp.returnReserveTerm1().add(reserveterm1);
				}
			}
		}
	}

	private class ReserveRemoveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {


			Module reserveterm1 = rmp.getReserveTerm1Modules().getSelectionModel().getSelectedItem();

			int reserveterm1credits = Integer.parseInt(rmp.getreservecredit1());

			rmp.clearSelection();
			rmp.returnReserveTerm1().remove(reserveterm1);
			rmp.setreservecredit1(reserveterm1credits - reserveterm1.getModuleCredits());
			rmp.returnReserveUnselectedTerm1().add(reserveterm1);

		}
	}

	private class ReserveRemove2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {


			Module reserveterm2 = rmp2.getReserveTerm2Modules().getSelectionModel().getSelectedItem();
			
			int reserveterm2credits = Integer.parseInt(rmp2.getreservecredit2());

					rmp2.clearSelection();
					rmp2.returnReserveTerm2().remove(reserveterm2);
					rmp2.setreservecredit2(reserveterm2credits - reserveterm2.getModuleCredits());
					rmp2.returnReserveUnselectedTerm2().add(reserveterm2);
				}
			}

	private class ReserveAdd2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {


			Module reserveterm2 = rmp2.getReserveTerm2UnselectedModules().getSelectionModel().getSelectedItem();
			int reserveterm2credits = Integer.parseInt(rmp2.getreservecredit2());

			if(reserveterm2 != null) {

				if( rmp2.getReserveTerm2UnselectedModules().getItems().size() <= 3) {
					alertDialogBuilder(AlertType.ERROR, "Error", "", "You can only select " +  "(30 credits worth)" + " of modules");
				}
				else {	
					rmp2.clearSelection();
					rmp2.returnReserveUnselectedTerm2().remove(reserveterm2);
					rmp2.setreservecredit2(reserveterm2credits + reserveterm2.getModuleCredits());
					rmp2.returnReserveTerm2().add(reserveterm2);
				}
			}
		}
	}

	private class ReserveConfirmHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			int reserveterm1credits = Integer.parseInt(rmp.getreservecredit1());
			
			if(reserveterm1credits != 30) {
				alertDialogBuilder(AlertType.ERROR, "Information Dialog", "", "You have not selected 30 credits worth of modules in Term 1");
				return;
			}
			else {
				for(Module m: rmp.returnReserveTerm1()) {
					model.addReservedModule(m);
				}
			}
			view.changePane();
		}
	}

	private class ReserveConfirm2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {


			int reserveterm2credits = Integer.parseInt(rmp2.getreservecredit2());
			
			if(reserveterm2credits != 30) {

				alertDialogBuilder(AlertType.ERROR, "Error", "", "You have not selected 30 credits worth of modules in Term 2");
				return;
			}
			else {
				for(Module m: rmp2.returnReserveTerm2()) {
					model.addReservedModule(m);
				}

				String profile = "";

				profile += "Course: " + model.getStudentCourse() + "\n" +
						 model.getStudentName() + "\n" + "P-Number: " + model.getStudentPnumber() + "\n" + 
						"Email: " + model.getStudentEmail() + "\n" + 
						"Date: " + model.getSubmissionDate() + "\n" +  "=====================\n";


				ObservableList<Module> selectedmodules =  smp.getEverySelectedModule();
				String selected = "";
				for(Module m: selectedmodules) {
					model.addSelectedModule(m);

					selected += "Module name: " + m.getModuleName() + ", Module Code: " + m.getModuleCode();				
					selected += "\nCredits: " + m.getModuleCredits() + ", Is this course mandatory? " + (m.isMandatory()?"Yes":"No")+ ", Schedule: " + m.getDelivery() + "\n\n";

				}


				ObservableList<Module> reservemodules =  rmp.getEveryReserveTerm1Modules();
				String reserve = "";
				for(Module m: reservemodules) {
					model.addSelectedModule(m);

					reserve += "Module name: " + m.getModuleName() + ", Module Code: " + m.getModuleCode();				
					reserve += "\nCredits: " + m.getModuleCredits() +  ", Schedule: " + m.getDelivery() + "\n\n";

				}

				ObservableList<Module> reservemodules2 =  rmp2.getEveryReserveTerm2Modules();
				for(Module m: reservemodules2) {
					model.addSelectedModule(m);

					reserve += "Module name: " + m.getModuleName() + ", Module Code: " + m.getModuleCode();				
					reserve += "\nCredits: " + m.getModuleCredits() + ", Schedule: " + m.getDelivery() + "\n\n";

				}

				osp.setOverviewprofile(profile);
				osp.setOverviewselected(selected);
				osp.setOverviewreserve(reserve);
				view.changeTab(3);
			}
		}
	}

	private class SaveOverviewHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			
			String profile = osp.getProfile();
			String selected = osp.getselected();
			String reserved = osp.getreserved();
			
			try (FileWriter overview = new FileWriter("saved_student_information.txt")) {
				PrintWriter overviewresults = new PrintWriter(overview);
				overviewresults.println(profile);
				overviewresults.println(selected);
				overviewresults.println(reserved);

				alertDialogBuilder(AlertType.CONFIRMATION, "Confirmation", "Save Successful", "saved_student_information.txt");
				return;

			} 
			catch (IOException E) {
				System.out.println("There is an Error in Saving");
				E.printStackTrace();
			}
			view.changeTab(0);
			osp.clearResults1();
			osp.clearResults2();
			osp.clearResults3();
		}
	}
	

	private class AboutHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "", "Module Chooser App v1.0 \nCreate a profile, select your modules, select your reserve modules and save your selection");



		}
	}

	private void alertDialogBuilder(AlertType type, String title, String headerText, String contentText) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public void populateListViews() {

		if(model.getStudentCourse().getCourseName() == "Computer Science") {

			for(Module m:generateAndGetCourses()[0].getAllModulesOnCourse()) {

				if(m.getDelivery() == Schedule.TERM_1  && m.isMandatory()) {
					smp.addSelectedTerm1Modules(m);

				}

				else {
					if(m.getDelivery() == Schedule.TERM_1  && !m.isMandatory()) {
						smp.addUnselectedTerm1Modules(m);

					}
				}

				if(m.getDelivery() == Schedule.TERM_2) {
					if(m.isMandatory()) {
						smp.addSelectedTerm2Modules(m); 
					} else {
						smp.addUnselectedTerm2Modules(m);
					}
				}

				if(m.getDelivery() == Schedule.YEAR_LONG) {
					smp.addSelectedYearLongModules(m);

				}
			}
		} 

		else {

			for(Module m: generateAndGetCourses()[1].getAllModulesOnCourse()) {

				if(m.getDelivery() == Schedule.YEAR_LONG) {
					smp.addSelectedYearLongModules(m);
				}

				if(m.getDelivery() == Schedule.TERM_1) {
					if (m.isMandatory()) {
						smp.addSelectedTerm1Modules(m); 
					} else {	 
						smp.addUnselectedTerm1Modules(m);
					}
				}


				if(m.getDelivery() == Schedule.TERM_2) {
					if(m.isMandatory()) {
						smp.addSelectedTerm2Modules(m);
					} else {
						smp.addUnselectedTerm2Modules(m); 
					}
				}
			}
		}
	}

	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}
}
