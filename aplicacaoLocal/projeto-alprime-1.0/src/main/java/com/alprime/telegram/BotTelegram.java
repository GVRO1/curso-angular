/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.telegram;

import com.alprime.bancoDados.Query.QueryBD;
import com.alprime.bancoDados.tabelas.Maquina;
import com.alprime.bancoDados.tabelas.Usuario;
import com.alprime.log.Log;
import com.alprime.log.MensagemLog;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author matheus.costa
 */
public class BotTelegram 
{

    private String CHAT_ID;
    private static final String TOKEN = "1218586965:AAGOQlenhj8avnFRx6p43n7HnfwDcv-R-6g";
    private Maquina maquina;

    public BotTelegram(Maquina maquina, String CHAT_ID) {
        this.CHAT_ID = CHAT_ID;
        this.maquina = maquina;
    }

    public BotTelegram(Maquina maquina) {
        this.maquina = maquina;
        this.CHAT_ID = this.getNovoIdChat();
    }

    public String enviarMensagem(String mensagemTelegram) {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", CHAT_ID)
                .queryParam("text", mensagemTelegram);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + TOKEN))
                .timeout(Duration.ofSeconds(5))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException ex) {
            Log log = new Log(maquina.getIdMaquina(), 1);
            String mensagem = String.format("Erro ao enviar mensagem");
            MensagemLog mensagemLog = new MensagemLog(maquina.getIdMaquina(), mensagem, "CRITICO");
            log.escrever(mensagemLog);
            return null;
        } catch (InterruptedException ex) {
            Log log = new Log(maquina.getIdMaquina(), 1);
            String mensagem = String.format("Ocorreu uma interrupção ao enviar a mensagem");
            MensagemLog mensagemLog = new MensagemLog(maquina.getIdMaquina(), mensagem, "CRITICO");
            log.escrever(mensagemLog);
            return null;
        }
    }

    public String getNovoIdChat() {
        String uri = String.format("https://api.telegram.org/bot1218586965:AAGOQlenhj8avnFRx6p43n7HnfwDcv-R-6g/getUpdates");
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);
        Gson g = new Gson();
        ApiTelegram bot = g.fromJson(json, ApiTelegram.class);

        try {
            String chatIdJson = String.valueOf(bot.getResult().get(bot.getResult().size() - 1).getMessage().getChat().getId());
            this.setCHAT_ID(chatIdJson);  
            QueryBD.updateChatId(this.maquina.getLocalizacao().getUsuario(), this.CHAT_ID);
            return chatIdJson;
        } catch (IndexOutOfBoundsException e) {
            Log log = new Log(maquina.getIdMaquina(), 1);
            String mensagem = String.format("Não foi possivel capturar o id pois não foi feita nenhum update recentemente");
            MensagemLog mensagemLog = new MensagemLog(maquina.getIdMaquina(), mensagem, "CRITICO");
            log.escrever(mensagemLog);
            return null;
        }
    }

    public String getCHAT_ID() {
        return CHAT_ID;
    }

    public void setCHAT_ID(String CHAT_ID) {
        this.CHAT_ID = CHAT_ID;
    }

    @Override
    public String toString() {
        return "BotTelegram{" + "CHAT_ID=" + CHAT_ID + '}';
    }

}
