package com.oscarmorton.ejer3;

import java.util.Arrays;

public class Coche {
    protected int matricula;
    protected double velocidad;
    protected int marchaActual;
    protected int[] velocidadesMaximasMachas;

    public  Coche(int matricula){
        this.velocidadesMaximasMachas = new int[5];
        this.velocidadesMaximasMachas[0] = 30;
        this.velocidadesMaximasMachas[1] = 40;
        this.velocidadesMaximasMachas[2] = 60;
        this.velocidadesMaximasMachas[3] = 80;
        this.velocidadesMaximasMachas[4] = 130;
        this.matricula = matricula;
        this.velocidad = 0;
        this.marchaActual = 1;

    }

    /**
     * Acelera el coche
     * @param velocidad
     */
    public void acelerar(double velocidad){
        double aux;

        aux = this.velocidad + velocidad;
        if(aux >= 0){
            if( this.velocidadesMaximasMachas[this.marchaActual] > aux) {
                this.velocidad += velocidad;
            }

        }else{
            System.out.println("La velocidad no puede ser menor que 0");
        }
    }

    /**
     *
     * @param velocidad
     */
    public void frenar(double velocidad){
        double aux;
        aux = this.velocidad - velocidad;
        if(aux >= 0){
            this.velocidad -= velocidad;
        }else{
            System.out.println("La velocidad no puede ser menor que 0");
        }

    }
   protected void cambiaMarcha(int marcha){
        if(marcha > 0){
            this.marchaActual = marcha;
        }else{
            System.out.println("La marcha no puede ser inferior a 0");
        }

   }

    /**
     * Imprime las detalles del coche
     * @return
     */
   @Override
    public String toString(){
        return String.format("Matricula: %-10s",matricula) +
                String.format(" Velocidad Actual: %-14s",velocidad) +
                String.format(" Marcha actual %-14s",marchaActual) +
                String.format(" Maxima velocidades marchas %-14s", Arrays.toString(velocidadesMaximasMachas));



    }

    //GETTERS

    public int getMatricula() {
        return matricula;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public int getMarchaActual() {
        return marchaActual;
    }

    public int[] getVelocidadesMaximasMachas() {
        return velocidadesMaximasMachas;
    }
    //SETTERS


    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public void setMarchaActual(int marchaActual) {
        this.marchaActual = marchaActual;
    }

    public void setVelocidadesMaximasMachas(int[] velocidadesMaximasMachas) {
        this.velocidadesMaximasMachas = velocidadesMaximasMachas;
    }
}
