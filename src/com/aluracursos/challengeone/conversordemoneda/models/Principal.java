package com.aluracursos.challengeone.conversordemoneda.models;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        GeneradorDeSolicitudes generadorDeSolicitudes = new GeneradorDeSolicitudes();
        ArrayList<String> monedasDisponibles = generadorDeSolicitudes.obtenerMonedasDisponibles();
        Scanner input = new Scanner(System.in);
        ConversorDeDinero conversorDeDinero = new ConversorDeDinero();
        while(true) {
            System.out.println("******** Conversor de monedas ********");
            System.out.println("""
                Estas son algunas de las monedas más conocidas:
                ARS - Peso argentino
                BOB - Boliviano boliviano
                BRL - Real brasileño
                CLP - Peso chileno
                COP - Peso colombiano
                USD - Dólar estadounidense
                
                NOTA: El programa cuenta con""" + " "+ monedasDisponibles.size() + " divisas para poder realizar las conversiones, " +
                    "Sientete libre de buscar cualquier divisa que se te ocurra :).");
            System.out.println("En caso de querer ver todas las monedas disponibles, escribe \"mostrar divisas\" para que aparezca la lista completa");
            System.out.print("\nEscribe la divisa que quieres utilizar (Ejemplo:  MXN o mxn) o para finalizar el programa escribe la palabra salir: ");
            var origen = input.nextLine().toUpperCase();
            if (origen.compareTo("MOSTRAR DIVISAS") == 0) {
                monedasDisponibles.forEach(System.out::println);
                System.out.print("Escribe la divisa que quieres utilizar (Ejemplo:  MXN o mxn) o para finalizar el programa escribe la palabra salir: ");
                origen = input.nextLine().toUpperCase();
            } else if (origen.compareTo("SALIR") == 0) {
                break;
            }
            if (monedasDisponibles.contains(origen)) {
                System.out.print("Escribe la cantidad que deseas convertir: ");
                var dineroOrigen = Integer.parseInt(input.nextLine());
                System.out.print("Escribe la divisa a la que deseas a convertir el monto indicado: ");
                var destino = input.nextLine().toUpperCase();
                if (monedasDisponibles.contains(destino)) {
                    var montoFinal = conversorDeDinero.convertirDinero(origen,destino,dineroOrigen);
                    if (montoFinal == -1) {
                        break;
                    }
                    System.out.println("La cantidad de: " + dineroOrigen + "[" + origen + "] equivale a " + montoFinal + "[" + destino + "]");
                }
            } else {
                System.out.println("Error: La divisa ingresada no existe o está mal escrita");
            }
        }
        System.out.println("Finalizando el programa");
    }
}
