package com.example.mecanicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReparacionDAO {

    public void agregarReparacion(Reparacion reparacion) {
        String sql = "INSERT INTO Reparacion (vehiculo_id, descripcion, fecha, costo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reparacion.getVehiculoId());
            stmt.setString(2, reparacion.getDescripcion());
            stmt.setDate(3, java.sql.Date.valueOf(reparacion.getFecha()));
            stmt.setBigDecimal(4, reparacion.getCosto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reparacion> obtenerReparacionesPorVehiculo(int vehiculoId) {
        String sql = "SELECT * FROM Reparacion WHERE vehiculo_id = ?";
        List<Reparacion> reparaciones = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehiculoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reparacion reparacion = new Reparacion(
                    rs.getInt("vehiculo_id"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getBigDecimal("costo")
                );
                reparacion.setId(rs.getInt("id"));
                reparaciones.add(reparacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reparaciones;
    }
}
