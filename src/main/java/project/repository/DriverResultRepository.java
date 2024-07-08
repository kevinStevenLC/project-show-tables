package project.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import project.model.DbConexion;
import project.model.DriverResult;

public class DriverResultRepository {

	public List<DriverResult> getResultByYear(int year) {

		List<DriverResult> results = new ArrayList<DriverResult>();

		try {
			// Establecer la conexion
			DbConexion db_conexion = new DbConexion();
			Connection conn = db_conexion.EstablecerConexion("test_database", "postgres", "593321", "postgresql");

			// Ejecutar la consulta
			String sql = "SELECT\n"
					+ "    r.year,\n"
					+ "    d.forename,\n"
					+ "    d.surname,\n"
					+ "    COUNT(CASE WHEN res.position = 1 THEN 1 END) AS wins,\n"
					+ "    SUM(res.points) AS total_points,\n"
					+ "    RANK() OVER (PARTITION BY r.year ORDER BY SUM(res.points) DESC) AS season_rank\n"
					+ "FROM\n"
					+ "    results res\n"
					+ "JOIN\n"
					+ "    races r ON res.race_id = r.race_id\n"
					+ "JOIN\n"
					+ "    drivers d ON res.driver_id = d.driver_id\n"
					+ "\n"
					+ "WHERE r.year = ? \n"
					+ "GROUP BY\n"
					+ "    r.year, d.driver_id, d.forename, d.surname\n"
					+ "ORDER BY\n"
					+ "    r.year, season_rank;";

			// Crear una sentencia
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, year);

			ResultSet rs = statement.executeQuery();

			// Procesar los resultados
			while (rs.next()) {
				String forename = rs.getString("forename");
				String surname = rs.getString("surname");
				int wins = rs.getInt("wins");
				int total_points = rs.getInt("total_points");
				int season_rank = rs.getInt("season_rank");

				DriverResult result = new DriverResult(forename + " " + surname, wins, total_points, season_rank);
				results.add(result);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return results;

	}

	public static void main(String[] args) {
		DriverResultRepository drv = new DriverResultRepository();
		List<DriverResult> ls_d = drv.getResultByYear(2009);

		// Imprimir encabezado de la tabla
		System.out.printf("%-20s %-10s %-10s %8s%n", "DriverName", "Wins", "Total Points", "Rank");

		// Imprimir cada registro con formato
		for (DriverResult rs : ls_d) {
			System.out.printf("%-20s %-10d %-15d  %-10d%n",
					rs.getDriverName(),
					rs.getWins(),
					rs.getTotalPoints(),
					rs.getRank());
		}
	}
}
