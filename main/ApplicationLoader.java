 
package main;

//P2589546
import controller.ModuleChooserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.StudentProfile;
import view.ModuleChooserRootPane;

public class ApplicationLoader extends Application {

	private ModuleChooserRootPane view;
	
	
	@Override
	public void init() {
		//create view and model and pass their references to the controller
		view = new ModuleChooserRootPane();
		StudentProfile model = new StudentProfile();
		new ModuleChooserController(view, model);	
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//sets min width and height for the stage window
		stage.setMinWidth(550); 
		stage.setMinHeight(550);
		
		stage.setTitle("Final Year Module Selection Tool");
		
		

		stage.setScene(new Scene(view));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
