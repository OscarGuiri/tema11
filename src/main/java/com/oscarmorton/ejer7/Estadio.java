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
        crearAsientosAleatorios(false);

    }
    /*
        crea asientos aleatorios en las zonas del del estadio
     */
    public void crearAsientosAleatorios(boolean generarOcupados){
        for(int i = 0; i < zonas.length; i++){
            zonas[i].asientoAleatoriasZona(generarOcupados);
        }
    }
    //todo EXPLICAR MEJOR COMO FUNCIONA EL SISTEMA DE VENDER
    public void venderAsiento(int fila, int columna, int zona){
        boolean vendio = false;

        for(int j = 0; j < zonas[zona - 1].getAsientos().length; j++){
            for(int k = 0; k < zonas[zona - 1].getAsientos()[j].length; k++){

                if( (fila - 1) == j   && (columna - 1) == k  ){

                    System.out.println("coinciden j y k");
                    if(!zonas[zona - 1].getAsientos()[j][k].isOcupado()){
                        zonas[zona - 1].getAsientos()[j][k].setOcupado(true);
                        vendio = true;
                    }else{
                        System.out.println("Esta zona esta ocupada");
                    }


                }
            }
        }
        if(vendio){
            System.out.println("vendido con exito");
        }else{
            System.out.println("No vendido");
        }


    }

    /**
     * public void venderAsiento(int fila, int columna, int zona){
     *         for(int i = 0; i < zonas.length; i++){
     *             for(int j = 0; j < zonas[i].getAsientos().length; j++){
     *                 for(int k = 0; k < zonas[i].getAsientos()[j].length; k++){
     *                     if(fila == (j + 1) && columna == (k + 1)){
     *                         if(!zonas[i].getAsientos()[j][k].isOcupado()){
     *                             zonas[i].getAsientos()[j][k].setOcupado(true);
     *                             System.out.println("Vendida con exito");
     *                         }else{
     *                             System.out.println("Esta zona esta ocupada");
     *                         }
     *
     *
     *                     }
     *                 }
     *             }
     *         }
     *
     *     }
     * Imprime es estadio completo
     */
    public void imprimeEstadio(){
        for(int i = 0; i < zonas.length; i++){
            System.out.println("**********ZONA "+ (i + 1) + "**************");
            System.out.println();
            zonas[i].imprimirAsientosZona();
            System.out.println();
        }
        System.out.println();

    }
    public void imprimirEstadiosAsientosLibres(){
        for(int i = 0; i < zonas.length; i++){
            System.out.println("**********ZONA "+ (i + 1) + "**************");
            System.out.println();
            zonas[i].imprimirAsientosLibresZona();
            System.out.println();
        }
        System.out.println();
    }
    public void imprimirEstadiosAsientosOcupados(){
        for(int i = 0; i < zonas.length; i++){
            System.out.println("**********ZONA "+ (i + 1) + "**************");
            System.out.println();
            zonas[i].imprimirAsientosOcupadosZona();
            System.out.println();
        }
        System.out.println();
    }

    public Zona[] getZonas() {
        return zonas;
    }
}
