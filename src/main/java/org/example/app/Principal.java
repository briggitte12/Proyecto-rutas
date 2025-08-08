package org.example.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.example.function.*;
import org.example.util.Archivo;
import org.example.util.Menu;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        Menu menu = new Menu();

        do {
            menu.mostrarMenuPrincipal();
            System.out.print("| Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            menu.marcoInferior();
            System.out.println("\n");

            switch (opcion) {
                case 1 -> {
                    int subOpcion = 0;
                    do {
                        menu.mostrarMenuSecundario();
                        System.out.print("| Ingrese una opción: ");
                        subOpcion = scanner.nextInt();
                        scanner.nextLine();
                        menu.marcoInferior();
                        System.out.println("\n");

                        switch (subOpcion) {
                            case 1 -> {
                                try {
                                    System.out.print("Ingresa la ruta del archivo: ");
                                    String ruta = scanner.nextLine();

                                    Grafo grafo = new Grafo();
                                    try {
                                        grafo.cargarDatos(new Archivo(ruta));
                                    } catch (Exception e) {
                                        System.out.println("Error al cargar los datos del archivo: " + e.getMessage());
                                        return;
                                    }

                                    System.out.print("Ingresa el nodo de origen: ");
                                    String origenNombre = scanner.nextLine().toUpperCase();
                                    Nodo origen = grafo.obtenerNodo(origenNombre);
                                    if (origen == null) {
                                        System.out.println("Nodo de origen no encontrado en el grafo.");
                                        return;
                                    }

                                    System.out.print("Ingresa el nodo de destino: ");
                                    String destinoNombre = scanner.nextLine().toUpperCase();
                                    Nodo destino = grafo.obtenerNodo(destinoNombre);
                                    if (destino == null) {
                                        System.out.println("Nodo de destino no encontrado en el grafo.");
                                        return;
                                    }

                                    Dijkstra dijkstra = new Dijkstra();
                                    LinkedList<Nodo> r = dijkstra.encontrarRutaMasCorta(grafo, origen, destino, true);

                                    if (r.isEmpty() || r == null) {
                                        System.out.println("No se encontró una ruta entre los nodos especificados.");
                                    } else {
                                        double tiempoTotal = 0.0;
                                        Nodo previo = null;
                                        List<Transporte> listaTransportes = new ArrayList<>();
                                        System.out.println("La ruta más corta es: ");
                                        for (Nodo nodo : r) {
                                            if (previo != null) {
                                                Enlace enlace = grafo.obtenerEnlace(previo, nodo);
                                                if (enlace != null) {
                                                    tiempoTotal += enlace.getPonderado();
                                                    listaTransportes.addAll(enlace.getTransportesUsados());
                                                }
                                            }

                                            if (r.indexOf(nodo) == 0) {
                                                System.out.print(nodo.getNombre());
                                            } else {
                                                System.out.print(" -> " + nodo.getNombre());
                                            }

                                            previo = nodo;
                                        }
                                        System.out.println("\n");
                                        System.out.println("Transportes utilizados:");
                                        listaTransportes.forEach(data -> {
                                            switch (data.getTipo()) {
                                                case "U" -> System.out.println("Bus");
                                                case "A" -> System.out.println("Auto");
                                                case "B" -> System.out.println("Bicicleta");
                                                default -> System.out.println("Transporte desconocido");
                                            }
                                        });

                                        System.out.println("\nTiempo total de transporte: " + tiempoTotal + " min.");
                                    }
                                    subOpcion = 0;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 2 -> subOpcion = 0;
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (subOpcion != 0);
                }
                case 2 -> {
                    int subOpcion = 0;
                    do {
                        menu.mostrarMenuSecundario();
                        System.out.print("| Ingrese una opción: ");
                        subOpcion = scanner.nextInt();
                        scanner.nextLine();
                        menu.marcoInferior();
                        System.out.println("\n");

                        switch (subOpcion) {
                            case 1 -> {
                                try {
                                    System.out.print("Ingresa la ruta del archivo: ");
                                    String ruta = scanner.nextLine();

                                    Grafo grafo = new Grafo();
                                    try {
                                        grafo.cargarDatos(new Archivo(ruta));
                                    } catch (Exception e) {
                                        System.out.println("Error al cargar los datos del archivo: " + e.getMessage());
                                        return;
                                    }

                                    int opcion3 = 0;
                                    do{
                                        for (Nodo nodo : grafo.getNodos()) {
                                            nodo.setVisitado(false);
                                        }
                                        menu.mostrarMenuTerciario();
                                        System.out.print("| Ingrese una opción: ");
                                        opcion3 = scanner.nextInt();
                                        scanner.nextLine();
                                        menu.marcoInferior();
                                        System.out.println("\n");

                                        switch (opcion3) {
                                            case 1 -> {
                                                System.out.print("Ingresa el nodo de origen: ");
                                                String origenNombre = scanner.nextLine().toUpperCase();
                                                Nodo origen = grafo.obtenerNodo(origenNombre);
                                                if (origen == null) {
                                                    System.out.println("Nodo de origen no encontrado en el grafo.");
                                                    return;
                                                }

                                                System.out.print("Ingresa el nodo de destino: ");
                                                String destinoNombre = scanner.nextLine().toUpperCase();
                                                Nodo destino = grafo.obtenerNodo(destinoNombre);
                                                if (destino == null) {
                                                    System.out.println("Nodo de destino no encontrado en el grafo.");
                                                    return;
                                                }

                                                Dijkstra dijkstra = new Dijkstra();
                                                List<String> rutasBFS = dijkstra.bfs(grafo, origen, destino);

                                                System.out.println("Búsqueda por BFS (amplitud):");
                                                for (String rutas : rutasBFS) {
                                                    System.out.println(rutas);
                                                }
                                                System.out.println("\n");
                                            }
                                            case 2 -> {
                                                System.out.print("Ingresa el nodo de origen: ");
                                                String origenNombre = scanner.nextLine().toUpperCase();
                                                Nodo origen = grafo.obtenerNodo(origenNombre);
                                                if (origen == null) {
                                                    System.out.println("Nodo de origen no encontrado en el grafo.");
                                                    return;
                                                }

                                                System.out.print("Ingresa el nodo de destino: ");
                                                String destinoNombre = scanner.nextLine().toUpperCase();
                                                Nodo destino = grafo.obtenerNodo(destinoNombre);
                                                if (destino == null) {
                                                    System.out.println("Nodo de destino no encontrado en el grafo.");
                                                    return;
                                                }

                                                Dijkstra dijkstra = new Dijkstra();
                                                List<Nodo> nodosDFS = dijkstra.dfs(grafo, origen, destino);

                                                System.out.println("Búsqueda por DFS (profundidad):");
                                                for (Nodo nodo : nodosDFS) {
                                                    System.out.print(nodo.getNombre() + " ");
                                                }
                                                System.out.println("\n");
                                            }
                                            case 3 -> opcion3 = 0;
                                        }
                                    } while (opcion3 != 0);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 2 -> subOpcion = 0;
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (subOpcion != 0);
                }
                case 3 -> {
                    int subOpcion = 0;
                    do {
                        menu.mostrarMenuSecundario();
                        System.out.print("| Ingrese una opción: ");
                        subOpcion = scanner.nextInt();
                        scanner.nextLine();
                        menu.marcoInferior();
                        System.out.println("\n");

                        switch (subOpcion) {
                            case 1 -> {
                                try {
                                    System.out.print("Ingresa la ruta del archivo: ");
                                    String ruta = scanner.nextLine();

                                    Grafo grafo = new Grafo();
                                    try {
                                        grafo.cargarDatos(new Archivo(ruta));
                                    } catch (Exception e) {
                                        System.out.println("Error al cargar los datos del archivo: " + e.getMessage());
                                        return;
                                    }

                                    System.out.print("Ingresa el nodo de origen: ");
                                    String origenNombre = scanner.nextLine().toUpperCase();
                                    Nodo origen = grafo.obtenerNodo(origenNombre);
                                    if (origen == null) {
                                        System.out.println("Nodo de origen no encontrado en el grafo.");
                                        return;
                                    }

                                    System.out.print("Ingresa el nodo de destino: ");
                                    String destinoNombre = scanner.nextLine().toUpperCase();
                                    Nodo destino = grafo.obtenerNodo(destinoNombre);
                                    if (destino == null) {
                                        System.out.println("Nodo de destino no encontrado en el grafo.");
                                        return;
                                    }

                                    Dijkstra dijkstra = new Dijkstra();
                                    LinkedList<Nodo> r = dijkstra.encontrarRutaMasCorta(grafo, origen, destino, false);

                                    if (r.isEmpty()) {
                                        System.out.println("No se encontró una ruta entre los nodos especificados.");
                                    } else {
                                        double distanciaTotal = 0.0;
                                        Nodo previo = null;
                                        System.out.println("La ruta más corta es: ");
                                        for (Nodo nodo : r) {
                                            if (previo != null) {
                                                Enlace enlace = grafo.obtenerEnlace(previo, nodo);
                                                if (enlace != null) {
                                                    distanciaTotal += enlace.getDistancia();
                                                }
                                            }
                                            if (r.indexOf(nodo) == 0) {
                                                System.out.print(nodo.getNombre());
                                            } else {
                                                System.out.print(" -> " + nodo.getNombre());
                                            }
                                            previo = nodo;
                                        }
                                        System.out.println("\n");
                                        System.out.println("Distancia total: " + distanciaTotal + " metros.");
                                        System.out.println("\n");
                                        subOpcion = 0;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 2 -> subOpcion = 0;
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (subOpcion != 0);
                }
                case 4 -> {System.out.println("Saliendo..."); opcion = 0;}
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }
}