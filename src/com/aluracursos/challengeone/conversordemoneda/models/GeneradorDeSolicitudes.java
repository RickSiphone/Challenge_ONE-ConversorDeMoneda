package com.aluracursos.challengeone.conversordemoneda.models;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GeneradorDeSolicitudes {
    private HttpClient cliente;
    private Gson gson;

    public GeneradorDeSolicitudes() {
        this.cliente = HttpClient.newHttpClient();
        this.gson = new GsonBuilder().create();
    }

    public ArrayList<String> obtenerMonedasDisponibles() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/8e147bafd016b2c54a9f58c1/codes"))
                    .build();
            HttpResponse<String> response = this.cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonArray diccionarioDeMonedas = jsonObject.getAsJsonArray("supported_codes");
            ArrayList<String> listaDeMonedas = new ArrayList<>();
            for(JsonElement moneda : diccionarioDeMonedas) {
                listaDeMonedas.add(moneda.getAsJsonArray().get(0).getAsString());
            }
            return listaDeMonedas;
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: Se han presentado problemas con la API");
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public double obtenerEquivalenteDeUnaMoneda(String origen, String destino) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/8e147bafd016b2c54a9f58c1/pair/" + origen + "/" + destino))
                    .build();
            HttpResponse<String> response = this.cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            return Double.parseDouble(jsonObject.get("conversion_rate").toString());
        } catch (IOException | InterruptedException e) {
            return -1;
        }
    }
}
