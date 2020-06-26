/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.Query;

/**
 *
 * @author Gabriel Vieira
 */
public class Administrativo {

    private Double lucro;
    private Double totalVenda;
    private Double gasto;
    private Double mediaClientes;
    private Integer clientesDia;

    public Administrativo(Double totalVenda, Double gasto, Double mediaClientes, Integer clientesDia) {
        this.lucro = this.calcularLucro();
        this.totalVenda = totalVenda;
        this.gasto = gasto;
        this.mediaClientes = mediaClientes;
        this.clientesDia = clientesDia;
    }

    public Administrativo() {
        this.gasto = 350 * 0.00019311 * 20.33 * 30.0 + 70.0;
    }

    public Double calcularLucro() {
        return this.totalVenda - this.gasto;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }

    public Double getMediaClientes() {
        return mediaClientes;
    }

    public void setMediaClientes(Double mediaClientes) {
        this.mediaClientes = mediaClientes;
    }

    public Integer getClientesDia() {
        return clientesDia;
    }

    public void setClientesDia(Integer clientesDia) {
        this.clientesDia = clientesDia;
    }

    public Double getLucro() {
        return lucro;
    }

    public void setLucro(Double lucro) {
        this.lucro = lucro;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    @Override
    public String toString() {
        return "Administrativo{" + "lucro=" + lucro + ", totalVenda=" + totalVenda + ", gasto=" + gasto + ", mediaClientes=" + mediaClientes + ", clientesDia=" + clientesDia + '}';
    }

}
