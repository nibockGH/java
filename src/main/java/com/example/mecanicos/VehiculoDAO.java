package com.example.mecanicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public void agregarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculo (patente, marca, modelo, anio) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getPatente());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getAnio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehiculo obtenerVehiculoPorPatente(String patente) {
        String sql = "SELECT * FROM Vehiculo WHERE patente = ?";
        Vehiculo vehiculo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vehiculo = new Vehiculo(
                    rs.getString("patente"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio")
                );
                vehiculo.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculo;
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        String sql = "SELECT * FROM Vehiculo";
        List<Vehiculo> vehiculos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getString("patente"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio")
                );
                vehiculo.setId(rs.getInt("id"));
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }
}
