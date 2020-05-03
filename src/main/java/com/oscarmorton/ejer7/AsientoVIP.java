package com.oscarmorton.ejer7;

import java.util.UUID;

public class AsientoVIP extends  Asientos {
    protected int codigoTaquilla;


    public AsientoVIP(UUID nEtrada, String zona, int nFila, int nAsiento, boolean ocupado, int codigoTaquilla) {
        super(nEtrada, zona, nFila, nAsiento, ocupado);
        this.codigoTaquilla = codigoTaquilla;
    }
}
