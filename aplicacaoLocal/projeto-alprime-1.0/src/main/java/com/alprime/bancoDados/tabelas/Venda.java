/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.tabelas;

import com.alprime.monitoramento.Converssao;


public class Venda 
{ 
    private Integer idVenda;
    private Double valor;
    private String dataHora;
    private Maquina maquina;
    
      public Venda(){
      }

    public Venda(Integer idVenda, Double valor, String dataHora, Maquina maquina) {
        this.idVenda = idVenda;
        this.valor = valor;
        this.dataHora = Converssao.dataHoraSqlServer(dataHora);
        this.maquina = maquina;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    @Override
    public String toString() {
        return "Venda{" + "idVenda=" + idVenda + ", valor=" + valor + ", dataHora=" + dataHora + ", idMaquina=" + maquina.getIdMaquina() + '}';
    }
      
    
      
}
