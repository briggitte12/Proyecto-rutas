package org.example.function;

import java.util.LinkedList;

public class Nodo implements Comparable<Nodo> {
    private String nombre;
    private boolean visitado;
    private LinkedList<Enlace> enlaces;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.visitado = false;
        this.enlaces = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public LinkedList<Enlace> getEnlaces() {
        return enlaces;
    }

    @Override
    public int compareTo(Nodo otroNodo) {
        return this.nombre.compareTo(otroNodo.nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }
}