package com.oscarmorton.ejer3;

public class CotxeCanviAutomatic extends Coche {

    public CotxeCanviAutomatic(int matricula) {
        super(matricula);
    }
    @Override
    /**
     * Acelera el coche
     * @param velocidad
     */
    public void acelerar(double velocidad){
        double aux;

        aux = this.velocidad + velocidad;
        if(aux >= 0){
            if(aux >= this.velocidadesMaximasMachas[this.marchaActual]){ // si la verlocidad es mas alto o igual, sube la marcha
                this.marchaActual += 1;
            }

            if( this.velocidadesMaximasMachas[this.marchaActual] > aux) {
                this.velocidad += velocidad;
            }


        }else{
            System.out.println("La velocidad no puede ser menor que 0");
        }
    }
    @Override
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
}
