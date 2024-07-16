package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.model.ConstructorPoints;
import project.repository.ConstructorPointsRepository;

public class ConstructorPointsController implements Initializable {

    @FXML
    private TableColumn<ConstructorPoints, String> ConductorNombresConstructor;

    @FXML
    private TableColumn<ConstructorPoints, Integer> TotalPointsConstructor;

    @FXML
    private TableView<ConstructorPoints> tableConstructor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        ConductorNombresConstructor.setCellValueFactory(new PropertyValueFactory<>("name"));
        TotalPointsConstructor.setCellValueFactory(new PropertyValueFactory<>("points"));

        // Cargar los datos en la tabla
        loadConstructorsData();
    }

    private void loadConstructorsData() {
        ConstructorPointsRepository constructorPointsRepository = new ConstructorPointsRepository();
        List<ConstructorPoints> constructorList = constructorPointsRepository.getConstructorTotalPoints();

        // Limpiar la tabla antes de agregar nuevos datos
        if (tableConstructor != null) {
            tableConstructor.getItems().clear();
            // Agregar los datos a la tabla
            tableConstructor.getItems().addAll(constructorList);
        }
    }
}