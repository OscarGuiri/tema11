package com.oscarmorton.ejer3;

public class CotxeCanviManual extends Coche {
    public CotxeCanviManual(int matricula) {
        super(matricula);
    }
    @Override
    public void cambiaMarcha(int marcha){
        if(marcha > 0){
            this.marchaActual = marcha;
        }else{
            System.out.println("La marcha no puede ser inferior a 0");
        }

    }
}
