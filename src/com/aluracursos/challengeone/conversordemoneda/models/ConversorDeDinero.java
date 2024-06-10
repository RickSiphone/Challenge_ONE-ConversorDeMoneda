package com.aluracursos.challengeone.conversordemoneda.models;

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
}
