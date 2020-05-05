package com.oscarmorton.ejer7;

import java.util.UUID;

public class AsientoNormal extends  Asientos {
    protected  int nLoteria;



    public AsientoNormal(UUID nEtrada, String zona, int nFila, int nAsiento, boolean ocupado, int nLoteria) {
        super(nEtrada, zona, nFila, nAsiento, ocupado);
        this.nLoteria = nLoteria;
        this.asientoVIP = false;

    }


}
