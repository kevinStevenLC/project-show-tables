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
        // Enviando panel de control.
        controller.recivePanel(stage);
        // Enviando tablas de resultados y tablas de puntos
        controller.reciveDrivers(FxmlTableDrivers(), FxmlTableDriversPoints());
        controller.reciveConstructors(FxmlTableConstructors(), FxmlTableConstructorsPoints());

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
    public Stage FxmlTableDriversPoints() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/tableDriversPoints.fxml"));
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

    @SuppressWarnings("exports")
    public Stage FxmlTableConstructorsPoints() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/tableConstructorsPoints.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Consulta Constructores");

        return stage;
    }

    public static void main(String[] args) {
        launch();
    }

}
