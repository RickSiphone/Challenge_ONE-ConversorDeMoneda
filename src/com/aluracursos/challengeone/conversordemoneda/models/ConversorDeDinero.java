package com.aluracursos.challengeone.conversordemoneda.models;

import java.util.ArrayList;

public class ConversorDeDinero {
    public double convertirDinero(String origen, String destino, double monto){
        GeneradorDeSolicitudes generadorDeSolicitudes  = new GeneradorDeSolicitudes();
        var valorDeIntercambio = generadorDeSolicitudes.obtenerEquivalenteDeUnaMoneda(origen,destino);
        if (valorDeIntercambio == -1) {
            System.out.println("Error: Se han presentado problemas con la API");
            throw new RuntimeException();
        }
        return  monto*valorDeIntercambio;
    }

    public void imprimirMonedasDisponibles(ArrayList<String> monedas) {
        int columnas = 15;
        for (int i = 0; i < monedas.size(); i++) {
            System.out.print(" || " + monedas.get(i) + " || ");
            if ((i + 1) % columnas == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
