package project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.model.DriversPoints;
import project.repository.DriversPointsRepository;

public class ConductorDriversController implements Initializable {

    @FXML
    private TableColumn<DriversPoints, String> ConductorNombres;

    @FXML
    private TableColumn<DriversPoints, Integer> TotalPoints;

    @FXML
    private TableView<DriversPoints> tableDrivers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        ConductorNombres.setCellValueFactory(new PropertyValueFactory<>("forename"));
        TotalPoints.setCellValueFactory(new PropertyValueFactory<>("points"));

        // Cargar los datos en la tabla
        loadDriversData();
    }

    private void loadDriversData() {
        DriversPointsRepository driversRepository = new DriversPointsRepository();
        List<DriversPoints> driversList = driversRepository.getDriversTotalPoints();

        // Limpiar la tabla antes de agregar nuevos datos
        if (tableDrivers != null) {
            tableDrivers.getItems().clear();
            // Agregar los datos a la tabla
            tableDrivers.getItems().addAll(driversList);
        }
    }
}