package org.example.function;

public class Transporte {
    private String tipo;
    private double tiempo;

    public Transporte(String tipo, double tiempo) {
        this.tipo = tipo;
        this.tiempo = tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
    
}
