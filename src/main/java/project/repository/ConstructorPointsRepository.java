package project.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import project.model.DbConexion;
import project.model.ConstructorPoints;

public class ConstructorPointsRepository {

    public List<ConstructorPoints> getConstructorTotalPoints() {
        List<ConstructorPoints> constructorList = new ArrayList<>();

        try {
            // Establecer la conexión
            DbConexion dbConexion = new DbConexion();
            Connection conn = dbConexion.EstablecerConexion("test_database", "postgres", "593321", "postgresql");

            // Ejecutar la consulta
            String sql = "SELECT d.name, " +
                    "SUM(r.points) AS total_points " +
                    "FROM results as r " +
                    "JOIN constructors as d ON r.constructor_id = d.constructor_id " +
                    "GROUP BY d.name " +
                    "ORDER BY total_points DESC " +
                    "LIMIT 50;";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                String name = rs.getString("name");
                int totalPoints = rs.getInt("total_points");

                // Crear objeto ConstructorPoints usando el constructor adecuado
                ConstructorPoints constructor = new ConstructorPoints(name, totalPoints);
                constructorList.add(constructor);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return constructorList;
    }

    public static void main(String[] args) {
        ConstructorPointsRepository constructorPointsRepository = new ConstructorPointsRepository();
        List<ConstructorPoints> constructorList = constructorPointsRepository.getConstructorTotalPoints();

        // Imprimir encabezado de la tabla
        System.out.printf("%-20s %-10s%n", "Name", "Total Points");

        // Imprimir cada registro con formato
        for (ConstructorPoints constructor : constructorList) {
            System.out.printf("%-20s %-10d%n", constructor.getName(), constructor.getPoints());
        }
    }
}