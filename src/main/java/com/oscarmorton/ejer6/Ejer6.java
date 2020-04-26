package com.oscarmorton.ejer6;



import com.oscarmorton.utils.Lib;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Ejer6 {
    /**
     * DEVOLVER MULTIMEDIA NO FUNCIONA.
     *
     *
     */

    /**TODO
     * DEVOLVER MULTIMEDIA, ORIGINAL COST 4 EUROS, AFTER 3 DAYS EXTRA 2 EUROS,
     *  - 1 EURO PELICULAS 2012, VIDEOJUEGOS 2010
     *
     *
     */

    private Tienda tienda;
    private Scanner lector;
    public Ejer6(){
        tienda = new Tienda();

        tienda.crearDatosAleatorios(5,5,5); // Creo 5 datos aleatorios de cada elemento


        int opcion = -1;

        do{
            opcion = menuPrincipal();
            switch (opcion){
                case 1: // Menu de altas
                    do {
                        opcion = menuAltas();
                        switch (opcion) {
                            case 1:
                                System.out.println("CREANDO PELICULA NUEVA:");
                                crearPeliculaNueva();
                                break;
                            case 2:
                                System.out.println("CREANDO VIDEOJUEGO:");
                                crearVideojuego();
                                break;
                            case 3:
                                System.out.println("CREANDO SOCIO");
                                crearSocio();
                                break;
                            case 0:
                                break;
                            default:
                                break;


                        }
                    }while (opcion != 0);
                    opcion = -1;
                    break;
                case 2: // Alquilar multimedia socio
                    alquilarMultimediaAlSocio(); // ESTO FALLA!!
                    break;
                case 3: // Devolver multimedia
                    devolverMultimedia(); //  NO PROBAR, NO FUNCIONA
                    break;
                case 4: // Listados
                    listados();

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
     * Crea un videojuego nuevo.
     */
    public void crearVideojuego(){
        String titulo;
        String autor;
        Multimedia.FORMATO formato;
        GregorianCalendar anyo;
        Videojuego.PLATAFORMA plataforma = Videojuego.PLATAFORMA.ERROR;

        int opcion = -1;
        boolean validado = false;


        titulo = pedirString("titulo"); // Pido el titulo
        autor = pedirString("autor"); // Pido el autor
        formato = pedirFormato(); // Pido el formato
        anyo = pedirAnyo(); // Pido el anyo


        do{

            System.out.println("Elige la plataforma:");
            System.out.println("1. PLAYSTATION");
            System.out.println("2. XBOX");
            System.out.println("3. NINTENDO");

            try {
                opcion = Integer.parseInt(lector.nextLine());
                validado = true;
                if (opcion > 3 || opcion < 1) {
                    validado = false;
                    System.out.println("Por favor, introduce un numero entre el 1 y el 3");
                }
            }catch (NumberFormatException nfe){
                System.out.println("Introduce un valor numerico entre el 1 y el 3");
            }
        }while(!validado);

        switch (opcion){
            case 1:
                plataforma  = Videojuego.PLATAFORMA.PLAYSTATION;
                break;
            case 2:
                plataforma  =  Videojuego.PLATAFORMA.XBOX;
                break;
            case 3:
                plataforma  = Videojuego.PLATAFORMA.NINTENDO;
                break;
            default:
                break;
        }

        //Creo el videojuego y lo anyado a la tienda:

        tienda.getVideojuegos().add(new Videojuego(titulo,autor,formato,anyo,plataforma));
        System.out.println("Videojuego creado con exito");
        Lib.pausa();



    }
    public void devolverMultimedia(){
        lector = new Scanner(System.in);
        int NIF;

        String titulo;
        char respuesta;
        double dineroDebido = 0;
        boolean isPelicula = false;
        System.out.println("Devolver multimedia socio");
        NIF = pedirNIF(); // Pido el nif

        if(tienda.exiteSocio(NIF)){ // Si el socio exite, continui
           titulo = pedirString("titulo"); // Pido el titulo.
            System.out.println("Es una pelicula? Si o no?");
            respuesta = lector.nextLine().charAt(0);
            if (respuesta == 's') {
                isPelicula = true;
                System.out.println("CONFIRMADO ES PELICULA");
            } else if (respuesta == 'n'){
                isPelicula = false;
            }else{
                isPelicula = false;
            }


          dineroDebido = tienda.devolverMultimedia(NIF,titulo, isPelicula);
            System.out.println("Usted debe " + dineroDebido);

        }else{
            System.out.println("El socio con NIF " + NIF + " no exite");
        }


    }


    /**
     * Crea una nueva pelicula y lo anyade a la tienda.
     */
    public void crearPeliculaNueva(){
        //Atributos de pelicula
        String titulo;
        String autor;
        Multimedia.FORMATO formato;
        GregorianCalendar anyo;
        double duracion = 0;
        String actorPrincipal;
        String actrizPrincipal;

        boolean validado = false;


        titulo = pedirString( "titulo"); // Pido el titulo
        autor = pedirString("autor"); // Pido el autor
        formato = pedirFormato(); // Pido el formato
        anyo = pedirAnyo(); // Pido el anyo

        do{
            System.out.println("Introduce la duracion:");
            try {
                duracion = Double.parseDouble(lector.nextLine());
                validado = true;

            if(duracion <= 0){
                validado = false;
                System.out.println("la duracion no puede ser un numero negativo");
            }
            }catch (NumberFormatException nfe){
                System.out.println("Por favor, introduce un numero.");
            }

        }while(!validado);
        validado = false;

        // Pido el actor
        do {
            System.out.println("Actor: ");
            actorPrincipal = lector.nextLine();
            validado = titulo.length() > 2;
            if(!validado) {
                System.out.println("El actor debe tener almenos 2 caracteres");
                Lib.pausa();
            }
        } while (!validado);

        validado = false;

        // Pido la actriz
        do {
            System.out.println("Actriz: ");
            actrizPrincipal = lector.nextLine();
            validado = titulo.length() > 2;
            if(!validado) {
                System.out.println("La actriz debe tener almenos 2 caracteres");
                Lib.pausa();
            }
        } while (!validado);


        // Finalmente creo la pelicula y lo anyado a las peliculas de la tienda.
        tienda.getPeliculas().add(new Pelicula(titulo, autor, formato, anyo, duracion, actorPrincipal, actrizPrincipal));
        System.out.println("Pelicula creado con exito");
        Lib.pausa();

    }

    public void crearSocio(){
        int NIF = 0;
        String nombre;
        LocalDate fechaNacimiento;
        String poblacion;
        long fecha;

        int anyo = 0;
        int mes = 0;
        int dia = 0;

        boolean validado = false;


       NIF = pedirNIF();   // pido la NIF

        if(!tienda.exiteSocio(NIF)) { // Compruebo que el NIF exite
            nombre = pedirString("nombre"); // pido el nombre

            //Pido el anyo
            do {
                System.out.println("Introduce tu anyo de nacimiento:");
                try {
                    anyo = Integer.parseInt(lector.nextLine());
                    validado = true;

                    if (anyo <= 0) {
                        validado = false;
                        System.out.println("El anyo no puede ser un numero negativo");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Por favor, introduce un numero.");
                }

            } while (!validado);
            //

            do {
                System.out.println("Introduce tu mes de nacimiento:");
                try {
                    mes = Integer.parseInt(lector.nextLine());
                    validado = true;

                    if (mes <= 0) {
                        validado = false;
                        System.out.println("El mes no puede ser un numero negativo");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Por favor, introduce un numero.");
                }

            } while (!validado);

            do {
                System.out.println("Introduce tu dia de nacimiento:");
                try {
                    dia = Integer.parseInt(lector.nextLine());
                    validado = true;

                    if (dia <= 0) {
                        validado = false;
                        System.out.println("El dia no puede ser un numero negativo");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Por favor, introduce un numero.");
                }

            } while (!validado);


            // Creo el LocalDate
            fecha = LocalDate.of(anyo, mes, dia).toEpochDay();
            fechaNacimiento = LocalDate.ofEpochDay(fecha);

            LocalDate hoy = LocalDate.now();
            Period p = Period.between(fechaNacimiento, hoy);

            if (p.getYears() > 18) {
                poblacion = pedirString("poblacion"); // Pido la poblacion
                tienda.getSocios().add(new Socio(NIF, nombre, fechaNacimiento, poblacion, false));
                System.out.printf("socio creado correctamente\n");
            } else {
                System.out.println("Los socions no pueden ser menores de edad");
                System.out.println("Socio no creado");
            }

        }else{
            System.out.println("El socio con NIF " + NIF + " ya exite.");
            System.out.println("Socio no creado");
        }

    }

    /**
     * Pide el NIF por teclado
     * @return EL NIF
     */
    public int pedirNIF(){
        int NIF = 0;
        boolean validado = false;
        do{
            System.out.println("Introduce la NIF:");
            try {
                NIF = Integer.parseInt(lector.nextLine());
                validado = true;

                if(NIF <= 0){
                    validado = false;
                    System.out.println("El NIF no puede ser un numero negativo");
                }
            }catch (NumberFormatException nfe){
                System.out.println("Por favor, introduce un numero.");
            }

        }while(!validado);
        return  NIF;
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
     * Pide el anyo en formato (dd-mm-yyyy)
     * @return El anyo en formato GregorianCalander
     */
    public GregorianCalendar pedirAnyo(){
        GregorianCalendar anyo = new GregorianCalendar();
        boolean validado = false;
        do {
            System.out.println("Introduce el anyo (dd-mm-yyyy): ");
            String fechaAnyoToString = lector.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date date = sdf.parse(fechaAnyoToString);
                anyo = new GregorianCalendar();
                anyo.setTime(date);
                validado = true;
            } catch (ParseException pe) {
                validado = false;
                System.out.println("El formato del anyo no es válido. Recuerde (dd-mm-yyyy).");
                Lib.pausa();
            }
        } while (!validado);
        return  anyo;

    }

    /**
     * pido el formato del multimedia
     * @return El formato [CD, DVD, BLUE_RAY, ARCHIVO]
     */
    public Multimedia.FORMATO pedirFormato(){
        int opcion = -1;
        boolean validado = false;
        Multimedia.FORMATO formato = Multimedia.FORMATO.ERROR;
        do{

            System.out.println("Elige un formato:");
            System.out.println("1. CD");
            System.out.println("2. DVD");
            System.out.println("3. BLUE_RAY");
            System.out.println("4 ARCHIVO");
            try {
                opcion = Integer.parseInt(lector.nextLine());
                validado = true;
                if (opcion > 4 || opcion < 1) {
                    validado = false;
                    System.out.println("Por favor, introduce un numero entre el 1 y el 4");
                }
            }catch (NumberFormatException nfe){
                System.out.println("Introduce un valor numerico entre el 1 y el 4");
            }
        }while(!validado);

        switch (opcion){
            case 1:
                formato  = Multimedia.FORMATO.CD;
                break;
            case 2:
                formato  = Multimedia.FORMATO.DVD;
                break;
            case 3:
                formato  = Multimedia.FORMATO.BLUE_RAY;
                break;
            case 4:
                formato  = Multimedia.FORMATO.ARCHIVO;
                break;
            default:
                break;
        }
        return  formato;

    }

    /**
     * Alquilar la multimeda a un socio pedido por teclado
     */
    public void alquilarMultimediaAlSocio(){
        lector = new Scanner(System.in);
        int opcion = -1;
        int NIF = 0;
        int pedirElemento;
        String titulo;
        Socio socio;



        NIF = pedirNIF(); // pido el NIF por teclado

        if(tienda.exiteSocio(NIF)){ // Si exite, continuo
            socio = tienda.getSocioWithNIF(NIF); // Si exite el socio, busco en la tienda y lo asigno.
           if(!socio.isAlquilando()) { // si el socio no está actualmente alquilando, no continuo
               opcion = pedirElemento(); // Pido el elemento

               switch (opcion) {
                   case 1:
                       tienda.imprimirDatos(tienda.getPeliculas());
                       System.out.println("Introduce el titulo de la pelicula que quieres: ");
                       titulo = lector.nextLine();
                       if (tienda.exitePelicula(titulo)) {

                           tienda.alquilarPeliculaSocio(NIF, titulo);
                           System.out.println("PELICULA ANYADIDO EN EL MAIN");


                       } else {
                           System.out.println("No exite la pelicula " + titulo + ".");
                       }


                       break;
                   case 2:
                       tienda.imprimirDatos(tienda.getVideojuegos());
                       System.out.println("Introduce el titulo del videojuego que quieres: ");
                       titulo = lector.nextLine();
                       if (tienda.existeVideojuego(titulo)) {

                           tienda.alquilarVideojuegoSocio(NIF, titulo);
                           System.out.println("VIDEOJUEGO ANYADIDO EN EL MAIN");


                       } else {
                           System.out.println("No exite el videojuego " + titulo + ".");
                       }
                       break;
                   default:
                       break;
               }
           }else{
               System.out.println("El socio " + socio.getNombre() + " tiene recargos pendientes.");
           }

        }else{
            System.out.println("El socio con NIF " + NIF + " no exite.");
        }






    }

    /**
     * Pide un elemento (PELICULA O VIDIOJUEGOS)
     * @return La opcion deseada
     */
    public int pedirElemento(){
        int opcion = -1;

        do {
            Lib.limpiarPantalla();
            System.out.println("Cual elemento quieres alquilar?");
            System.out.println("1. Nueva pelicula");
            System.out.println("2. Nuevo Videojuego");
            System.out.println("0. Cancelar\n");
            System.out.println("Elija una opción: ");
            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 2) {
                    System.out.println("Elija una opción del menú [0-2]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 2);
        return  opcion;

    }


    /**
     * Los listados pedidos
     */
    public void listados(){
        int opcion = -1;
        int NIFSocio = 0;



        do{
            opcion = menuListados();
            switch(opcion){
                case 1:
                    System.out.println("***PELICULAS***\n");
                    tienda.imprimirDatos(tienda.getPeliculas());
                    System.out.println("***VIDEOJUEGOS***\n");
                    tienda.imprimirDatos(tienda.getVideojuegos());
                    System.out.println("***SOCIOS***");
                    tienda.imprimirDatos(tienda.getSocios());



                    break;
                case 2:

                    //Utilizo la clase Collector para realizar el sort para que sea ordenador alfabeticamente
                    //Por ahora no entiendo como funciona muy bien, lo encontré online
                    Collections.sort(tienda.getPeliculas(), Comparator.comparing(Pelicula::getTitulo));

                    //Luego imprimo las peliculas
                    tienda.imprimirDatos(tienda.getPeliculas());



                    break;
                case 3:
                    //Utilizo la clase Collector para realizar el sort para que sea ordenador alfabeticamente
                    Collections.sort(tienda.getVideojuegos(), Comparator.comparing(Videojuego::getAnyo));
                    tienda.imprimirDatos(tienda.getVideojuegos());
                    break;
                case 4:
                    System.out.println("Listado del histórico de alquileres de un socio ordenados por fecha de alquiler:");
                    NIFSocio = pedirNIF();
                    if(tienda.exiteSocio(NIFSocio)) {
                        tienda.imprimirAlquileres(NIFSocio);
                    }else{
                        System.out.println("No exite el socio con NIF "+ NIFSocio);
                    }

                        //

                    break;
                case 5:
                    System.out.println("LISTADO DE ALQUILERES DE UN SOCIO:");
                    NIFSocio = pedirNIF();
                    if(tienda.exiteSocio(NIFSocio)) {
                        tienda.imprimirAlquileres(NIFSocio);
                    }else{
                        System.out.println("No exite el socio con NIF "+ NIFSocio);
                    }


                    break;
                case 6:
                    System.out.println("Listado de los socios con recargos pendientes");
                    NIFSocio = pedirNIF();
                    if(tienda.exiteSocio(NIFSocio)) {
                        tienda.imprimirAlquileresregargosPendientes();
                    }else{
                        System.out.println("No exite el socio con NIF "+ NIFSocio);
                    }
                    break;

                case 0:
                    break;
                default:
                    break;

            }

        }while(opcion != 0);


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
            System.out.println("4. Listado del histórico de alquileres de un socio ordenados por fecha de alquiler");
            System.out.println("5. Listado de los alquileres actuales de un socio");
            System.out.println("6. Listado de los socios con recargos pendientes");
            System.out.println("---------------------");
            System.out.println("0. Cancelar\n");
            System.out.println("Elija una opción: ");
            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 6) {
                    System.out.println("Elija una opción del menú [0-7]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 6);
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
            System.out.println("3  Devolver multimedia ESTO FALLA");
            System.out.println("4  Listados");
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
