package com.example.mecanicos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        ReparacionDAO reparacionDAO = new ReparacionDAO();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar un nuevo vehículo");
            System.out.println("2. Registrar una nueva reparación");
            System.out.println("3. Consultar historial de reparaciones por patente");
            System.out.println("4. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la patente del vehículo:");
                    String patente = scanner.nextLine();
                    System.out.println("Ingrese la marca del vehículo:");
                    String marca = scanner.nextLine();
                    System.out.println("Ingrese el modelo del vehículo:");
                    String modelo = scanner.nextLine();
                    System.out.println("Ingrese el año del vehículo:");
                    int anio = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    Vehiculo vehiculo = new Vehiculo(patente, marca, modelo, anio);
                    vehiculoDAO.agregarVehiculo(vehiculo);
                    System.out.println("Vehículo registrado correctamente.");
                    break;

                case 2:
                    System.out.println("Ingrese la patente del vehículo:");
                    patente = scanner.nextLine();
                    vehiculo = vehiculoDAO.obtenerVehiculoPorPatente(patente);
                    if (vehiculo == null) {
                        System.out.println("Vehículo no encontrado.");
                        break;
                    }

                    System.out.println("Ingrese la descripción de la reparación:");
                    String descripcion = scanner.nextLine();
                    System.out.println("Ingrese la fecha de la reparación (YYYY-MM-DD):");
                    LocalDate fecha = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ingrese el costo de la reparación:");
                    BigDecimal costo = scanner.nextBigDecimal();
                    scanner.nextLine(); // Consumir el salto de línea

                    Reparacion reparacion = new Reparacion(vehiculo.getId(), descripcion, fecha, costo);
                    reparacionDAO.agregarReparacion(reparacion);
                    System.out.println("Reparación registrada correctamente.");
                    break;

                case 3:
                    System.out.println("Ingrese la patente del vehículo:");
                    patente = scanner.nextLine();
                    vehiculo = vehiculoDAO.obtenerVehiculoPorPatente(patente);
                    if (vehiculo == null) {
                        System.out.println("Vehículo no encontrado.");
                        break;
                    }

                    List<Reparacion> reparaciones = reparacionDAO.obtenerReparacionesPorVehiculo(vehiculo.getId());
                    if (reparaciones.isEmpty()) {
                        System.out.println("No se encontraron reparaciones para este vehículo.");
                    } else {
                        for (Reparacion rep : reparaciones) {
                            System.out.println("Reparación ID: " + rep.getId());
                            System.out.println("Descripción: " + rep.getDescripcion());
                            System.out.println("Fecha: " + rep.getFecha());
                            System.out.println("Costo: " + rep.getCosto());
                            System.out.println("------------------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
