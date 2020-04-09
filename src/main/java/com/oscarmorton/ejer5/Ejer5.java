package com.oscarmorton.ejer5;

import com.oscarmorton.utils.Lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejer5 {
    private Scanner lector;
    private ArrayList < ItemBlock > blocks;


    // LOS ITEM BLOCKES:


    public Ejer5() {
        lector = new Scanner(System.in);

        int opcion = 0;





        // Creo los 7 blockes
        blocks = new ArrayList < > ();
        for (int i = 0; i < 7; i++) {
            blocks.add(new ItemBlock());
        }



        System.out.println("INVENARIO MINECRAFT");
        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1:
                    Lib.limpiarPantalla();
                    anyadirInventario();
                    break;
                case 2:
                    Lib.limpiarPantalla();
                    eliminarElemento();
                    break;
                case 3:
                    //Muestro el inventario
                    Lib.limpiarPantalla();
                    System.out.println("Mostrar Inventario");
                    System.out.println(mostrarInventarioItems());
                    System.out.println(mostrarInventarioCuantidad());
                    Lib.limpiarPantalla();
                    Lib.pausa();
                    break;

                case 0:
                    break;
                default:
                    break;
            }
        } while (opcion != 0);

    }

    /**
     * Devuelve los espacios libres que queda en el inventario
     * @return
     */
    public int getEspaciosLibres() {
        int epasaciosLibre = 0;
        ItemBlock item;

        for (int i = 0; i < blocks.size(); i++) {

            if (blocks.get(i).getNombre().equals("VACIO")) {
                epasaciosLibre++;


            }

        }
        return epasaciosLibre;

    }

    /**
     * Creacion de un Item del inventario.
     */
        public void anyadirInventario(){
            lector = new Scanner(System.in);
            ItemBlock item;
            int idElemento;
            int numeroAnyadir;
            int espaciosLibres;
            int contador = 0;
            int aux = 0;
            boolean anyadido = false;
            Lib.limpiarPantalla();
            System.out.println("Añadir inventario:");
            idElemento = menuCualElemento();
            switch (idElemento) {
                case 1:


                    espaciosLibres = getEspaciosLibres();
                    System.out.println("Hay " + espaciosLibres + " espacios libres");
                    if (espaciosLibres != 0) {




                        numeroAnyadir = pedirCantidadItem("pico");
                        if (espaciosLibres >= numeroAnyadir) { // Si hay espacios libres, creo y el pico
                            item = new Pico();

                            for (int i = 0; contador < numeroAnyadir; i++) { // El bucle continua hasta anyadir todos los items.

                                if (blocks.get(i).getNombre().equals("VACIO")) {
                                    blocks.set(i, item);
                                    blocks.get(i).setCantidad(1);
                                    contador++;

                                }

                            }
                            contador = 0;
                            System.out.println("Creado pico con exito");
                        } else {
                            System.out.println("No hay espacio suficiente");
                        }


                        System.out.println("Creado pico con exito");
                    } else {
                        System.out.println("El inventario está lleno");
                    }

                    break;
                case 2:

                    espaciosLibres = getEspaciosLibres();

                    if (espaciosLibres != 0) {
                        System.out.println("Hay " + espaciosLibres + " espacios libres");


                        numeroAnyadir = pedirCantidadItem("espada");

                        if (espaciosLibres >= numeroAnyadir) { // Si hay espacios libres, creo y el pico
                            item = new Espada();

                            for (int i = 0; contador < numeroAnyadir; i++) { // El bucle continua hasta anyadir todos los items.

                                if (blocks.get(i).getNombre().equals("VACIO")) {
                                    blocks.set(i, item);
                                    blocks.get(i).setCantidad(1);
                                    contador++;

                                }

                            }
                            contador = 0;
                        } else {
                            System.out.println("No hay espacio suficiente");
                        }


                    } else {
                        System.out.println("El inventario está lleno");
                    }

                    break;
                // TO DO!!
                case 3:
                    espaciosLibres = getEspaciosLibres();

                    System.out.println("Hay " + espaciosLibres + " espacios libres");

                    numeroAnyadir = pedirCantidadItem("Piedra");
                    for (int i = 0; i < blocks.size(); i++) {

                        if (blocks.get(i).getNombre().equals("piedra") && blocks.get(i).getCantidad() < 64) { // Si ya hay un bloque de piedra y está menos de 64, anyadimos la piedra
                            while (blocks.get(i).getCantidad() < 64 && numeroAnyadir != 0) {

                                blocks.get(i).setCantidad(blocks.get(i).getCantidad() + 1);
                                numeroAnyadir--;
                            }
                        }


                    }
                    // Si veo que no hay un elemento piedra en el los blockes y aun tengo cantidad para anyadir, busco un blocke vacio.
                    if (numeroAnyadir != 0) {

                        while (numeroAnyadir > 0 && contador != 7) {

                            for (int i = 0; i < blocks.size(); i++) {
                                if (numeroAnyadir != 0) {
                                    if (blocks.get(i).getNombre().equals("VACIO")) {
                                        item = new Piedra();

                                        if (numeroAnyadir >= 64) {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(64);
                                            numeroAnyadir = numeroAnyadir - 64;


                                        } else {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(numeroAnyadir);
                                            numeroAnyadir = 0;
                                            anyadido = true;


                                        }
                                    }
                                }
                                contador++;

                            }


                        }
                        contador = 0;


                    }
                    if (numeroAnyadir == 0 && anyadido) {
                        System.out.println("Anyadido correctamente");
                    } else {
                        System.out.println("No se puede anyadir mas al inventario");
                    }



                    break;
                case 4:
                    espaciosLibres = getEspaciosLibres();

                    System.out.println("Hay " + espaciosLibres + " espacios libres");

                    numeroAnyadir = pedirCantidadItem("madera");
                    for (int i = 0; i < blocks.size(); i++) {

                        if (blocks.get(i).getNombre().equals("madera") && blocks.get(i).getCantidad() < 64) { // Si ya hay un bloque de piedra y está menos de 64, anyadimos la piedra
                            while (blocks.get(i).getCantidad() < 64 && numeroAnyadir != 0) {

                                blocks.get(i).setCantidad(blocks.get(i).getCantidad() + 1);
                                numeroAnyadir--;
                            }
                        }


                    }
                    // Si veo que no hay un elemento piedra en el los blockes y aun tengo cantidad para anyadir, busco un blocke vacio.
                    if (numeroAnyadir != 0) {

                        while (numeroAnyadir > 0 && contador != 7) {

                            for (int i = 0; i < blocks.size(); i++) {
                                if (numeroAnyadir != 0) {
                                    if (blocks.get(i).getNombre().equals("VACIO")) {
                                        item = new Madera();

                                        if (numeroAnyadir >= 64) {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(64);
                                            numeroAnyadir = numeroAnyadir - 64;


                                        } else {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(numeroAnyadir);
                                            numeroAnyadir = 0;
                                            anyadido = true;


                                        }
                                    }
                                }
                                contador++;

                            }


                        }
                        contador = 0;


                    }
                    if (numeroAnyadir == 0 && anyadido) {
                        System.out.println("Anyadido correctamente");
                    } else {
                        System.out.println("No se puede anyadir mas al inventario");
                    }


                    break;
                case 5:
                    espaciosLibres = getEspaciosLibres();

                    System.out.println("Hay " + espaciosLibres + " espacios libres");

                    numeroAnyadir = pedirCantidadItem("huevo");
                    for (int i = 0; i < blocks.size(); i++) {

                        if (blocks.get(i).getNombre().equals("huevo") && blocks.get(i).getCantidad() < 16) { // Si ya hay un bloque de piedra y está menos de 64, anyadimos la piedra
                            while (blocks.get(i).getCantidad() < 16 && numeroAnyadir != 0) {

                                blocks.get(i).setCantidad(blocks.get(i).getCantidad() + 1);
                                numeroAnyadir--;
                            }
                        }


                    }
                    // Si veo que no hay un elemento piedra en el los blockes y aun tengo cantidad para anyadir, busco un blocke vacio.
                    if (numeroAnyadir != 0) {

                        while (numeroAnyadir > 0 && contador != 7) {

                            for (int i = 0; i < blocks.size(); i++) {
                                if (numeroAnyadir != 0) {
                                    if (blocks.get(i).getNombre().equals("VACIO")) {
                                        item = new Huevo();

                                        if (numeroAnyadir >= 16) {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(16);
                                            numeroAnyadir = numeroAnyadir - 16;


                                        } else {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(numeroAnyadir);
                                            numeroAnyadir = 0;
                                            anyadido = true;


                                        }
                                    }
                                }
                                contador++;

                            }


                        }
                        contador = 0;


                    }
                    if (numeroAnyadir == 0 && anyadido) {
                        System.out.println("Anyadido correctamente");
                    } else {
                        System.out.println("No se puede anyadir mas al inventario");
                    }



                    break;
                case 6:
                    espaciosLibres = getEspaciosLibres();

                    System.out.println("Hay " + espaciosLibres + " espacios libres");

                    numeroAnyadir = pedirCantidadItem("perla");
                    for (int i = 0; i < blocks.size(); i++) {

                        if (blocks.get(i).getNombre().equals("perla") && blocks.get(i).getCantidad() < 16) { // Si ya hay un bloque de piedra y está menos de 64, anyadimos la piedra
                            while (blocks.get(i).getCantidad() < 16 && numeroAnyadir != 0) {

                                blocks.get(i).setCantidad(blocks.get(i).getCantidad() + 1);
                                numeroAnyadir--;
                            }
                        }


                    }
                    // Si veo que no hay un elemento piedra en el los blockes y aun tengo cantidad para anyadir, busco un blocke vacio.
                    if (numeroAnyadir != 0) {

                        while (numeroAnyadir > 0 && contador != 7) {

                            for (int i = 0; i < blocks.size(); i++) {
                                if (numeroAnyadir != 0) {
                                    if (blocks.get(i).getNombre().equals("VACIO")) {
                                        item = new Perla();

                                        if (numeroAnyadir >= 16) {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(16);
                                            numeroAnyadir = numeroAnyadir - 16;


                                        } else {
                                            blocks.set(i, item);
                                            blocks.get(i).setCantidad(numeroAnyadir);
                                            numeroAnyadir = 0;
                                            anyadido = true;


                                        }
                                    }
                                }
                                contador++;

                            }


                        }
                        contador = 0;


                    }
                    if (numeroAnyadir == 0 && anyadido) {
                        System.out.println("Anyadido correctamente");
                    } else {
                        System.out.println("No se puede anyadir mas al inventario");
                    }




                    break;
                case 0:
                    break;
                default:
                    System.out.println("ERROR");
                    break;


            }


        }

    /**
     * Pide la cantidad del elemento introducido como parametro
     * @param nombreItem El nombre del elemento
     * @return La cantidad deseada
     */
    public int pedirCantidadItem(String nombreItem) {
        lector = new Scanner(System.in);
        boolean valido = false;
        int cantidad = 0;
        do {
            System.out.println("Cuantos " + nombreItem + "s quieres anyadir?");
            try {
                cantidad = Integer.parseInt(lector.nextLine());
                valido = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Por favor, introduce una cantidad en formato numerico");
            }
        } while (!valido);
        return cantidad;
    }

    /**
     * Elimina un item del inventario, pidiendo cuanto tambien
     */
    public void eliminarElemento(){
        lector = new Scanner(System.in);
        int opcion = -1;
        boolean valido = false;
        int cantidadParaEliminar = 0;
        ItemBlock item = new ItemBlock();

        System.out.println("Cual elemento quieres eliminar?");
        System.out.println( "|-----------1-----------2-----------3-----------4-----------5-----------6-----------7----|");
        System.out.println(mostrarInventarioItems());
        System.out.println(mostrarInventarioCuantidad());
        do {
            System.out.println("Cual quieres eliminar?");
            try {
                opcion = Integer.parseInt(lector.nextLine());
                valido = true;
                if(opcion < 0 && opcion > 7){
                    System.out.println("Por favor introduce un valor entre el 1 y el 7");
                    valido = false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Por favor, introduce un numero");
            }
        }while(!valido);
        System.out.println("Queda " +  blocks.get(opcion - 1).getCantidad() + " " +  blocks.get(opcion - 1).getNombre() + "(s)");


        if(blocks.get(opcion - 1 ).getCantidad() > 1){ // Si la cantidad es 1, lo elimina directamente
            System.out.println("Cuanto quieres eliminar?");
            valido =false;

            // Pido la cantidad deseada para eliminar
            do {
                System.out.println("Cual quieres eliminar?");
                try {
                    cantidadParaEliminar = Integer.parseInt(lector.nextLine());
                    valido = true;
                    if(opcion < 0){
                        System.out.println("Por favor introduce un valor superior al 0");
                        valido = false;
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Por favor, introduce un numero");
                }
            }while(!valido);
            blocks.get(opcion - 1).setCantidad(blocks.get(opcion - 1).getCantidad() - cantidadParaEliminar);

            //Si la cantidad es 0, pongo el item por defecto (VACIO)
            if( blocks.get(opcion - 1).getCantidad() == 0){
                blocks.set((opcion - 1), item);
                System.out.println("Eliminado corractamente");
                Lib.pausa();
            }else{
                System.out.println("Eliminado corractamente");
                Lib.pausa();
            }
        }else{
            blocks.set((opcion - 1), item);
            System.out.println("Item eliminado con exito.");
            Lib.pausa();
        }


    }



    /**
     * Muestra el menu de los elementos posibles para anydir al inventario.
     * @return 1. pico 2. espada 3. piedra 4. madera 5. Huevos 6. perlas de ender
     */
    public int menuCualElemento() {
        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("************************************");
            System.out.println("*        ELEGIR ELEMENTO           *");
            System.out.println("************************************");
            System.out.println("1. Pico");
            System.out.println("2. Espada");
            System.out.println("3. Piedra");
            System.out.println("4. Madera");
            System.out.println("5. Huevo");
            System.out.println("6. Perla de Ender");
            System.out.println("---------------------");
            System.out.println("0. Cancelar\n");
            System.out.println("Elija una opción: ");
            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 7) {
                    System.out.println("Elija una opción del menú [0-7]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 7);
        return opcion;

    }

    /**
     * Imprime el menu principal
     * @return
     */
    public int menuPrincipal() {

        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("*************************");
            System.out.println("*        MINECRAFT       *");
            System.out.println("*   SISTEMA DE INVENTARIO *");
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

    /**
     * Muestra la cantidad de cada Item del inventario
     * @return
     */
    public String mostrarInventarioCuantidad() {
        return String.format("| %-10s", blocks.get(0).getCantidad()) +
                String.format(" | %-10s", blocks.get(1).getCantidad()) +
                String.format(" | %-10s", blocks.get(2).getCantidad()) +
                String.format(" | %-10s", blocks.get(3).getCantidad()) +
                String.format(" | %-10s", blocks.get(4).getCantidad()) +
                String.format(" | %-10s", blocks.get(5).getCantidad()) +
                String.format(" | %-10s", blocks.get(6).getCantidad()) +
                String.format("| %-14s", "\n|-----------------------------------------------------------------------------------------|");



    }

    public String mostrarInventarioItems() {
        return String.format("| %-10s", "----------------------------------------------------------------------------------------|\n") +
                String.format("| %-10s", blocks.get(0).getNombre()) +
                String.format(" | %-10s", blocks.get(1).getNombre()) +
                String.format(" | %-10s", blocks.get(2).getNombre()) +
                String.format(" | %-10s", blocks.get(3).getNombre()) +
                String.format(" | %-10s", blocks.get(4).getNombre()) +
                String.format(" | %-10s", blocks.get(5).getNombre()) +
                String.format(" | %-10s", blocks.get(6).getNombre()) +
                String.format("| %-14s", "\n|-----------------------------------------------------------------------------------------|");



    }


}