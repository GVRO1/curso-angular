/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.log;

import com.alprime.monitoramento.Converssao;
import java.time.LocalDateTime;

/**
 *
 * @author Gabriel Vieira
 */
public class MensagemLog {
    private LocalDateTime dataHora;
    private int idMaquina;
    private String mensagem;
    private String tipo;

    public MensagemLog(int idMaquina, String mensagem, String tipo) {
        this.dataHora = LocalDateTime.now();
        this.idMaquina = idMaquina;
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return Converssao.dataHoraLogMensagem(dataHora) + ", 'Maquina': " + idMaquina + " , 'Descrição': " + mensagem + ", 'Importância': " + tipo + ';';
    }
 
    
    
}
