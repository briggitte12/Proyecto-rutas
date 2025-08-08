package org.example.function;

import java.util.*;

public class Dijkstra {

    public LinkedList<Nodo> encontrarRutaMasCorta(Grafo grafo, Nodo origen, Nodo destino, boolean basadoTiempo) {
        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, Nodo> predecesores = new HashMap<>();
        PriorityQueue<Nodo> prioridad = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        Set<Nodo> enPrioridad = new HashSet<>();

        for (Nodo nodo : grafo.getNodos()) {
            distancias.put(nodo, Double.MAX_VALUE);
            predecesores.put(nodo, null);
        }
        distancias.put(origen, 0.0);
        prioridad.add(origen);
        enPrioridad.add(origen);

        while (!prioridad.isEmpty()) {
            Nodo actual = prioridad.poll();
            enPrioridad.remove(actual);

            if (actual.equals(destino)) break;

            for (Enlace enlace : grafo.getEnlacesNodo(actual)) {
                Nodo vecino = enlace.getDestino();
                double nuevaDistancia = distancias.get(actual) + (basadoTiempo ? enlace.getPonderado() : enlace.getDistancia());

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, actual);
                    if (!enPrioridad.contains(vecino)) {
                        prioridad.add(vecino);
                        enPrioridad.add(vecino);
                    }
                }
            }
        }
        LinkedList<Nodo> ruta = new LinkedList<>();
        Nodo nodoActual = destino;
        while (nodoActual != null) {
            ruta.addFirst(nodoActual);
            nodoActual = predecesores.get(nodoActual);
        }
        return ruta;
    }

    public List<String> bfs(Grafo grafo, Nodo origen, Nodo destino) {
        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, List<Nodo>> caminos = new HashMap<>();
        Queue<List<Nodo>> cola = new LinkedList<>();
        List<String> rutas = new ArrayList<>();

        for (Nodo nodo : grafo.getNodos()) {
            distancias.put(nodo, Double.MAX_VALUE);
            caminos.put(nodo, new ArrayList<>());
        }
        distancias.put(origen, 0.0);
        List<Nodo> caminoInicial = new ArrayList<>();
        caminoInicial.add(origen);
        cola.add(caminoInicial);

        while (!cola.isEmpty()) {
            List<Nodo> caminoActual = cola.poll();
            Nodo ultimoNodo = caminoActual.get(caminoActual.size() - 1);

            if (ultimoNodo.equals(destino)) {
                StringBuilder ruta = new StringBuilder();
                double distanciaTotal = 0.0;
                double tiempoMinimoEstimado = 0.0;

                for (int i = 0; i < caminoActual.size(); i++) {
                    Nodo nodoActual = caminoActual.get(i);
                    ruta.append(nodoActual.getNombre()).append(" -> ");
                    if (i < caminoActual.size() - 1) {
                        Nodo siguienteNodo = caminoActual.get(i + 1);
                        Enlace enlace = nodoActual.getEnlaces().stream()
                                .filter(e -> e.getDestino().equals(siguienteNodo))
                                .findFirst()
                                .orElse(null);
                        if (enlace != null) {
                            distanciaTotal += enlace.getDistancia();
                            tiempoMinimoEstimado += enlace.getPonderado();
                        }
                    }
                }
                ruta.setLength(ruta.length() - 4); // Elimina el último " -> "
                ruta.append(" | Distancia total: ").append(distanciaTotal).append(" m");
                ruta.append(" | Tiempo mínimo estimado: ").append(tiempoMinimoEstimado).append(" min");
                rutas.add(ruta.toString());
                continue;
            }

            for (Enlace enlace : ultimoNodo.getEnlaces()) {
                Nodo vecino = enlace.getDestino();
                if (!caminoActual.contains(vecino)) {
                    List<Nodo> nuevoCamino = new ArrayList<>(caminoActual);
                    nuevoCamino.add(vecino);
                    cola.add(nuevoCamino);
                }
            }
        }

        rutas.sort((r1, r2) -> {
            int cantidadNodos1 = r1.split(" -> ").length;
            int cantidadNodos2 = r2.split(" -> ").length;

            if (cantidadNodos1 != cantidadNodos2) {
                return Integer.compare(cantidadNodos1, cantidadNodos2);
            }

            double d1 = Double.parseDouble(r1.substring(r1.indexOf("Distancia total: ") + 17, r1.indexOf(" m", r1.indexOf("Distancia total: "))));
            double d2 = Double.parseDouble(r2.substring(r2.indexOf("Distancia total: ") + 17, r2.indexOf(" m", r2.indexOf("Distancia total: "))));
            return Double.compare(d1, d2);
        });

        return rutas;
    }

    public List<Nodo> dfs(Grafo grafo, Nodo origen, Nodo destino) {
        List<Nodo> visitados = new ArrayList<>();
        dfsRecursivo(grafo, origen, destino, visitados);
        return visitados;
    }

    private boolean dfsRecursivo(Grafo grafo, Nodo actual, Nodo destino, List<Nodo> visitados) {
        actual.setVisitado(true);
        visitados.add(actual);

        if (actual.equals(destino)) {
            return true;
        }

        for (Enlace enlace : grafo.getEnlacesNodo(actual)) {
            Nodo vecino = enlace.getDestino();
            if (!vecino.isVisitado()) {
                if (dfsRecursivo(grafo, vecino, destino, visitados)) {
                    return true;
                }
            }
        }

        visitados.remove(visitados.size() - 1);
        return false;
    }
}