package com.oscarmorton.ejer7;

import com.github.javafaker.Faker;
import com.oscarmorton.utils.Lib;

import java.util.GregorianCalendar;

public class Partido {
    enum AFLUENCIA{BAJA_AFLUENCIA, MEDIA_AFLUENCIA, ALTA_AFLUENCIA, ERROR}

    private  AFLUENCIA afluencia;
    private GregorianCalendar fechaPartido;
    private String equipoLocal;
    private String equipoVisitante;
    private Estadio estadio;
    private double dineroAcumulado;
    private boolean jugado;

    public Partido(AFLUENCIA afluencia, GregorianCalendar fechaPartido, String equipoLocal, String equipoVisitante,  Estadio estadio) {
        this.afluencia = afluencia;
        this.fechaPartido = fechaPartido;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.estadio = estadio;
        this.jugado = false;
    }



    /**
     * Pide la afluencia de una partida
     * @param opcion
     * 1. BAJA_AFLUENCIA
     * 2. MEDIA_AFLUENCIA
     * 3.  ALTA_AFLUENCIA
     * @return  La afluencia correspondiente
     */
    public AFLUENCIA conseguirAfluencia(int opcion){
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
    //GETTERS AND SETTERS
    public void venderEntrada(int nFila, int nColumna, int zona){
        estadio.venderAsiento(nFila, nColumna, zona);

    }


    public double getDineroAcumulado() {
        return dineroAcumulado;
    }

    public void setDineroAcumulado(double dineroAcumulado) {
        this.dineroAcumulado = dineroAcumulado;
    }

    public AFLUENCIA getAfluencia() {
        return afluencia;
    }

    public GregorianCalendar getFechaPartido() {
        return fechaPartido;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public String getGetEquipoVisitante() {
        return equipoVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }
}
