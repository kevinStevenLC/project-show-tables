package project.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import project.model.DbConexion;
import project.model.DriversPoints;

public class DriversPointsRepository {

    public List<DriversPoints> getDriversTotalPoints() {
        List<DriversPoints> driversList = new ArrayList<>();

        try {
            // Establecer la conexión
            DbConexion dbConexion = new DbConexion();
            Connection conn = dbConexion.EstablecerConexion("test_database", "postgres", "593321", "postgresql");

            // Ejecutar la consulta
            String sql = "SELECT " +
                    "    d.forename || ' ' || d.surname AS driver_name, " +
                    "    SUM(r.points) AS total_points " +
                    "FROM " +
                    "    results r " +
                    "JOIN " +
                    "    drivers d ON r.driver_id = d.driver_id " +
                    "GROUP BY " +
                    "    d.forename, d.surname " +
                    "ORDER BY " +
                    "    total_points DESC " +
                    "LIMIT 50;";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                String forename = rs.getString("driver_name");
                int totalPoints = rs.getInt("total_points");

                // Crear objeto Drivers usando el constructor adecuado
                DriversPoints driver = new DriversPoints(forename, totalPoints);
                driversList.add(driver);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return driversList;
    }

    public static void main(String[] args) {
        DriversPointsRepository driversRepository = new DriversPointsRepository();
        List<DriversPoints> driversList = driversRepository.getDriversTotalPoints();

        // Imprimir encabezado de la tabla
        System.out.printf("%-20s %-10s%n", "Forename", "Total Points");

        // Imprimir cada registro con formato
        for (DriversPoints driver : driversList) {
            System.out.printf("%-20s %-10d%n", driver.getForename(), driver.getPoints());
        }
    }
}