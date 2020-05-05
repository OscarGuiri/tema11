package com.oscarmorton.ejer7;

import com.github.javafaker.Faker;
import com.oscarmorton.utils.Lib;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ejer7 {
    /**
     * TODO
     * Venta de entradas
     * Devolver una entrada
     * Listado de localidades ocupadas
     * Listado de localidades libres
     *  Mostrar recaudación del partido
     * Jugar tartida
     */


    private ArrayList<Partido> partidos;
    private Scanner lector;
    private int nFilas;
    private int nColumnas;
    private int nZonas;

    public Ejer7(int nFilas, int nColumnas, int nZonas, double precioBase) {
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.nZonas = nZonas;

        partidos = new ArrayList<>();
        generarPartidoAleatorio(3); // Genero 3 partidas aleatoriamente
       /* Estadio estadio = new Estadio(nZonas, "javea", 3, nFilas, nColumnas); // THIS IS A TEST
        estadio.imprimeEstadio();
        estadio.crearAsientosAleatorios(true);
        estadio.imprimeEstadio(); */

        int opcion = -1;
        int posicionSeleccionada = 0;
        int fila = 0;
        int columna = 0;
        int zona = 0;
        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1:
                    System.out.println("Nuevo partido:");
                    nuevoPartido();

                    break;
                case 2:
                    System.out.println("Gestion de entradas");
                    posicionSeleccionada = elegirPartida(); // Consigo la partida elegida correspondiente en el arraylist
                    opcion = menuGestiones();

                    switch (opcion){
                        case 1:
                            System.out.println("Venta de entradas");
                            partidos.get(posicionSeleccionada).getEstadio().imprimeEstadio();

                            System.out.println("Introduce la zona:");
                            zona = Integer.parseInt(lector.nextLine());
                            System.out.println("Fila: ");
                            fila = Integer.parseInt(lector.nextLine());
                            System.out.println("columna");
                            columna = Integer.parseInt(lector.nextLine());

                            partidos.get(posicionSeleccionada).venderEntrada(fila,columna, zona);

                            break;
                        case 2:
                            System.out.println(" Devolver una entrada");
                            break;
                        case 3:
                            System.out.println("Listado de localidades ocupadas");
                            partidos.get(posicionSeleccionada).getEstadio().imprimirEstadiosAsientosOcupados();
                            break;
                        case 4:
                            System.out.println("Listado de localidades libres");
                            partidos.get(posicionSeleccionada).getEstadio().imprimirEstadiosAsientosLibres();
                            break;
                        case 5:
                            break;
                        case 0:
                            break;



                    }


                    break;
                case 3:
                    System.out.println("Jugar partida"); // Simplente pide la fecha del partido y juega la loteria con los asientos reservados.
                    break;
                case 0:
                    System.out.println("Hasta pronto!");
                    break;



            }
        } while (opcion != 0);


    }

    /**
     * Crea una nueva partida
     */
    public void nuevoPartido(){
        lector = new Scanner(System.in);
        boolean validado = false;
        int opcion = 0;
        int nAsientosVip = 0;
        Partido.AFLUENCIA afluencia = Partido.AFLUENCIA.ERROR;
        String nombreEquipoLocal;
        String nombreEquipoVisitante;
        String zona;

        GregorianCalendar hoy = new GregorianCalendar();
        GregorianCalendar fechaPartido;

        do {
            fechaPartido = pedirFechaPartido();

            if (fechaPartido.after(hoy)) { // Compruebo que la fecha del partido no es en el pasado
                validado = true;
            } else {
                validado = false;
                System.out.println("La fecha del partido po puede ser en el pasado");
            }
        }while (!validado);


        // Pido la anfluencia
        do{
            System.out.println("Que tipo de influencia es la partida? ");
            System.out.println("1. BAJA_AFLUENCIA\n2. MEDIA_AFLUENCIA\n3.  ALTA_AFLUENCIA");
            try {
                opcion = Integer.parseInt(lector.nextLine());
                validado = true;
            }catch (NumberFormatException nfe){
                System.out.println("Introduce un numero entre el 1 y el 3");
            }
            validado = true;
            if (opcion > 3 || opcion < 1) {
                validado = false;
                System.out.println("Por favor, introduce un valor entre  el 3 y el 1");
            }

        }while(!validado);
        afluencia = conseguirAfluencia(opcion); // Consigo la afluencia


        // Pido cuantas sillas VIP hay
        do{
            System.out.println("Cuantos asientos VIP hay por zona?");

            try {
                nAsientosVip = Integer.parseInt(lector.nextLine());
                validado = true;
            }catch (NumberFormatException nfe){
                System.out.println("Introduce un numero.");
            }
            validado = true;
            if (nAsientosVip < 0) {
                validado = false;
                System.out.println("Por favor, introduce un valor que no está en negativo");
            }

        }while(!validado);




        nombreEquipoLocal = pedirString("Nombre equipo local"); // Consigo el nombre del equipo local
        nombreEquipoVisitante = pedirString("Nombre equipo visitante"); // Consigo el nombre del equipo visitante.
        zona = pedirString("Zona");
        Estadio estadio = new Estadio(nZonas, zona, nAsientosVip, nFilas, nColumnas); // Creo el estadio

        partidos.add( new Partido(afluencia,fechaPartido,nombreEquipoLocal,nombreEquipoVisitante, estadio)); // Anyado la partida a la lista de partidos.
        System.out.println("Partido creado");





    }
    public void imprimirPartidas(){
        for(int i = 0; i < partidos.size(); i++){
            System.out.println("Partida " + (i +1) + ":");
            // Imprimo la fecha del partida
            System.out.println(partidos.get(i).getFechaPartido().get(Calendar.DATE) +" / " +
                    partidos.get(i).getFechaPartido().get(Calendar.MONTH) +" / "  +
                    partidos.get(i).getFechaPartido().get(Calendar.YEAR));

            System.out.println(partidos.get(i).getEquipoLocal() + " vs " + partidos.get(i).getGetEquipoVisitante());
            System.out.println();


        }
    }

    /**
     * Imprime las partidas y pide cual quieres elegir
     * @return La posicion de la partida en el arraylist
     */
    public int elegirPartida(){
        int opcion = -1;
        boolean validado = false;
        imprimirPartidas();
        do{
            System.out.println("Elgige una opcion: ");
            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                validado = true;
                if (opcion < 0 || opcion > partidos.size()) {
                    validado = false;
                    System.out.println("Elija una opción entre 0 y " + partidos.size());
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }

        }while (!validado);
        return  opcion - 1;

    }

    /**
     * Pide la afluencia de una partida
     * @param opcion
     * 1. BAJA_AFLUENCIA
     * 2. MEDIA_AFLUENCIA
     * 3.  ALTA_AFLUENCIA
     * @return  La afluencia correspondiente
     */
    public Partido.AFLUENCIA conseguirAfluencia(int opcion){
        Partido.AFLUENCIA afluencia = Partido.AFLUENCIA.ERROR;
        switch (opcion){
            case 1:
                afluencia = Partido.AFLUENCIA.BAJA_AFLUENCIA;
                break;
            case 2:
                afluencia = Partido.AFLUENCIA.MEDIA_AFLUENCIA;
                break;
            case 3:
                afluencia = Partido.AFLUENCIA.ALTA_AFLUENCIA;
                break;
            default:
                System.out.println("ERROR SWITCH");
                break;


        }
        return  afluencia;
    }

    /**
     * Genera partidas aleatorioas para el arraylist de partidas
     * @param cantidadPartidas
     */
    public void generarPartidoAleatorio(int cantidadPartidas) {
        Faker faker = new Faker();
        Partido.AFLUENCIA afluencia = Partido.AFLUENCIA.ERROR;
        int opcion = Lib.aleatorio(1, 3);
        int year;
        int dayOfYear;
        String equipoLocal;
        String equipoVisitante;

        for (int i = cantidadPartidas; i > 0; i--) { // creo partidas
            //Creo un estadio aleatorio

            Estadio estadio = new Estadio(nZonas, faker.address().city(), (Lib.aleatorio(2, 4)), nFilas, nColumnas);
            estadio.crearAsientosAleatorios(true); // Genero las sillas aleatorias.


            //Consigo la fecha aleatoria
            GregorianCalendar gc = new GregorianCalendar();
            afluencia = conseguirAfluencia(opcion);

            // Creo la fecha aleatoria
            gc = new GregorianCalendar();
            year = rangoEntre(2020, 2021);
            gc.set(gc.YEAR, year);
            dayOfYear = rangoEntre((gc.DAY_OF_MONTH + 1), gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            equipoLocal = faker.team().name();
            equipoVisitante = faker.team().name();

            partidos.add(new Partido(afluencia, gc, equipoLocal, equipoVisitante, estadio));


        }
    }
    /**
     * Devuelve un rango entre 2 valores
     * @param start El principio
     * @param end El final
     * @return El rango aleatorio
     */
    public static int rangoEntre(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    /**
     * Pide la fecha del partido
     * @return La fecha introducida.
     */
    public GregorianCalendar pedirFechaPartido(){
        GregorianCalendar anyo = new GregorianCalendar();
        boolean validado = false;
        do {
            System.out.println("Introduce la fecha del partido:(dd-mm-yyyy)   ");
            String fechaAnyoToString = lector.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date date = sdf.parse(fechaAnyoToString);
                anyo = new GregorianCalendar();
                anyo.setTime(date);
                validado = true;
            } catch (ParseException pe) {
                validado = false;
                System.out.println("El formato de la fehca no es válido. Recuerde (dd-mm-yyyy).");
                Lib.pausa();
            }
        } while (!validado);
        return  anyo;

    }

    /**
     * Pido un string
     * @return El el string introducido por el usuario
     */
    public String pedirString(String string){
        boolean validado = false;
        // Pido el titulo
        do {
            System.out.println(string.toString() + ":");
            string = lector.nextLine();
            validado = string.length() > 2;
            if(!validado) {
                System.out.println(string.toString() + " debe tener almenos 2 caracteres");
                Lib.pausa();
            }
        } while (!validado);

        return  string;
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
