package org.example.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Archivo {
    private String rutaArchivo;
    private static final Logger LOGGER = Logger.getLogger(Archivo.class.getName());

    public Archivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public List<String[]> leerDatos() {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean isFirstLine = true;
            while ((linea = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] array = linea.split("\\|");
                datos.add(array);
            }
        } catch (Exception ex) {
            LOGGER.severe(String.format("ERROR: %s", ex));
        }
        return datos;
    }
}