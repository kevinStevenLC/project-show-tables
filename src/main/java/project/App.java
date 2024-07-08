package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.controller.PanelController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/pagePanel.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Panel de control");

        PanelController controller = loader.getController();
        // Enviando los 3 estados.
        controller.recivePanel(stage);
        controller.reciveDrivers(FxmlTableDrivers());
        controller.reciveConstructors(FxmlTableConstructors());

        stage.show();
    }

    @SuppressWarnings("exports")
    public Stage FxmlTableDrivers() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/tableDrivers.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Consulta Conductores");

        return stage;
    }

    @SuppressWarnings("exports")
    public Stage FxmlTableConstructors() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/tableConstructors.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Consulta Constructores");

        return stage;
    }

    public static void main(String[] args) {
        launch();
    }

}