package com.oscarmorton.ejer6;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Multimedia {
    enum  FORMATO{ CD, DVD, BLUE_RAY, ARCHIVO, ERROR};
    protected String titulo;
    protected String autor;
    protected FORMATO formato;
    protected GregorianCalendar anyo;

    public Multimedia(String titulo, String autor, FORMATO formato, GregorianCalendar anyo) {
        this.titulo = titulo;
        this.autor = autor;
        this.formato = formato;
        this.anyo = anyo;
    }


    //Devuelve true en caso que el titulo y el autor son iguales
    public boolean equals(Multimedia o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multimedia that = (Multimedia) o;
        return titulo.equals(that.titulo) &&
                autor.equals(that.autor);
    }



    @Override
    /**
     * Devuelve los atributos del objeto en formato string
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("%-10s ",titulo) +
                String.format("%-10s ",autor) +
                String.format("%-10s ",formato.toString()) + "\t" +
                String.format("%10s ", sdf.format(anyo.getTime()));

    }

    // GETTERS AND SETTERS
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public FORMATO getFormato() {
        return formato;
    }

    public GregorianCalendar getAnyo() {
        return anyo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFormato(FORMATO formato) {
        this.formato = formato;
    }

    public void setAnyo(GregorianCalendar anyo) {
        this.anyo = anyo;
    }
}
