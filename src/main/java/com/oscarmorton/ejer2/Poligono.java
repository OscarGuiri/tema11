package com.oscarmorton.ejer2;

import javax.annotation.processing.SupportedOptions;
import java.util.ArrayList;

public class Poligono {
    protected ArrayList<Punto> puntos;
    public Poligono(ArrayList puntos){
        this.puntos = puntos;
    }


    /**
     * Devuelve el numero de puntos que contiene el poligono.
     * @return
     */
    public int numVertices(){

        return puntos.size();

    }

    /**
     * Convierte todos los puntos en un string.
     * @return El estring, separado por un espacio por punto.
     */
    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder();
        for(int i = 0; i < puntos.size(); i++){
            sb.append(puntos.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();

    }

    /**
     * Calcula el perimetro del poligono
     * @return El perimetro en formato double.
     */
    public double perimetro(){
        double perimetro = 0;
        double sumaLados = 0;

        for(int i = 0; i < puntos.size(); i++){
          sumaLados += puntos.get(i).distancia(puntos.get(i));
        }
        return perimetro = numVertices() * sumaLados;
    }

}
