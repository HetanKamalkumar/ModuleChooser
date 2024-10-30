package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//P2589546
public class OverviewSelectionPane extends GridPane {

	private TextArea profile;
	private TextArea selected;
	private TextArea reserved;
	private Button save;

	public OverviewSelectionPane() {

		//this.setAlignment(Pos.CENTER);

		VBox v1 = new VBox();
		profile = new TextArea("Profile will appear here");
		profile.setEditable(false);
		profile.setPrefSize(1800, 250);
		v1.getChildren().addAll(profile);
		v1.setPadding(new Insets(40,40,40,40));
		v1.prefWidthProperty().bind(this.widthProperty());

		this.add(v1, 0, 1);

		HBox v2 = new HBox();
		selected = new TextArea("Selected modules will appear here");
		selected.setEditable(false);

		reserved = new TextArea("Reserved modules will appear here");
		reserved.setEditable(false);

		selected.setPrefSize(900, 400);
		reserved.setPrefSize(900, 400);
		v2.getChildren().addAll(selected, reserved);
		v2.setSpacing(40);
		v2.setPadding(new Insets(40,40,40,40));
		this.add(v2, 0, 2);


		VBox v3 = new VBox();
		save = new Button("Save Overview");
		save.setPrefSize(120, 0);
		v3.getChildren().addAll(save);
		v3.setPadding(new Insets(20,20,20,20));
		v3.setAlignment(Pos.BOTTOM_CENTER);
		this.add(v3, 0, 3);
	}

	public TextArea returnOverviewReserved() {
		return reserved;
	}

	public void setOverviewprofile(String info) {
		profile.setText(info);
	}

	public void setOverviewselected(String info2) {
		selected.setText(info2);
	}

	public void setOverviewreserve(String info3) {
		reserved.setText(info3);
	}

	public String getProfile() {
		return profile.getText();
	}

	public String getselected() {
		return selected.getText();
	}

	public String getreserved() {
		return reserved.getText();
	}
	
	public void clearResults1() {
		profile.setText(" ");
	}

	public void clearResults2() {
		selected.setText(" ");
	}

	public void clearResults3() {
		reserved.setText(" ");
	}
	

	//event handling 

	public void SaveOverviewHandler(EventHandler<ActionEvent> handler) {
		save.setOnAction(handler);
	}

}


