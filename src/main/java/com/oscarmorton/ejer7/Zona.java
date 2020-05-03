package com.oscarmorton.ejer7;

import com.oscarmorton.utils.Lib;

import java.util.UUID;

public class Zona {
    private Asientos[][] asientos;
    private  int nZonasVip;
    private  String zona;



    public Zona(int asientosVIP, int nFilas, int nColumnas, String zona) {
        this.asientos = new Asientos[nFilas][nColumnas];
        this.nZonasVip = nZonasVip;
        this.zona = zona;
    }
    public void imprimirAsientosZona(){
        for( int i = 0; i < asientos.length; i++){
            System.out.println("****FILA " + i + "*****");
            for(int j = 0; j < asientos[i].length; j++){
                if( asientos[i][j].isOcupado()){
                    System.out.println("OCUPADO");
                }else{
                    System.out.println("LIBRE");
                }

            }
        }

    }

    /**
     * Genera asientos aleatoriamente.
     */
    public void asientoAleatoriasZona(){
        int aux = this.nZonasVip;
        int nAleatorio = 0;
        int nAsiento = 0;
        boolean ocupado = false;

        for(int i = 0; i < asientos.length; i++){
           if(aux != 0) { // Primero creo los asientos VIP
               aux--;
               for (int j = 0; j < asientos[i].length; j++) {
                   nAleatorio = Lib.aleatorio(0,1);
                   if( nAleatorio == 0){
                       ocupado = false;
                   }else{
                       ocupado = true;
                   }

                   asientos[i][j] = new AsientoVIP(UUID.randomUUID(), this.zona, i, nAsiento, ocupado,  Lib.aleatorio(111111,999999));
                   nAsiento++;
               }
           }else{ // Ahora creo los asientos normales.
               for (int j = 0; j < asientos[i].length; j++) {
                   nAleatorio = Lib.aleatorio(0,1);
                   if( nAleatorio == 0){
                       ocupado = false;
                   }else{
                       ocupado = true;
                   }

                   asientos[i][j] = new AsientoNormal(UUID.randomUUID(), this.zona, i, nAsiento, ocupado, Lib.aleatorio(0, nAsiento));
                   nAsiento++;
               }

           }
        }

    }
}
