package com.oscarmorton.ejer3;

import java.util.Scanner;

public class Ejer3 {
    private Scanner lector;
    private Coche coche;


    public Ejer3(){
        int opcion = -1;
        double velocidad = 0;
        boolean automatico = false;
        int matricula = 0;
        System.out.println("***CREANDO COCHE***");
        matricula = pedirMatricula();
        automatico = isAutomatico();
        //Creo los coches, depende de si es automatico o no, creo lo deseado.
        if (automatico) {
             coche = new CotxeCanviAutomatic(matricula);
        }else{
            coche = new CotxeCanviManual(matricula);
        }


        System.out.println(coche.toString());
        do {

            opcion = accerelarFrenar();
           switch (opcion){
               case 1:
                   velocidad = pedirVelocidad();
                   coche.acelerar(velocidad);
                   break;
               case 2:
                   velocidad = pedirVelocidad();
                   coche.frenar(velocidad);
                   break;
               case 3:
                   coche.cambiaMarcha(coche.marchaActual + 1);
                   break;
               case 4:
                   coche.cambiaMarcha(coche.marchaActual - 1);
                   break;
               case 0:
                   System.out.println("Hasta pronto!");
                   break;
               default:
                   System.out.println("ERROR");
                   break;
           }
            System.out.println( coche.toString());


        }while(opcion != 0);


    }

    /**
     * Pide la verlocidad y comprueba que no es menor que 0
     * @return
     */
    public double pedirVelocidad(){
        lector = new Scanner(System.in);
        double velociadad = 10;
        boolean valido = false;

        do {
            System.out.println("Introduce la velocidad deseada: ");
            try {
                velociadad = Double.parseDouble(lector.nextLine());
                if(velociadad > 0){
                    valido = true;
                }else{
                    System.out.println("No puedes introducir una velocidad negativa");
                }
            }catch (NumberFormatException nfe){
                System.out.println("Tienes que introducir la velocidad en numeros");
            }

        }while(!valido);
        return  velociadad;

    }

    /**
     * Pide al usuario is es automatico el coche
     * Si lo es, devuelve true;
     * @return
     */
    public boolean isAutomatico(){
        boolean automatico = false;
        boolean valido = false;
        lector = new Scanner(System.in);
        do {
            System.out.println("El coche es automatico? ");
            char r = lector.nextLine().toLowerCase().charAt(0);
            if (r == 's') {
                automatico = true;
                valido = true;
            } else if (r != 'n') {
                valido = false;
                System.out.println("Por favor, dir si o no");
            }else{
                valido = true;
            }
        }while (!valido);
        return  automatico;
    }

    /**
     * Pide al usuario si quiere accelerar o frenar el coche
     * @return 1 si quiere accelerar, 2 si quiere frenar, 3 para subir marcha, 4 para bajar marcha, 0 subir macha.
     */
    public int accerelarFrenar() {
        int opcion = -1;
        boolean valido = false;
        lector = new Scanner(System.in);
        do {
            System.out.println("Que quieres hacer?");
            System.out.println("1. accelerar");
            System.out.println("2. frenar");
            System.out.println("3. Subir marcha");
            System.out.println("4. Bajar marcha");
            System.out.println("0. parar");
            opcion = Integer.parseInt(lector.nextLine());
            if (opcion < 0 || opcion > 4) {
                System.out.println("Tienes que insertar un valor entre 0 y 4 ");
            } else {
                valido = true;
            }
        }while(!valido);
        return opcion;
    }

    /**
     * Crea el coche
     */
    public int pedirMatricula(){
        lector = new Scanner(System.in);
        boolean valido = false;
        boolean automatico = false;
        int matricula = 0;


        do {
            System.out.println("Introduce la matricula: ");
            try {
                matricula = Integer.parseInt(lector.nextLine());
                valido = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Tienes que introducir un numero");
            }
        }while(!valido);
        return  matricula;
    }


}
