package org.example.function;

import org.example.util.Archivo;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Grafo {
    private List<Nodo> nodos;
    private Map<String, Nodo> mapaNodos;

    public Grafo() {
        this.nodos = new LinkedList<>();
        this.mapaNodos = new HashMap<>();
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Enlace> getEnlacesNodo(Nodo nodo) {
        return nodo.getEnlaces();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
        mapaNodos.put(nodo.getNombre(), nodo);
    }

    public Nodo obtenerNodo(String nombre) {
        return mapaNodos.get(nombre);
    }

    public Enlace obtenerEnlace(Nodo previo, Nodo nodo) {
        for (Enlace enlace : previo.getEnlaces()) {
            if (enlace.getDestino().equals(nodo)) {
                return enlace;
            }
        }
        return null;
    }

    public void cargarDatos(Archivo archivo) {
        List<String[]> datos = archivo.leerDatos();

        for (String[] registro : datos) {
            try {
                String origenNombre = registro[0];
                String destinoNombre = registro[1];
                double distancia = Double.parseDouble(registro[2]);

                Nodo origen = obtenerNodo(origenNombre);
                if (origen == null) {
                    origen = new Nodo(origenNombre);
                    agregarNodo(origen);
                }

                Nodo destino = obtenerNodo(destinoNombre);
                if (destino == null) {
                    destino = new Nodo(destinoNombre);
                    agregarNodo(destino);
                }

                List<Transporte> transportes = new LinkedList<>();
                for (int i = 3; i < registro.length; i += 2) {
                    String tipoTransporte = registro[i];
                    if (!tipoTransporte.equals("-1")) {
                        double tiempo = Double.parseDouble(registro[i + 1]);
                        transportes.add(new Transporte(tipoTransporte, tiempo));
                    }
                }

                Enlace enlace = new Enlace(destino, distancia, transportes);
                origen.getEnlaces().add(enlace);

            } catch (NumberFormatException e) {
                System.out.println("Error al parsear datos: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Formato de registro incorrecto: " + e.getMessage());
            }
        }
    }

    public void imprimirDatosGrafo() {
        for (Nodo nodo : nodos) {
            System.out.println("Nodo: " + nodo.getNombre());
            for (Enlace enlace : nodo.getEnlaces()) {
                System.out.println("  Enlace a: " + enlace.getDestino().getNombre() + ", Distancia: " + enlace.getDistancia());
                for (Transporte transporte : enlace.getTransportes()) {
                    System.out.println("    Transporte: " + transporte.getTipo() + ", Tiempo: " + transporte.getTiempo());
                }
            }
        }
    }
}

