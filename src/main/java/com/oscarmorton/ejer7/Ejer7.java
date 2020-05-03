package com.oscarmorton.ejer7;

import com.oscarmorton.utils.Lib;


import java.util.Scanner;

public class Ejer7 {
    /**
     * TODO
     * INVESTIGATE UUID
     * MAKE ZONES AND CHARS
     *
     *
     *
     *
      */

    private Scanner lector;

    public Ejer7( int nFilas, int nColumnas){
        Estadio estadio = new Estadio(3,"javea", 5, nFilas, nColumnas);
        estadio.crearAsientosAleatorios();
        estadio.imprimeEstadio();

        int opcion = -1;
        do {
            opcion = menuPrincipal();
            switch (opcion){
                case 1:
                    System.out.println("Nuevo partido:");
                    break;
                case 2:
                    System.out.println("Gestion de entradas");
                    break;
                case 3:
                    System.out.println("Jugar partida"); // Simplente pide la fecha del partido y juega la loteria con los asientos reservados.
                    break;
                case 0:
                    System.out.println("Hasta pronto!");
                    break;



            }
        }while (opcion != 0);


    }


    /**
     * Imprime el menu de gestiones
     * @return El opcion seleccionado
     */
    public int menuGestiones() {
        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("************************************");
            System.out.println("*     GESTION ESTADIO DE FUTBOL    *");
            System.out.println("************************************");
            System.out.println("1. Venta de entradas\n" +
                    "2. Devolver una entrada\n" +
                    "3. Listado de localidades ocupadas\n" +
                    "4. Listado de localidades libres\n" +
                    "5. Mostrar recaudación del partido\n" +
                    "0. Volver al menú principal\n");

            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 5) {
                    System.out.println("Elija una opción del menú [0-5]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 5);
        return opcion;

    }



    /**
     * Imprime el menu principal
     * @return El opcion seleccionado
     */
    public int menuPrincipal() {
        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("************************************");
            System.out.println("*     GESTION ESTADIO DE FUTBOL    *");
            System.out.println("************************************");
            System.out.println("1. Nuevo partido\n" +
                    "2. Gestión de entradas\n" +
                    "3. Jugar partida\n" +
                    "0. Salir");

            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 2) {
                    System.out.println("Elija una opción del menú [0-3]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 2);
        return opcion;

    }
}
