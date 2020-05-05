package com.oscarmorton.ejer7;

import java.util.UUID;

public abstract class Asientos  {
    protected boolean asientoVIP;
    protected UUID nEntrada;
    protected  int nFila;
    protected  int nAsiento;
    protected  boolean ocupado;
    protected String zona;

    //Contructor con parametros
    public Asientos(UUID nEntrada, String zona,  int nFila, int nAsiento, boolean ocupado) {
        this.nEntrada = nEntrada;
        this.nFila = nFila;
        this.nAsiento = nAsiento;
        this.ocupado = ocupado;
        this.zona = zona;

    }

    public boolean isAsientoVIP() {
        return asientoVIP;
    }

    //GETTERS AND SETTERS

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
