/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoevaluado_hotel;

import javax.swing.JOptionPane;

/**
 *
 * @author Laboratorio
 */
public class habitaciones {
     private static String[][][] habitaciones;
    private static final int NumPisos = 3;
    private static final int HabitacionesPorPiso = 4;
//Se inicia el primer metodo 
    public static void inicializarHabitaciones() {
        habitaciones = new String[NumPisos][HabitacionesPorPiso][4];

// Piso 1
        habitaciones[0][0] = new String[]{"101", "Libre", "Simple", "30"};
        habitaciones[0][1] = new String[]{"102", "Libre", "Doble", "30"};
        habitaciones[0][2] = new String[]{"103", "Libre", "Simple", "30"};
        habitaciones[0][3] = new String[]{"104", "Sucia", "Doble", "40"};

// Piso 2
        habitaciones[1][0] = new String[]{"201", "Libre", "Simple", "40"};
        habitaciones[1][1] = new String[]{"202", "Libre", "Doble", "40"};
        habitaciones[1][2] = new String[]{"203", "Ocupada", "Simple", "40"};
        habitaciones[1][3] = new String[]{"204", "Sucia", "Doble", "40"};

// Piso 3
        habitaciones[2][0] = new String[]{"301", "Libre", "Doble", "50"};
        habitaciones[2][1] = new String[]{"302", "Libre", "Doble", "60"};
        habitaciones[2][2] = new String[]{"303", "Libre", "Simple", "40"};
        habitaciones[2][3] = new String[]{"304", "Sucia", "Simple", "40"};
    }
// Segundo metodo 
    public static void mostrarMenuPrincipal() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                    "HOTEL\n\n"
                    + "1. Visualizar Habitaciones\n"
                    + "2. Modificar Información de Habitación\n"
                    + "3. Resumen del Estado del Hotel\n"
                    + "4. Salir\n\n"
                    + "Seleccione una opción:"
            );

            if (opcion == null) {
                opcion = "4";
            }

            switch (opcion) {
                case "1":
                    visualizarHabitaciones();
                    break;
                case "2":
                    modificarInformacionHabitacion();
                    break;
                case "3":
                    resumenEstadoHotel();
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, intente de nuevo.");
            }
        } while (!opcion.equals("4"));
    }
//Esta instruccion es para mostrar la configuracion ya predeterminada
    public static void visualizarHabitaciones() {
        String mensaje = "Estado de Habitaciones:\n\n";
        mensaje += "Piso Habitación Estado Tipo Precio\n";
        mensaje += "------------------------------------------------------------\n";

        for (int i = 0; i < NumPisos; i++) {
            mensaje += "Piso " + (i + 1) + "\n";
            for (int j = 0; j < HabitacionesPorPiso; j++) {
                mensaje += " "
                        + habitaciones[i][j][0] + " "
                        + habitaciones[i][j][1] + " "
                        + habitaciones[i][j][2] + " "
                        + habitaciones[i][j][3] + "$\n";
            }
            mensaje += "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }
//Instruccion para cuando se modifique alguna habitacion y ese cambio se muestre como predeterminada 
    public static void modificarInformacionHabitacion() {
        String numHabitacionStr = JOptionPane.showInputDialog("Ingrese el número de la habitación a modificar (ej. 101):");
        if (numHabitacionStr == null || numHabitacionStr.isEmpty()) {
            return;
        }

        for (int i = 0; i < NumPisos; i++) {
            for (int j = 0; j < HabitacionesPorPiso; j++) {
                if (habitaciones[i][j][0].equals(numHabitacionStr)) {

                    String nuevoEstado = JOptionPane.showInputDialog(
                            "Habitación " + numHabitacionStr + " - Estado actual: " + habitaciones[i][j][1] + "\n"
                            + "Ingrese el nuevo estado (Libre, Ocupada, Sucia):"
                    );
                    if (nuevoEstado != null && !nuevoEstado.isEmpty()) {
                        habitaciones[i][j][1] = nuevoEstado;
                    }

                    String nuevoTipo = JOptionPane.showInputDialog(
                            "Habitación " + numHabitacionStr + " - Tipo actual: " + habitaciones[i][j][2] + "\n"
                            + "Ingrese el nuevo tipo (Simple, Doble):"
                    );
                    if (nuevoTipo != null && !nuevoTipo.isEmpty()) {
                        habitaciones[i][j][2] = nuevoTipo;
                    }

                    String nuevoPrecioStr = JOptionPane.showInputDialog(
                            "Habitación " + numHabitacionStr + " - Precio actual: " + habitaciones[i][j][3] + "$\n"
                            + "Ingrese el nuevo precio por noche:"
                    );
                    if (nuevoPrecioStr != null && !nuevoPrecioStr.isEmpty()) {
                        try {
                            Double.parseDouble(nuevoPrecioStr);
                            habitaciones[i][j][3] = nuevoPrecioStr;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Precio inválido. Debe ser un número.");
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Información de la habitación " + numHabitacionStr + " actualizada.");
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Habitación " + numHabitacionStr + " no encontrada.");
    }
//Esta de aca es para hacer los calculos
    public static void resumenEstadoHotel() {
        int libres = 0;
        int ocupadas = 0;
        int sucias = 0;
        double ganancia = 0.0;
        int total = NumPisos * HabitacionesPorPiso;

        for (int i = 0; i < NumPisos; i++) {
            for (int j = 0; j < HabitacionesPorPiso; j++) {
                String estado = habitaciones[i][j][1];
                String precioStr = habitaciones[i][j][3];

                if (estado.equals("Libre")) {
                    libres++;
                } else if (estado.equals("Ocupada")) {
                    ocupadas++;
                    try {
                        ganancia += Double.parseDouble(precioStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Precio inválido en la habitación " + habitaciones[i][j][0]);
                    }
                } else if (estado.equals("Sucia")) {
                    sucias++;
                }
            }
        }

        double porcentajeLibres = (double) libres / total * 100;
        double porcentajeOcupadas = (double) ocupadas / total * 100;
        double porcentajeSucias = (double) sucias / total * 100;

        String mensaje = "Resumen del Estado del Hotel:\n\n";
        mensaje += "Total de Habitaciones: " + total + "\n";
        mensaje += "Habitaciones Libres: " + libres + " (" + Math.round(porcentajeLibres * 100.0) / 100.0 + "%)\n";
        mensaje += "Habitaciones Ocupadas: " + ocupadas + " (" + Math.round(porcentajeOcupadas * 100.0) / 100.0 + "%)\n";
        mensaje += "Habitaciones Sucias: " + sucias + " (" + Math.round(porcentajeSucias * 100.0) / 100.0 + "%)\n";
        mensaje += "\nGanancia Actual del Hotel: " + Math.round(ganancia * 100.0) / 100.0 + "$";

        JOptionPane.showMessageDialog(null, mensaje);
    }
}
