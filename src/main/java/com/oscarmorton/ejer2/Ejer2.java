package com.oscarmorton.ejer2;

import java.util.ArrayList;

public class Ejer2 {
    public Ejer2(){
        // CREO EL POLIGONO
        Punto p1 = new Punto(1.0, 2.0);
        Punto p2 = new Punto(2.0, 6);
        Punto p3 = new Punto(2.0, 4.0);
        Punto p4 = new Punto(8.0, 10.0);
        ArrayList puntos = new ArrayList();
        puntos.add(p1);
        puntos.add(p2);
        puntos.add(p3);
        puntos.add(p4);
        Poligono poligono = new Poligono(puntos);

        System.out.println("Puntos del poligono:");
        System.out.println(poligono.toString());
        System.out.println("Perimetro del poligono:");
        System.out.println((poligono.perimetro()));



    }
}
