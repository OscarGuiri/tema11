package com.oscarmorton.ejer6;

import java.time.LocalDate;

public class Alquiler {
    private Socio socio;
    private Multimedia multimediaAlquilado;
    private LocalDate fechaAlquilado;

    public Alquiler(Socio socio, Multimedia multimediaAlquilado, LocalDate fechaAlquilado) {
        this.socio = socio;
        this.multimediaAlquilado = multimediaAlquilado;
        this.fechaAlquilado = fechaAlquilado;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "socio=" + socio +
                ", multimediaAlquilado=" + multimediaAlquilado +
                ", fechaAlquilado=" + fechaAlquilado +
                '}';
    }
}
