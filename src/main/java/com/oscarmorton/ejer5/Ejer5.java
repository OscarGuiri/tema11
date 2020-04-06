package com.oscarmorton.ejer5;

import com.oscarmorton.utils.Lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejer5 {
    private Scanner lector;
    private ArrayList<ItemBlock> blocks;

    // LOS ITEM BLOCKES:


    public Ejer5(){
        blocks = new ArrayList<>();
     for(int i = 0; i < 7; i++) {
         blocks.add(new ItemBlock());
     }


        int opcion = 0;
        System.out.println("INVENARIO MINECRAFT");
        do {
            opcion = menuPrincipal();
            switch (opcion){
                case 1:
                    Lib.limpiarPantalla();
                    System.out.println("Añadir inventario:");
                break;
                case 2:
                    Lib.limpiarPantalla();
                    System.out.println("Eliminar inventarip");
                    break;
                case 3:
                    Lib.limpiarPantalla();
                    System.out.println("Mostrar Inventario");
                    System.out.println(mostrarInventario());
                    Lib.pausa();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }while(opcion != 0);

    }
    public int menuPrincipal() {

        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("*************************");
            System.out.println("*        MINECRAFT       *");
            System.out.println("*   SISTEMA DE INVENTARIO  *");
            System.out.println("*************************");
            System.out.println("1. Añadir al inventario\n");
            System.out.println("2. Eliminar del inventario");
            System.out.println("3. Mostrar inventario");
            System.out.println("---------------------");
            System.out.println("0. Salir\n");
            System.out.println("Elija una opción: ");
            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 3) {
                    System.out.println("Elija una opción del menú [0-3]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 3);
        return opcion;
    }

        public String mostrarInventario(){
            return String.format("| %-10s","----------------------------------------------------------------------------------------|\n") +
                    String.format("| %-10s",blocks.get(0).getNombre()) +
                    String.format(" | %-10s",blocks.get(1).getNombre()) +
                    String.format(" | %-10s",blocks.get(2).getNombre()) +
                    String.format(" | %-10s",blocks.get(3).getNombre()) +
                    String.format(" | %-10s",blocks.get(4).getNombre()) +
                    String.format(" | %-10s",blocks.get(5).getNombre()) +
                    String.format(" | %-10s", blocks.get(6).getNombre()) +
                    String.format("| %-14s","\n|-----------------------------------------------------------------------------------------|");



    }


}
