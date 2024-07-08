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
	Stage stageConstructors;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void recivePanel(@SuppressWarnings("exports") Stage stage) {
		this.stagePanel = stage;
	}

	public void reciveDrivers(@SuppressWarnings("exports") Stage stage) {
		this.stageDrivers = stage;
	}

	public void reciveConstructors(@SuppressWarnings("exports") Stage stage) {
		this.stageConstructors = stage;
	}

	@FXML
	void showTableConstructors() {
		stageConstructors.show();
	}

	@FXML
	void showTableDrivers() throws IOException {
		stageDrivers.show();

	}

}
