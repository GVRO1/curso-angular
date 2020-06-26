package com.alprime.monitoramento;

import com.alprime.monitoramento.Converssao;
import com.profesorfalken.jsensors.model.components.Cpu;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.FormatUtil;

public class InformacoesComputador 
{

    // Variáveis que os dados vão entrar
    private String usuario, hostname, fabricante, modelo, processador, ramTotal, sistemaOperacional;
    private Double disco;
    private Double gpu;
    private Double cpu;
    private List<HWDiskStore> ds;
    private List<Cpu> cpus;
    private int cpuLoad;
    private final SystemInfo infoSistema = new SystemInfo();
    private final HardwareAbstractionLayer infoHardware = infoSistema.getHardware();
    private final OperatingSystem infoSO = infoSistema.getOperatingSystem();

    // Aqui são fica o nome de todas variáveis a serem puxadas na hora de pegar os dados do PC.
    //infoSO.getProcess(0).getUser()
    public InformacoesComputador() 
    {
        usuario = infoSO.getNetworkParams().getHostName();
        hostname = infoSO.getNetworkParams().getHostName();
        fabricante = infoHardware.getComputerSystem().getManufacturer();
        modelo = infoHardware.getComputerSystem().getModel();
        processador = infoHardware.getProcessor().toString().split("\n")[0];
        ramTotal = FormatUtil.formatBytes(infoHardware.getMemory().getTotal());
        sistemaOperacional = infoSO.getFamily();
        ds = infoHardware.getDiskStores();
        List<OSFileStore> memoria = infoSO.getFileSystem().getFileStores();
        Double totalMemoria = 0.0;

        for (OSFileStore reparticoes : memoria) 
        {
            Double memoriaGB = Double.valueOf(reparticoes.getTotalSpace()) / 1000000000;
            totalMemoria += memoriaGB;
        }
        disco = totalMemoria;
    }

    // Métodos para pegar os dados
    public String getUsuario() {
        return usuario;
    }

    public String getHostname() {
        return hostname;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public String getProcessador() {
        return processador;
    }

    public String getRamTotal() {
        return ramTotal;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public Double getDisco() {
        return disco;
    }

    public Double getGpu() {
        return gpu;
    }

    public void setGpu(Double gpu) {
        this.gpu = gpu;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public List<HWDiskStore> getDs() {
        return ds;
    }

    public void setDs(List<HWDiskStore> ds) {
        this.ds = ds;
    }

    public List<Cpu> getCpus() {
        return cpus;
    }

    public void setCpus(List<Cpu> cpus) {
        this.cpus = cpus;
    }

    public int getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(int cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public OperatingSystem getInfoSO() {
        return infoSO;
    }

    @Override
    public String toString() {
        return "InformacoesComputador{" + "usuario=" + usuario + ", hostname=" + hostname + ", fabricante=" + fabricante + ", modelo=" + modelo + ", processador=" + processador + ", ramTotal=" + ramTotal + ", sistemaOperacional=" + sistemaOperacional + ", disco=" + disco + ", gpu=" + gpu + ", cpu=" + cpu + ", ds=" + ds + ", cpus=" + cpus + ", cpuLoad=" + cpuLoad + ", infoSistema=" + infoSistema + ", infoHardware=" + infoHardware + ", infoSO=" + infoSO + '}';
    }
    
}
