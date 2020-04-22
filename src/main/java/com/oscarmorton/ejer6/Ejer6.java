package com.oscarmorton.ejer6;


import com.github.javafaker.Faker;
import com.oscarmorton.utils.Lib;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Ejer6 {
    /**TO-DO
     *
     * MENU DE ALTAS:
     *  -NUEVA PELICULA
     *  -NUEVO VIDEOJUEGO
     *  -NUEVO SOCIO
     *
     * ALQUILAR MULTIMEDIA AL SOCIO, SOLO SI ACTUALMENTE NO ESTA ALQUILANDO
     * DEVOLVER MULTIMEDIA, ORIGINAL COST 4 EUROS, AFTER 3 DAYS EXTRA 2 EUROS,
     *  - 1 EURO PELICULAS 2012, VIDEOJUEGOS 2010
     *
     * LISTADO:
     *  -VIEW ALL MULTIMEDIA
     *  -VIEW ALL MOVIES ALFABETACLY
     *  -VIEW GAMES  BY YEAR
     *  -HISTORY OF ALQUILERES
     *  -LIST ALQUIERES BY SPESIFIC SOCIO
     *  - LIST OF SOCIOS WHO NEED TO RETURN
     *
     *
     */

    private ArrayList<Videojuego> videojuegos;
    private ArrayList<Pelicula> peliculas;
    private ArrayList<Socio> socios;
    private Scanner lector;
    public Ejer6(){
        Tienda tienda = new Tienda();
        videojuegos = new ArrayList<>();
        peliculas = new ArrayList<>();
        socios = new ArrayList<>();
        int opcion = -1;


        crearDatosAleatorios(5, 5, 5); // Creo las datos aleatorios
        for(int i = 0; i < peliculas.size(); i++){
            System.out.println(peliculas.get(i).toString());
        }


        do{
            opcion = menuPrincipal();
            switch (opcion){
                case 1: // Menu de altas
                    do {
                        opcion = menuAltas();
                        switch (opcion) {
                            case 1:
                                System.out.println("CREANDO PELICULA NUEVA:");
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 0:
                                break;
                            default:
                                break;


                        }
                    }while (opcion != 0);
                    break;
                case 2: // Alquilar multimedia socio
                    break;
                case 3: // Devolver multimedia
                    break;
                case 4: // Listados
                        opcion = menuListados();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("ERROR");
                    break;

            }

        }while(opcion != 0);



    }


    /**
     * Genera datos aleatorios
     * TO-DO RETURNS AN ARRAYLIST
     * @param cantidadPeliculas La cantidad de peliculas
     * @param cantidadVideojuegos la cantidad de videojuegos
     * @param cantidadSocios La cantidad de socios
     */
    public void crearDatosAleatorios(int cantidadPeliculas, int cantidadVideojuegos, int cantidadSocios){
        crearPeliculaAleatoria(cantidadPeliculas);
        crearVideojuegosAleatorios(cantidadVideojuegos);
        crearSocioAleatorio(cantidadSocios);



    }

    /**
     * Crea videojuegos aleatorios
     * @param cantidad La cantidad de videojuegos que desea crear
     */
    public void crearVideojuegosAleatorios(int cantidad){
        Faker faker = new Faker();
        int numeroAleatorio;

        String titulo;
        String autor;
        Multimedia.FORMATO formato = Multimedia.FORMATO.ERROR;
        GregorianCalendar anyo;
        double duracion;
        Videojuego.PLATAFORMA plataforma = Videojuego.PLATAFORMA.ERROR;

        int year;
        int dayOfYear;

        GregorianCalendar gc;





        //Anyado los elementos:
        for(int i = 0; i < cantidad; i++){
            titulo =  faker.hitchhikersGuideToTheGalaxy().starship();
            autor = faker.name().fullName();

            // Creao el fomrato aleatorio
            numeroAleatorio = Lib.aleatorio(1, 4);
            switch (numeroAleatorio){
                case 1:
                    formato = Multimedia.FORMATO.CD;
                    break;
                case 2:
                    formato = Multimedia.FORMATO.DVD;
                    break;
                case 3:
                    formato = Multimedia.FORMATO.BLUE_RAY;
                    break;
                case 4:
                    formato = Multimedia.FORMATO.ARCHIVO;
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            // Creao el fomrato aleatorio
            numeroAleatorio = Lib.aleatorio(1, 3);
            switch (numeroAleatorio){
                case 1:
                    plataforma = Videojuego.PLATAFORMA.PLAYSTATION;
                    break;
                case 2:
                    plataforma = Videojuego.PLATAFORMA.XBOX;
                    break;
                case 3:
                    plataforma = Videojuego.PLATAFORMA.NINTENDO;
                    break;

                default:
                    System.out.println("ERROR");
                    break;
            }

            // Creo la fecha aleatoria
            gc = new GregorianCalendar();
            year = rangoEntre(1980, 2020);
            gc.set(gc.YEAR, year);
            dayOfYear = rangoEntre(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            anyo = gc;
            duracion = Lib.aleatorio(1.5,4.0);


            videojuegos.add(new Videojuego(titulo, autor,formato, anyo, plataforma ));

        }


    }
    public void crearSocioAleatorio(int cantidad){
        Faker faker;

        int nif;
        String nombre;
        String poblacion;



        //Anyado los elementos:
        for(int i = 0; i < cantidad; i++){
            faker  = new Faker();
            nif = Lib.aleatorio(111111,999999);
            nombre = faker.name().fullName();

            // Creo la fecha aleatoria
            long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(1999, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate fechaAleatoria = LocalDate.ofEpochDay(randomDay);



           //Poblacio aleatoria
            poblacion = faker.address().city();

            socios.add(new Socio(nif,nombre,fechaAleatoria,poblacion));

        }


    }

    /**
     * Genera peliculas aleatorias y los anyade al array
     *
     * @param cantidad
     */
    public void crearPeliculaAleatoria(int cantidad){
        Faker faker = new Faker();
        int numeroAleatorio;

        String titulo;
        String autor;
        Multimedia.FORMATO formato = Multimedia.FORMATO.ERROR;
        GregorianCalendar anyo;
        double duracion;

        int year;
        int dayOfYear;
        String actorPrincipal;
        String actrizPrincipal;
        GregorianCalendar gc;





        //Anyado los elementos:
        for(int i = 0; i < cantidad; i++){
            titulo =  faker.harryPotter().book();
            autor = faker.name().fullName();

            // Creao el fomrato aleatorio
            numeroAleatorio = Lib.aleatorio(1, 4);
            switch (numeroAleatorio){
                case 1:
                    formato = Multimedia.FORMATO.CD;
                    break;
                case 2:
                    formato = Multimedia.FORMATO.DVD;
                    break;
                case 3:
                    formato = Multimedia.FORMATO.BLUE_RAY;
                    break;
                case 4:
                    formato = Multimedia.FORMATO.ARCHIVO;
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            // Creo la fecha aleatoria
            gc = new GregorianCalendar();
            year = rangoEntre(1980, 2020);
            gc.set(gc.YEAR, year);
            dayOfYear = rangoEntre(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            anyo = gc;
            duracion = Lib.aleatorio(1.5,4.0);
            actorPrincipal = faker.rickAndMorty().character();
            actrizPrincipal = faker.rickAndMorty().character();

            peliculas.add(new Pelicula(titulo, autor,formato, anyo, duracion,actorPrincipal, actrizPrincipal ));

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
     * Imprime el menu de altas
     * * @return devuelve la opcion
     */

    public int menuAltas(){
        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("************************************");
            System.out.println("*       MENU DE ALTAS         *");
            System.out.println("************************************");
            System.out.println("1. Nueva pelicula");
            System.out.println("2. Nuevo Videojuego");
            System.out.println("3 Nuevo socio");
            System.out.println("---------------------");
            System.out.println("0. Cancelar\n");
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
     * Menu de los listados
     * @return int entre 0 y 7
     */
    public int menuListados(){
        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            Lib.limpiarPantalla();
            System.out.println("************************************");
            System.out.println("*        LISTADOS          *");
            System.out.println("************************************");
            System.out.println("1. Mostrar todos los multimedias");
            System.out.println("2. Mostrar peliculas ordenadas ");
            System.out.println("3. Mostrar videojuegos ordenados por anyo");
            System.out.println("4. Listado alquileres de un socio");
            System.out.println("5. Listado del histórico de alquileres de un socio ordenados por fecha de alquiler");
            System.out.println("6. Listado de los alquileres actuales de un socio");
            System.out.println("7. Listado de los socios con recargos pendientes");
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
            System.out.println("************************************");
            System.out.println("*        MENU PRINCIPAL          *");
            System.out.println("************************************");
            System.out.println("1. Altas");
            System.out.println("2. Alquilar multimedia socio");
            System.out.println("3 Devolver multimedia");
            System.out.println("4 Listados");
            System.out.println("---------------------");
            System.out.println("0. Cancelar\n");
            System.out.println("Elija una opción: ");
            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 4) {
                    System.out.println("Elija una opción del menú [0-4]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 4);
        return opcion;

    }
}
