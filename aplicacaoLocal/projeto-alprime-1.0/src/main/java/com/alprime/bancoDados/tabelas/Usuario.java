/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.tabelas;

/**
 *
 * @author Gabriel Vieira
 */
public class Usuario {
 private Integer idUsuario;
 private String nomeUsuario;
 private String cpfUsuario;
 private String emailUsuario;
 private String senhaUsuario;
 private String telefone;
 private String chatId;
 private Localizacao localizacao;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nomeUsuario, String cpfUsuario, String emailUsuario, String senhaUsuario, String telefone, Localizacao localizacao, String chatId) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.cpfUsuario = cpfUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.telefone = telefone;
        this.localizacao = localizacao;
        this.chatId = chatId;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", cpfUsuario=" + cpfUsuario + ", emailUsuario=" + emailUsuario + ", senhaUsuario=" + senhaUsuario + ", telefone=" + telefone + ", chatId=" + chatId + ", idLocalizacao=" + localizacao.getIdLocalizacao() + '}';
    }

    

    
}
