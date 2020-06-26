/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.tabelas;

import java.util.List;

/**
 *
 * @author Gabriel Vieira
 */
public class Localizacao {
    private Integer idLocalizacao;
    private String nomeLocalizacao;
    private String tipoLinha;
    private String endereco;
    private Usuario usuario;
    private List<Maquina> maquinas;

    public Localizacao() {
    }

    public Localizacao(Integer idLocalizacao, String nomeLocalizacao, String tipoLinha, String endereco, Usuario usuario, List<Maquina> maquinas) {
        this.idLocalizacao = idLocalizacao;
        this.nomeLocalizacao = nomeLocalizacao;
        this.tipoLinha = tipoLinha;
        this.endereco = endereco;
        this.usuario = usuario;
        this.maquinas = maquinas;
    }

    public Integer getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(Integer idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public String getNomeLocalizacao() {
        return nomeLocalizacao;
    }

    public void setNomeLocalizacao(String nomeLocalizacao) {
        this.nomeLocalizacao = nomeLocalizacao;
    }

    public String getTipoLinha() {
        return tipoLinha;
    }

    public void setTipoLinha(String tipoLinha) {
        this.tipoLinha = tipoLinha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    @Override
    public String toString() {
        return "Localizacao{" + "idLocalizacao=" + idLocalizacao + ", nomeLocalizacao=" + nomeLocalizacao + ", tipoLinha=" + tipoLinha + ", endereco=" + endereco + ", usuario=" + usuario + ", maquinas=" + maquinas + '}';
    }

    
    
}
