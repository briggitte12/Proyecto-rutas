package org.example.util;

public class Menu {

    public void mostrarMenuPrincipal() {
        String menuPrincipal = """
                +-------------------------------------------------+
                |                Menú Principal                   |
                +-------------------------------------------------+
                | 1. Mostrar ruta más corta en base al tiempo     |
                | 2. Buscar por BFS y DFS                         |
                | 3. Mostrar ruta más corta en base a la distancia|
                | 4. Salir                                        |
                """;
        System.out.print(menuPrincipal);
    }

    public void marcoInferior(){
        System.out.println("+-------------------------------------------------+");
    }

    public void mostrarMenuSecundario() {
        String menuSecundario = """
                +-------------------------------------------------+
                |                Menú Secundario                  |
                +-------------------------------------------------+
                | 1. Escribir la ruta del archivo                 |
                | 2. Atrás                                        |
                """;
        System.out.print(menuSecundario);
    }

    public void mostrarMenuTerciario() {
        String menuSecundario = """
                +-------------------------------------------------+
                |                Menú Terciario                   |
                +-------------------------------------------------+
                | 1. Buscar por BFS (amplitud)                    |
                | 2. Buscar por DFS (profundidad)                 |
                | 3. Atrás                                        |
                """;
        System.out.print(menuSecundario);
    }


}
