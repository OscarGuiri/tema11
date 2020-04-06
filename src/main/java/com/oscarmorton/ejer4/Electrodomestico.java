package com.oscarmorton.ejer4;

public class Electrodomestico {
    protected enum Color {BLANCO, NEGRO, ROJO, AZUL, GRIS}

    protected enum Consumo {A, B, C, D, E, F}

    protected double precioBase;
    protected Color color;
    protected Consumo consumo;
    protected double peso;

    //Constructor por defecto
    public Electrodomestico() {
        this.precioBase = 100;
        this.color = Color.BLANCO;
        this.consumo = Consumo.F;
        this.peso = 5;
    }

    // Contructor para el precio y el peso
    public Electrodomestico(double precioBase, double peso) {
        this.precioBase = precioBase;
        this.color = Color.BLANCO;
        this.consumo = Consumo.F;
        this.peso = peso;

    }

    //Contructor para todo
    public Electrodomestico(double precioBase, Color color, Consumo consumo, double peso) {
        this.precioBase = precioBase;
        this.color = color;
        this.consumo = consumo;
        this.peso = peso;
    }

    /**
     * Comprueba si la letra es correta y los define en el enum Consumo. Si no es correcta, se pone por defecto el consumo F
     *
     * @param letra
     * @return
     */
    public Consumo comprobarConsumoEnergetico(char letra) {
        boolean correcta = false;
        Consumo consumo;
        char letraUpperCase;
        letraUpperCase = Character.toUpperCase(letra); // cambio la letra al uppercase para prevenir fallos
        //
        switch (letra) {
            case 'A':
                correcta = true;
                consumo = Consumo.A;
                break;
            case 'B':
                consumo = Consumo.B;
                correcta = true;
                break;
            case 'C':
                consumo = Consumo.C;
                correcta = true;
                break;
            case 'D':
                consumo = Consumo.D;
                correcta = true;
                break;
            case 'E':
                consumo = Consumo.E;
                correcta = true;
                break;
            case 'F':
                consumo = Consumo.F;
                correcta = true;
                break;
            default:
                consumo = Consumo.F;
                break;

        }
        return consumo;

    }

    /**
     * Comprueba si el color es correta y los define en el enum color. Si no es correcta, se pone por defecto el color BLANCO
     *
     * @param colorString
     * @return
     */
    public Color comprobarColor(String colorString) {
        boolean correcta = false;
        Color color;
        colorString = colorString.toUpperCase();

        //
        switch (colorString) {
            case "BLANCO":

                color = Color.BLANCO;
                break;
            case "NEGRO":
                color = Color.NEGRO;

                break;
            case "ROJO":
                color = Color.ROJO;

                break;
            case "AZUL":
                color = Color.AZUL;

                break;
            case "GRIS":
                color = Color.GRIS;

                break;

            default:
                color = Color.BLANCO;
                break;

        }
        return color;


    }

    /**
     * según el consumo energético, aumenta el precio, y según su tamaño, también.
     */
    public void precioFinal() {
        double precio = 0;
        //Por consumo
        switch (this.consumo) {
            case A:
                precio = 100;

                break;
            case B:
                precio = 80;

                break;
            case C:
                precio = 60;

                break;
            case D:
                precio = 50;

                break;
            case E:
                precio = 30;

                break;
            case F:

                precio = 10;
                break;

            default:

                break;

        }
        //Por tamanyo
        if (this.peso <= 19) {
            precio += 10;
        } else if (this.precioBase >= 20 && this.precioBase <= 49) {
            precio += 50;
        } else if (this.precioBase >= 50 && this.precioBase <= 79) {
            precio += 80;
        } else if (this.precioBase >= 80) {
            precio += 100;
        }
        System.out.println("Precio final: " + precio);

    }
    //GETTERS

    public double getPrecioBase () {
        return precioBase;
    }

    public Color getColor () {
        return color;
    }

    public Consumo getConsumo () {
        return consumo;
    }

    public double getPeso () {
        return peso;
    }
}
