package com.oscarmorton.ejer5;

public class ItemBlock {
    protected String nombre;
    protected boolean ampliable;
    protected int cantidad;
    protected int maximaCantidad;

    public ItemBlock(String nombre, int maximaCantidad, boolean ampliable){ // Constructor
        this.nombre = nombre;
        this.ampliable =   ampliable;
        this.maximaCantidad = maximaCantidad;



    }
    public ItemBlock(String nombre){ //Contructor por defecto con string
        this(nombre,  0, false);

    }
    public ItemBlock(){ //Contructor por defecto
        this("Item",  0, false);

    }
    /**
     * Anyaed madera al Item
     * @param cantidad La cantidad deseada
     * @return TRUE si a sido posible, FALSE si la cantidad está al maxima
     */
    public boolean anyadir(int cantidad ){
        boolean anyadido = false;
        int aux;
        //Compruebo con un aux si la cantidad no sobrepasa la cantidad introducida
        aux = this.cantidad + cantidad;
        if(aux <= this.maximaCantidad && this.ampliable){
            this.cantidad = cantidad;
            anyadido = true;
        }else{
            anyadido = false;
        }
        return anyadido;

    }

        //GETTERS AND SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAmpliable(boolean ampliable) {
        this.ampliable = ampliable;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAmpliable() {
        return ampliable;
    }
}
