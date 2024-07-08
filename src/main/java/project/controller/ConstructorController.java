package project.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConstructorController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private TableColumn<?, ?> ColumnConstructorName;

	@FXML
	private TableColumn<?, ?> ColumnRank;

	@FXML
	private TableColumn<?, ?> ColumnTotalPoints;

	@FXML
	private TableColumn<?, ?> ColumnWins;

	@FXML
	private ComboBox<?> selectionYear;

	@FXML
	private TableView<?> tableConstructors;

	@FXML
	void selectionMethod(ActionEvent event) {

	}

}
