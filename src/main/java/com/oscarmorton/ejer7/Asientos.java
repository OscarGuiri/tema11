package com.oscarmorton.ejer7;

import java.util.UUID;

public abstract class Asientos  {
    protected UUID nEtrada;
    protected  int nFila;
    protected  int nAsiento;
    protected  boolean ocupado;
    protected String zona;

    //Contructor con parametros
    public Asientos(UUID nEtrada, String zona,  int nFila, int nAsiento, boolean ocupado) {
        this.nEtrada = nEtrada;
        this.nFila = nFila;
        this.nAsiento = nAsiento;
        this.ocupado = ocupado;
        this.zona = zona;
    }

    public boolean isOcupado() {
        return ocupado;
    }
}
