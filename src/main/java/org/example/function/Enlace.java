package org.example.function;

import java.util.ArrayList;
import java.util.List;

public class Enlace {
    private Nodo destino;
    private double distancia;
    private List<Transporte> transportes;
    private List<Transporte> transportesUsados;
    private double ponderado;

    public Enlace(Nodo destino, double distancia, List<Transporte> transportes) {
        this.destino = destino;
        this.distancia = distancia;
        this.transportes = transportes;
        this.transportesUsados = new ArrayList<>();
        this.ponderado = calcularPonderado();
    }

    public Nodo getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public List<Transporte> getTransportes() {
        return transportes;
    }

    public double getPonderado() {
        return ponderado;
    }

    public List<Transporte> getTransportesUsados() {
        return transportesUsados;
    }

    private double calcularPonderado() {
        double tiempoMinimo = Double.MAX_VALUE;
        Transporte transporteMinimo = null;
        for (Transporte transporte : transportes) {
            if (transporte.getTiempo() != -1 && transporte.getTiempo() < tiempoMinimo) {
                tiempoMinimo = transporte.getTiempo();
                transporteMinimo = transporte;
            }
        }

        if (transporteMinimo != null) {
            transportesUsados.add(transporteMinimo);
        }

        return tiempoMinimo == Double.MAX_VALUE ? -1 : tiempoMinimo;

    }
}
