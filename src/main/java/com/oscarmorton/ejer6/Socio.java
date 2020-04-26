package com.oscarmorton.ejer6;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import java.util.HashMap;
import java.util.Map;

public class Socio {
    private int NIF;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String poblacion;
    private  boolean alquilando;
    private HashMap<Multimedia, LocalDate> historico;

    public Socio(int NIF, String nombre, LocalDate fechaNacimiento, String poblacion, boolean alquilando) {
        //Antes de crear el objeto socio, compruebo a ver si tiene 18 años, mirando si desde su fecha de nacimiento hasta el dia de hoy suma mas o igual que 18 años.
        LocalDate hoy = LocalDate.now();
        Period p = Period.between(fechaNacimiento, hoy);
        if( p.getYears() >= 18){
            this.NIF = NIF;
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.poblacion = poblacion;
            this.alquilando = alquilando;
            historico = new HashMap<>();
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

        return String.format("NIF: %10s ",NIF) +
                String.format("\nNOMBRE: %10s \n", nombre +
                String.format("\nALQUILANDO: %10s \n", alquilando +
                String.format("\nPOBLACION: %10s \n",poblacion)));


    }

    public String mostrarHistorioSocio(){
        return historico.entrySet().toString();

    }

    //GETTERS AND SETTERS

    public int getNIF() {
        return NIF;
    }

    public boolean isAlquilando() {
        return alquilando;
    }

    public void setAlquilando(boolean alquilando) {
        this.alquilando = alquilando;
    }

    public HashMap<Multimedia, LocalDate> getHistorico() {
        return historico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHistorico(HashMap<Multimedia, LocalDate> historico) {
        this.historico = historico;
    }
}
