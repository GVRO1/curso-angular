/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.tabelas;

import com.alprime.monitoramento.Converssao;
import java.time.LocalDateTime;

/**
 *
 * @author Gabriel Vieira
 */
public class Aviso {
    private Integer idAviso;
    private String categoria;
    private String mensagem;
    private boolean resolvido;
    private Maquina maquina;
    private String dataHora;

    public Aviso(Integer idAviso, String categoria, String mensagem, boolean resolvido, Maquina maquina) {
        this.idAviso = idAviso;
        this.categoria = categoria;
        this.mensagem = mensagem;
        this.resolvido = resolvido;
        this.maquina = maquina;
        this.dataHora = Converssao.dataHoraSqlServer(LocalDateTime.now().toString());
    }

    public Integer getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Integer idAviso) {
        this.idAviso = idAviso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isResolvido() {
        return resolvido;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Aviso{" + "idAviso=" + idAviso + ", categoria=" + categoria + ", mensagem=" + mensagem + ", resolvido=" + resolvido + ", maquina=" + maquina + ", dataHora=" + dataHora + '}';
    }
    
    
    
}
