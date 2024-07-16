package project.controller;

import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.stage.Stage;

public class PanelController implements Initializable {

	Stage stagePanel;
	Stage stageDrivers;
	Stage stageDriversPoints;
	Stage stageConstructors;
	Stage stageConstructorsPoints;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void recivePanel(@SuppressWarnings("exports") Stage stage) {
		this.stagePanel = stage;
	}

	public void reciveDrivers(@SuppressWarnings("exports") Stage stage, @SuppressWarnings("exports") Stage stage_points) {
		this.stageDrivers = stage;
		this.stageDriversPoints = stage_points;
	}

	public void reciveConstructors(@SuppressWarnings("exports") Stage stage,
			@SuppressWarnings("exports") Stage stage_points) {
		this.stageConstructors = stage;
		this.stageConstructorsPoints = stage_points;
	}

	@FXML
	void showTableConstructors() {
		stageConstructors.show();
	}

	@FXML
	void showTableConstructorsPoints() {
		stageConstructorsPoints.show();
	}

	@FXML
	void showTableDrivers() throws IOException {
		stageDrivers.show();

	}

	@FXML
	void showTableDriversPoints() {
		stageDriversPoints.show();
	}
}
