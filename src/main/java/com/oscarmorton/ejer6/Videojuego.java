package com.oscarmorton.ejer6;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Videojuego extends  Multimedia  {
    protected   enum PLATAFORMA{PLAYSTATION, XBOX, NINTENDO, ERROR};
    protected PLATAFORMA plataforma;

    //CONTRUCTOR
    public Videojuego(String titulo, String autor, FORMATO formato, GregorianCalendar anyo, PLATAFORMA plataforma) {
        super(titulo, autor, formato, anyo);
        this.plataforma = plataforma;
    }
    @Override
    /**
     * Devuelve los atributos del objeto en formato string
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("TITULO: %-10s  \n",titulo) +
                String.format("FOMRMATO: %-10s \n",formato.toString()) +
                String.format("ANYO: %10s \n", sdf.format(anyo.getTime())) +
                String.format("PLATAFORMA: %10s \n",plataforma.toString());


    }
}
