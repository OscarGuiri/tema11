package com.oscarmorton.ejer6;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;

public class Socio {
    private int NIF;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String poblacion;
    private  boolean alquilando;

    public Socio(int NIF, String nombre, LocalDate fechaNacimiento, String poblacion) {
        //Antes de crear el objeto socio, compruebo a ver si tiene 18 años, mirando si desde su fecha de nacimiento hasta el dia de hoy suma mas o igual que 18 años.
        LocalDate hoy = LocalDate.now();
        Period p = Period.between(fechaNacimiento, hoy);
        if( p.getYears() >= 18){
            this.NIF = NIF;
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.poblacion = poblacion;
            this.alquilando = false;
        }else{
            System.out.println("ERROR, NO SE PUEDE SER SOCIO SIENDO MENOR DE EDAD");
            System.out.println("SOCIO NO CREADO");
        }



    }

    @Override
    /**
     * Devuelve los atributos del objeto en formato string
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("NIF: %10s ",NIF) +
                String.format("\nNOMBRE: %10s \n", nombre +
                String.format("\nFECHA NACIMIENTO: %10s \n", fechaNacimiento +
                String.format("\nPOBLACION: %10s \n",poblacion)));


    }

    //GETTERS AND SETTERS
    public void setAlquilando(boolean alquilando) {
        this.alquilando = alquilando;
    }
}
