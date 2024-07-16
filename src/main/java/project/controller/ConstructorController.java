package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.model.ConstructorResult;
import project.repository.ConstructorResultRepository;

public class ConstructorController implements Initializable {

	@FXML
	private TableColumn<ConstructorResult, String> ColumnConstructorName;

	@FXML
	private TableColumn<ConstructorResult, Integer> ColumnRank;

	@FXML
	private TableColumn<ConstructorResult, Integer> ColumnTotalPoints;

	@FXML
	private TableColumn<ConstructorResult, Integer> ColumnWins;

	@FXML
	private ComboBox<Integer> selectionYear;

	@FXML
	private TableView<ConstructorResult> tableConstructors;

	@FXML
	void selectionMethod(ActionEvent event) {
		int selectedYear = selectionYear.getValue();
		System.out.println("Year selected: " + selectedYear);
		// Limpiar la tabla antes de cargar nuevos datos
		tableConstructors.getItems().clear();
		cargarConstructores(selectedYear);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Agregando Opcioens
		for (int year = 1956; year <= 2019; year++) {
			selectionYear.getItems().add(year);
		}

		cargarConstructores(2004);

		// valor default
		selectionYear.setValue(2004);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	void cargarConstructores(int año) {
		this.ColumnConstructorName.setCellValueFactory(new PropertyValueFactory("constructorName"));
		this.ColumnRank.setCellValueFactory(new PropertyValueFactory("rank"));
		this.ColumnTotalPoints.setCellValueFactory(new PropertyValueFactory("totalPoints"));
		this.ColumnWins.setCellValueFactory(new PropertyValueFactory("wins"));

		ConstructorResultRepository objConstructorResultRepository = new ConstructorResultRepository();
		List<ConstructorResult> ls_c = objConstructorResultRepository.getResultByYear(año);
		for (ConstructorResult rs : ls_c) {
			tableConstructors.getItems().add(rs);
		}

	}

}
