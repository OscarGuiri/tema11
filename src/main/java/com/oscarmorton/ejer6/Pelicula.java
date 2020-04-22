package com.oscarmorton.ejer6;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Pelicula extends  Multimedia {
    private double duracion;
    private String actorPrincipal;
    private  String actrizPrincipal;


    //CONTRUCTORES
    public Pelicula(String titulo, String autor, FORMATO formato, GregorianCalendar anyo, double duracion, String actorPrincipal, String actrizPrincipal) {
        super(titulo, autor, formato, anyo);
        this.duracion = duracion;
        this.actorPrincipal = actorPrincipal;
        this.actrizPrincipal = actrizPrincipal;
    }
    public Pelicula(String titulo, String autor, FORMATO formato, GregorianCalendar anyo) {
        this(titulo, autor, formato, anyo, 0.00, "DESCONOCIDO", "DESCONOCIDO");
    }
    public Pelicula(String titulo, String autor, FORMATO formato, GregorianCalendar anyo, double duracion) {
       this(titulo, autor, formato, anyo, duracion, "DESCONOCIDO", "DESCONOCIDO");
    }

    //TO STRING


    @Override
    /**
     * Devuelve los atributos del objeto en formato string
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("TITULO: %-10s  \n",titulo) +
                String.format("FOMRMATO: %-10s \n",formato.toString()) +
                String.format("ANYO: %10s \n", sdf.format(anyo.getTime())) +
                String.format("DURACION: %.2f \n",duracion) +
                String.format("ACTOR: %-10s \n",actorPrincipal) +
                String.format("ACTRIZ: %-10s \n",actrizPrincipal);

    }
}
