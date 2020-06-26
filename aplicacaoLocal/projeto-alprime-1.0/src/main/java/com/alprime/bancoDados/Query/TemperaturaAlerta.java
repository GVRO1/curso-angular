/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.Query;

import com.alprime.bancoDados.tabelas.Maquina;
import com.alprime.bancoDados.tabelas.Registro;


public class TemperaturaAlerta {
    private Double tempMedia;
    private Double tempAtencao;
    private Double tempPerigo;

    public Double getTempMedia() {
        return tempMedia;
    }

    public void setTempMedia(Double tempMedia) {
        this.tempMedia = tempMedia;
    }

    public Double getTempAtencao() {
        return tempAtencao;
    }

    public void setTempAtencao(Double tempAtencao) {
        this.tempAtencao = tempAtencao;
    }

    public Double getTempPerigo() {
        return tempPerigo;
    }

    public void setTempPerigo(Double tempPerigo) {
        this.tempPerigo = tempPerigo;
    }

    @Override
    public String toString() {
        return "TemperaturaAlerta{" + "tempMedia=" + tempMedia + ", tempAtencao=" + tempAtencao + ", tempPerigo=" + tempPerigo + '}';
    }

    public String mensagemAtencao(Maquina maquina, Registro registro){
        return String.format("O Totem %d está com a temperatura acima do normal."
                           + "\n Temperatura Atual: %.2f"
                           + "\n Temperatura Média: %.2f"
                           + "\n Envie um técnico para uma avaliação preventiva", maquina.getIdMaquina(), registro.getTemperaturaCpu(), this.tempMedia);
    }
    
    public String mensagemPerigo(Maquina maquina, Registro registro){
        return String.format("O Totem %d está com a temperatura muito acima do normal."
                           + "\n Temperatura Atual: %.2f"
                           + "\n Temperatura Média: %.2f"
                           + "\n Envie um técnico para uma avaliação urgente", maquina.getIdMaquina(), registro.getTemperaturaCpu(), this.tempMedia);
    }
    
}
