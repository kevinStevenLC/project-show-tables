package project.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import project.model.DbConexion;
import project.model.ConstructorResult;

public class ConstructorResultRepository {

    public List<ConstructorResult> getResultByYear(int year) {

        List<ConstructorResult> results = new ArrayList<>();

        try {
            // Establecer la conexion
            DbConexion db_conexion = new DbConexion();
            Connection conn = db_conexion.EstablecerConexion("test_database", "postgres", "593321", "postgresql");

            // Ejecutar la consulta
            String sql = "SELECT\n"
                    + "    r.year,\n"
                    + "    c.name AS constructorName,\n"
                    + "    COUNT(CASE WHEN res.position = 1 THEN 1 END) AS wins,\n"
                    + "    SUM(cr.points) AS total_points,\n"
                    + "    RANK() OVER (PARTITION BY r.year ORDER BY SUM(cr.points) DESC) AS season_rank\n"
                    + "FROM\n"
                    + "    results res\n"
                    + "JOIN\n"
                    + "    races r ON res.race_id = r.race_id\n"
                    + "JOIN\n"
                    + "    constructor_results cr ON res.constructor_id = cr.constructor_id AND res.race_id = cr.race_id\n"
                    + "JOIN\n"
                    + "    constructors c ON res.constructor_id = c.constructor_id\n"
                    + "WHERE\n"
                    + "    r.year = ? \n"
                    + "GROUP BY\n"
                    + "    r.year, c.name\n"
                    + "ORDER BY\n"
                    + "    r.year, season_rank;";

            // Crear una sentencia
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, year);

            ResultSet rs = statement.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                String constructorName = rs.getString("constructorName");
                int wins = rs.getInt("wins");
                int total_points = rs.getInt("total_points");
                int season_rank = rs.getInt("season_rank");

                ConstructorResult result = new ConstructorResult(constructorName, wins, total_points, season_rank);
                results.add(result);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return results;
    }

    public static void main(String[] args) {
        ConstructorResultRepository repository = new ConstructorResultRepository();
        List<ConstructorResult> results = repository.getResultByYear(2009);

        // Imprimir encabezado de la tabla
        System.out.printf("%-20s %-10s %-10s %8s%n", "ConstructorName", "Wins", "Total Points", "Rank");

        // Imprimir cada registro con formato
        for (ConstructorResult rs : results) {
            System.out.printf("%-20s %-10d %-15d  %-10d%n",
                    rs.getConstructorName(),
                    rs.getWins(),
                    rs.getTotalPoints(),
                    rs.getRank());
        }
    }
}
