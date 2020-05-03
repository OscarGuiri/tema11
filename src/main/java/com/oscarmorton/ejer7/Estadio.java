package com.oscarmorton.ejer7;

import java.util.ArrayList;

public class Estadio {
    private Zona[] zonas;
    private String localizacion;

    public Estadio(int nZonas, String localizacion, int asientosVIP, int nFilasZonas, int nColumnasZonas) {
        zonas =  new Zona[nZonas];
        this.localizacion = localizacion;
        for(int i = 0; i < zonas.length; i++){
            zonas[i] = new Zona(asientosVIP,nFilasZonas,nColumnasZonas, localizacion);
        }

    }
    public void crearAsientosAleatorios(){
        for(int i = 0; i < zonas.length; i++){
            zonas[i].asientoAleatoriasZona();
        }
    }
    public void imprimeEstadio(){
        for(int i = 0; i < zonas.length; i++){
            System.out.println("****ZONA "+ (i + 1) + "*****");
            zonas[i].imprimirAsientosZona();
        }
    }
}
