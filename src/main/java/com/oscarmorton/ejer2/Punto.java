package com.oscarmorton.ejer2;

public class Punto {
    protected double x;
    protected double y;
    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Punto(){
        this.x = 2.0;
        this.y = 4.0;
    }

    /**
     * Calcula la distancia
     * @param p
     * @return
     */
    public double distancia(Punto p){
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }

    /**
     * Copnvierte el punto a un String
     * @return El punto en formato String
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}

