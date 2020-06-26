/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados.tabelas;

import com.alprime.monitoramento.Consumo;
import com.alprime.monitoramento.Converssao;
import java.time.LocalDateTime;


/**
 *
 * @author Gabriel Vieira
 */
public class Registro 
{
    private Integer idRegistro;
    private String dataHora;
    private Double porcProcessador;
    private Double porcDisco;
    private Double porcMemoria;
    private static Double tempCpu;
    private Double porcRam;
    private Maquina maquina;

    public Registro()
    {
        
    }

    public Registro(Maquina maquina) 
    {
        Consumo consumo = new Consumo();
        this.idRegistro = null;
        this.dataHora = Converssao.dataHoraFormatoSQL(String.valueOf(LocalDateTime.now()));
        this.porcProcessador = consumo.getUsoCpu();
        this.porcDisco = consumo.getConsumoDisco();
        this.porcMemoria = consumo.getConsumoMemoria();
        this.tempCpu = consumo.getTemperaturaCPU();
        this.porcRam = consumo.getConsumoRAM();
        this.maquina = maquina;
    }
    
    public Registro(Integer idRegistro, String dataHora, Double porcProcessador, Double porcDisco, Double porcMemoria, Double tempCpu, Double porcRam, Maquina maquina) {
        this.idRegistro = idRegistro;
        this.dataHora = dataHora;
        this.porcProcessador = porcProcessador;
        this.porcDisco = porcDisco;
        this.porcMemoria = porcMemoria;
        this.tempCpu = tempCpu;
        this.porcRam = porcRam;
        this.maquina = maquina;
    }
    
    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Double getPorcProcessador() {
        return porcProcessador;
    }

    public void setPorcProcessador(Double porcProcessador) {
        this.porcProcessador = porcProcessador;
    }

    public Double getPorcDisco() {
        return porcDisco;
    }

    public void setPorcDisco(Double porcDisco) {
        this.porcDisco = porcDisco;
    }

    public Double getPorcMemoria() {
        return porcMemoria;
    }

    public void setPorcMemoria(Double porcMemoria) {
        this.porcMemoria = porcMemoria;
    }

    public Double getTemperaturaCpu() 
    {
        return tempCpu;
    }

    public void setTempCpu(Double tempCpu) {
        this.tempCpu = tempCpu;
    }

    public Double getPorcRam() {
        return porcRam;
    }

    public void setPorcRam(Double porcRam) {
        this.porcRam = porcRam;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    @Override
    public String toString() 
    {
        return "Registro{" + "idRegistro=" + idRegistro + ", dataHora=" + dataHora + ", porcProcessador=" + porcProcessador + ", porcDisco=" + porcDisco + ", porcMemoria=" + porcMemoria + ", tempCpu=" + tempCpu + ", porcRam=" + porcRam + ", maquina=" + maquina + '}';
    }

  
    
    
}
