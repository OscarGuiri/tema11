package com.oscarmorton.ejer7;

import com.oscarmorton.utils.Lib;

import java.util.UUID;

public class Zona {
    private Asientos[][] asientos;
    private  int asientosVIP;
    private  String zona;



    public Zona(int asientosVIPZona, int nFilas, int nColumnas, String zona) {
        this.asientos = new Asientos[nFilas][nColumnas];
        this.asientosVIP = asientosVIPZona;
        this.zona = zona;
    }
    public void imprimirAsientosZona(){
        for( int i = 0; i < asientos.length; i++){
            System.out.println("****FILA " + (i +1) + "*****");
            for(int j = 0; j < asientos[i].length; j++){

                if( asientos[i][j].isOcupado()){
                    if(asientos[i][j].isAsientoVIP()){
                        System.out.print((j +1) + ":");
                        System.out.print("| OCUPADO VIP |");
                    }else{
                        System.out.print((j +1) + ":");
                        System.out.print( "| OCUPADO | ");
                    }

                }else{
                    if(asientos[i][j].isAsientoVIP()){
                        System.out.print((j +1) + ":");
                        System.out.print("| LIBRE VIP | ");

                    }else{
                        System.out.print((j +1) + ":");
                        System.out.print("| LIBRE | ");

                    }
                }

            }
            System.out.println();
        }

    }
    public void imprimirAsientosLibresZona(){
        for( int i = 0; i < asientos.length; i++){
            System.out.println("****FILA " + (i +1) + "*****");
            for(int j = 0; j < asientos[i].length; j++){

                if( !asientos[i][j].isOcupado()){
                    if(asientos[i][j].isAsientoVIP()){
                        System.out.print((j +1) + ":");
                        System.out.print("| LIBRE VIP | ");

                    }else{
                        System.out.print((j +1) + ":");
                        System.out.print("| LIBRE | ");

                    }
                }
            }

            System.out.println();
        }

    }
    public void imprimirAsientosOcupadosZona(){
        for( int i = 0; i < asientos.length; i++){
            System.out.println("****FILA " + (i +1) + "*****");
            for(int j = 0; j < asientos[i].length; j++){

                if(asientos[i][j].isOcupado()){
                    if(asientos[i][j].isAsientoVIP()){
                        System.out.print((j +1) + ":");
                        System.out.print("| OCUPADO VIP |");
                    }else{
                        System.out.print((j +1) + ":");
                        System.out.print( "| OCUPADO | ");
                    }
                }
            }

            System.out.println();
        }

    }

    /**
     * Genera asientos aleatorios
     * @param generarOcupados Si quieres generar asientos ocupados aleatorios
     */
    public void asientoAleatoriasZona(boolean generarOcupados){
        int aux = this.asientosVIP;
        int nAleatorio = 0;
        int nAsiento = 0;
        boolean ocupado = false;


        for(int i = 0; i < asientos.length; i++){
           if(aux > 1) { // Primero creo los asientos VIP


               for (int j = 0; j < asientos[i].length; j++) {
                   nAleatorio = Lib.aleatorio(0,2);
                   if(generarOcupados) {
                       if (nAleatorio == 1) {
                           ocupado = true;
                       } else {
                           ocupado = false;
                       }
                   }else{
                       ocupado = false;
                   }

                   asientos[i][j] = new AsientoVIP(UUID.randomUUID(), this.zona, i, nAsiento, ocupado,  Lib.aleatorio(111111,999999));
                   aux--;
                   nAsiento++;
               }
           }else{ // Ahora creo los asientos normales.
               for (int j = 0; j < asientos[i].length; j++) {
                   nAleatorio = Lib.aleatorio(0,1);
                   if(generarOcupados) {
                       if (nAleatorio == 1) {
                           ocupado = true;
                       } else {
                           ocupado = false;
                       }
                   }else{
                       ocupado = false;
                   }

                   asientos[i][j] = new AsientoNormal(UUID.randomUUID(), this.zona, i, nAsiento, ocupado, Lib.aleatorio(0, nAsiento));
                   nAsiento++;
               }

           }
        }

    }

    public Asientos[][] getAsientos() {
        return asientos;
    }
}
