package com.oscarmorton.ejer6;

import com.github.javafaker.Faker;
import com.oscarmorton.utils.Lib;
import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tienda {
   private ArrayList<Multimedia> videojuegos;
    private ArrayList<Multimedia> peliculas;
    private ArrayList<Socio> socios;


    public Tienda(){
        videojuegos = new ArrayList<>();
        peliculas = new ArrayList<>();
        socios = new ArrayList<>();




    }

    /**
     * Comprueba si el socio exite.
     * @param NIF EL nif del socio que quieres buscar
     * @return Verdades si  exite, falso si no.
     */
    public boolean exiteSocio(int NIF){
        boolean encontrado = false;

        for(int i = 0; i < socios.size(); i++){
            if(NIF == socios.get(i).getNIF() ){
                encontrado = true;

            }

        }
        return  encontrado;

    }

    /**
     * Recorre el arraylist de peliculas para ver si exite el elemento con el titulo.
     * @param titulo el titulo del elemento que quieres selecionar
     * @return true si existe el elemento, false si no;
     */
    public boolean exitePelicula(String titulo){
        boolean encontrado = false;
        for(int i = 0; i < peliculas.size(); i++){
            if(peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)){
                encontrado = true;

            }

        }
        return  encontrado;

    }
    /**
     * Recorre el arraylist de peliculas para ver si exite el elemento con el titulo.
     * @param titulo el titulo del elemento que quieres selecionar
     * @return true si existe el elemento, false si no;
     */
    public boolean existeVideojuego(String titulo){
        boolean encontrado = false;
        for(int i = 0; i < videojuegos.size(); i++){
            if(videojuegos.get(i).getTitulo().equalsIgnoreCase(titulo)){
                encontrado = true;

            }

        }
        return  encontrado;

    }

    /**
     * Alquila una videojuego a un socio
     * @param NIFsocio El nif de socio
     * @param titulo El titulo de la videjuego deseada
     */
    public void alquilarVideojuegoSocio(int NIFsocio, String titulo){
        Multimedia videojuego = new Videojuego(null, null, null, null, null);
        LocalDate hoy = LocalDate.now();


        if (exiteSocio(NIFsocio) && existeVideojuego(titulo)){

            // Consigo la pelicula
            for(int i = 0; i < videojuegos.size(); i++){
                if(videojuegos.get(i).getTitulo().equalsIgnoreCase(titulo)){
                    videojuego = videojuegos.get(i);
                    videojuegos.remove(i); // borro la pelicula

                }

            }
            // consigo el socio
            for (int i = 0; i < socios.size(); i++) {
                if (NIFsocio == socios.get(i).getNIF()) {
                    socios.get(i).getHistorico().put(videojuego,hoy);
                    socios.get(i).setAlquilando(true);


                }

            }

        }


    }


    /**
     * Alquila una pelicula a un socio
     * @param NIFsocio El nif de socio
     * @param titulo El titulo de la pelicula deseada
     */
    public void alquilarPeliculaSocio(int NIFsocio, String titulo){
        Multimedia pelicula = new Pelicula(null, null, null, null, 0, null, null);
        LocalDate hoy = LocalDate.now();


            if (exiteSocio(NIFsocio) &&exitePelicula(titulo)){

                // Consigo la pelicula
                for(int i = 0; i < peliculas.size(); i++){
                    if(peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)){
                       pelicula = peliculas.get(i);
                       peliculas.remove(i); // borro la pelicula

                    }

                }
                // consigo el socio
                for (int i = 0; i < socios.size(); i++) {
                    if (NIFsocio == socios.get(i).getNIF()) {
                        socios.get(i).getHistorico().put(pelicula,hoy);
                        System.out.println("PELICULA ANYADIDO EN EL HASHMAP");
                        socios.get(i).setAlquilando(true);

                    }

                }

            }


    }

    /**
     * Consigo cuanto dinero debe el socio
     * @param antiguedadDeMultimedia La antiguadad del multimedia
     * @param dias Los dias que tardó en devolver
     * @return La cantidad de dinero debido
     */
    public double cantidadDineroDebido(GregorianCalendar antiguedadDeMultimedia, long dias, boolean ispelicula){
        // Los objetos multimedia se alquilan a los socios durante un periodo máximo de 3 días. El alquiler
        //tendrá un precio base de 4 €. El alquiler se ve rebajado 1 € si la pelicula es anterior al año
        //2012 o si el videojuego es anterior al año 2010.

        //Cuando el socio devuelve el objeto multimedia se debe comprobar que está dentro del plazo de
        //alquiler de 3 días. Por cada día que pase del mencionado periodo, el socio deberá pagar un
        //recargo de 2 €.

        double cantidadDinero = 4;
        long aux = 0;



        if(ispelicula && antiguedadDeMultimedia.get(Calendar.YEAR) < 2012){ // Si no es pelicula, sera videojuego

                cantidadDinero -= 1;

        }else if(!ispelicula && antiguedadDeMultimedia.get(Calendar.YEAR) < 2010){
            cantidadDinero -=1;
        }

       if(dias >= 3 ) { // si los dias superan los 3 dias, paga 2 euros mas por dia
          aux = dias - 3; // consigo los dias sobrados
           cantidadDinero += (aux * 2);
       }
        return cantidadDinero;


    }

    /** ME HE COMPLICADO LA VIDA CON ESTO. ESTO FALLA!!
     * Devuelve la multimedia a la tienda
     * @param NIFsocio EL nif del socio
     * @param tituloMultimedia El titulo del multimedia
     * @param isPelicula Si es pelicula o no.
     * @return Devuelve el dinero que debe el socio
     */
    public double devolverMultimedia(int NIFsocio, String tituloMultimedia, boolean isPelicula ){
        Socio socio;
        Multimedia multimedia;
        LocalDate fechaDevolucion;
        LocalDate fechaAlquilar;
        GregorianCalendar antiguedadMultimedia;
        HashMap<Multimedia, LocalDate> historial;
        boolean devuelto = false;
        long diasAlquilado = 0;

        double dineroDebido = 0;

        if(exiteSocio(NIFsocio)){
            socio = getSocioWithNIF(NIFsocio);
            historial = socio.getHistorico();
            if(isPelicula){

                for (Map.Entry<Multimedia, LocalDate> entry : historial.entrySet()) { // Recorro el hitorial para encotrar el titulo que quiero
                    System.out.println("ENTRADO EN PRIMER FOR");

                    if(entry.getKey().getTitulo().equalsIgnoreCase(tituloMultimedia ) && !devuelto){
                        System.out.println("ENTRADO EN PRIMER IF");
                        multimedia = entry.getKey(); // consigo la multimedia para eliminar.
                        antiguedadMultimedia = multimedia.getAnyo();
                        fechaAlquilar = entry.getValue();  // consigo la fecha que alquilo.

                        for(int i = 0; i < peliculas.size(); i++){ // recorro la lista de peliculas
                            System.out.println("ENTRADO EN SEGUNDO FOR");

                            if(multimedia.getTitulo().equalsIgnoreCase(peliculas.get(i).getTitulo())){
                                System.out.println("ENTRADO EN SEGUNDO IF");
                                peliculas.add(multimedia); // devuelvo la pelicula a la lista de peliculas de la tienda.
                                socio.setAlquilando(false); // Ahora  empleado ya no está alquilando.
                                devuelto = true;
                                System.out.println("Devuelto a la tienda con exito");
                                fechaDevolucion = LocalDate.now();
                                diasAlquilado =  ChronoUnit.DAYS.between(fechaAlquilar, fechaDevolucion); // Consigo las dias entre  el inicio y cuando devolvio
                                dineroDebido = cantidadDineroDebido(antiguedadMultimedia,diasAlquilado, true);



                            }
                        }
                    }
                }
                return  dineroDebido;

            }else{
                for (Map.Entry<Multimedia, LocalDate> entry : historial.entrySet()) { // Recorro el hitorial para encotrar el titulo que quiero

                    if(entry.getKey().getTitulo().equalsIgnoreCase(tituloMultimedia)){
                        multimedia = entry.getKey(); // consigo la multimedia para eliminar.
                        antiguedadMultimedia = multimedia.getAnyo();
                        fechaAlquilar = entry.getValue();  // consigo la fecha que alquilo.

                        for(int i = 0; i < videojuegos.size(); i++){ // recorro la lista de peliculas
                            if(multimedia.getTitulo().equalsIgnoreCase(videojuegos.get(i).getTitulo())){
                                videojuegos.add(multimedia); // devuelvo la pelicula a la lista de peliculas de la tienda.
                                socio.setAlquilando(false); // Ahora  empleado ya no está alquilando.
                                System.out.println("Devuelto a la tienda con exito");
                                fechaDevolucion = LocalDate.now();
                                diasAlquilado =  ChronoUnit.DAYS.between(fechaAlquilar, fechaDevolucion); // Consigo las dias entre  el inicio y cuando devolvio
                                dineroDebido = cantidadDineroDebido(antiguedadMultimedia,diasAlquilado, false);



                            }
                        }
                    }
                }
                return  dineroDebido;

            }

        }else{
            System.out.println("NO EXITE EL SOCION CON NIF: " + NIFsocio);
        }

        return  dineroDebido;
    }



    /**
     * Devuelve  el socio con el NIF indicado como parametro
     * @param NIF
     * @return
     */
    public Socio getSocioWithNIF(int NIF){
        Socio socio = new Socio(0,null,LocalDate.of(1980,01,01),null,false);
        if(exiteSocio(NIF)){
            for(int i = 0; i < socios.size(); i++){
                if(socios.get(i).getNIF() == NIF){
                    socio = socios.get(i);
                }

            }
            return socio;
        }else{
            System.out.println(" NO EXISTE EL SOCIO CON NIF: " + NIF);
            return  null;
        }


    }

    /**
     * Genera los datos aleatorios
     * @param cantidadPeliculas La cantidad de peliculas
     * @param cantidadVideojuegos La cantidad de videojuegos
     * @param cantidadSocios La cantidad de socios
     */
    public void crearDatosAleatorios(int cantidadPeliculas, int cantidadVideojuegos, int cantidadSocios){
        crearPeliculaAleatoria(cantidadPeliculas);
        crearVideojuegosAleatorios(cantidadVideojuegos);
        crearSocioAleatorio(cantidadSocios);



    }

    /**
     * Imprime el contenido de un arraylist
     * @param datos El arraylist de datos
     */
    public void imprimirDatos(ArrayList datos){

        for(int i = 0; i < datos.size(); i++){
            System.out.println(datos.get(i).toString());
        }

    }

    /**
     * Imprime los historios de todos los socios.
     */
    public void imprimirAlquileres(){
        for(int i = 0; i < socios.size(); i++){
            System.out.println(socios.get(i).getHistorico());


        }

    }
    /**
     * Imprime la historia de alquileres de los socios con recargos pendientes
     * @param
     */
    public void imprimirAlquileresregargosPendientes(){
        for(int i = 0; i < socios.size(); i++){
            if(socios.get(i).isAlquilando()) {
                System.out.println(socios.get(i).getHistorico());
            }

        }

    }

    /**
     * Imprime la historia de alquileres de los socios:
     * @param NIFsocio
     */
    public void imprimirAlquileres(int NIFsocio){
        for(int i = 0; i < socios.size(); i++){
            if(NIFsocio == socios.get(i).getNIF()) {
                System.out.println(socios.get(i).getHistorico());
            }

        }

    }

    /**
     * Genera datos aleatorios
     * TO-DO RETURNS AN ARRAYLIST
     * @param cantidadPeliculas La cantidad de peliculas
     * @param cantidadVideojuegos la cantidad de videojuegos
     * @param cantidadSocios La cantidad de socios
     */


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

    /**
     * Genera los socios aleatorios
     * @param cantidad la cantidad de socios que deseas crear
     */
    public void crearSocioAleatorio(int cantidad){
        Faker faker;
        int numeroAleatorio;

        int nif;
        String nombre;
        String poblacion;
        boolean alquilando = false;



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

            // Pongo si está alquilando aleatoriamente entre true y false;
            numeroAleatorio = Lib.aleatorio(0,1);
            if(numeroAleatorio == 1){
                alquilando = true;

            }else{
                alquilando = false;
            }


            socios.add(new Socio(nif,nombre,fechaAleatoria,poblacion, alquilando));

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

    public ArrayList getVideojuegos() {
        return videojuegos;
    }

    public ArrayList getPeliculas() {
        return peliculas;
    }

    public ArrayList getSocios() {
        return socios;
    }
}
