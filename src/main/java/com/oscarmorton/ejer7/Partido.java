package com.oscarmorton.ejer7;

import java.util.GregorianCalendar;

public class Partido {
    private enum AFLUENCIA{BAJA_AFLUENCIA, MEDIA_AFLUENCIA, ALTA_AFLUENCIA}

    private  AFLUENCIA afluencia;
    private GregorianCalendar fechaPartido;
    private String equipoLocal;
    private  String getEquipoVisitante;
    private Zona zona;

    public Partido(AFLUENCIA afluencia, GregorianCalendar fechaPartido, String equipoLocal, String getEquipoVisitante, Zona zona) {
        this.afluencia = afluencia;
        this.fechaPartido = fechaPartido;
        this.equipoLocal = equipoLocal;
        this.getEquipoVisitante = getEquipoVisitante;
        this.zona = zona;
    }
}
