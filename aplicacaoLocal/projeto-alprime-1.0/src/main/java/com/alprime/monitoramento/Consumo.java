package com.alprime.monitoramento;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Disk;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.util.List;
import java.util.Map;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;
import oshi.hardware.Sensors;
import oshi.software.os.FileSystem;

public class Consumo {

    private int cpuSize;
    private Double cpuUso, consumoRAM, consumoMemoria, consumoDisco, tamanhoDisco, tempCPU;
    private static List<Cpu> cpus = JSensors.get.components().cpus;

    private InformacoesComputador comp = new InformacoesComputador();
    private static final SystemInfo INFO_SISTEMA = new SystemInfo();
    private static final HardwareAbstractionLayer INFO_HARDWARE = INFO_SISTEMA.getHardware();
    private static final CentralProcessor PROCESSOR = INFO_HARDWARE.getProcessor();
    private static final OperatingSystem INFO_SO = INFO_SISTEMA.getOperatingSystem();

    public Consumo() {

        cpuUso = Consumo.pegarCpu();
        tempCPU = Consumo.pegarTemperaturaJsensor();
        consumoRAM = Consumo.monitorarRam();
        consumoMemoria = Consumo.monitorarMemoria();
        consumoDisco = Consumo.monitorarConsumoDisco();
        tamanhoDisco = Consumo.pegarTamanhoDisco();

    }

    public Double getConsumoRAM() {
        return consumoRAM;
    }

    public Double getConsumoDisco() {
        return consumoDisco;
    }

    public Double getUsoCpu() {
        return cpuUso;
    }

    public Double getConsumoMemoria() {
        return consumoMemoria;
    }

    public Double getTemperaturaCPU() {
        return tempCPU;
    }

    public void setConsumoMemoria(Double consumoMemoria) {
        this.consumoMemoria = consumoMemoria;
    }

    public static Double pegarTemperaturaOshi() {
        return INFO_HARDWARE.getSensors().getCpuTemperature();
    }

    public static Double pegarTemperaturaJsensor() {
        if (!JSensors.get.components().cpus.isEmpty() && JSensors.get.components().cpus.get(0).sensors.temperatures != null) {
            Cpu cpu = JSensors.get.components().cpus.get(0);
                List<Temperature> temperaturas = cpu.sensors.temperatures;
                System.out.println(cpu.sensors.temperatures.get(temperaturas.size() - 1).name);
                return cpu.sensors.temperatures.get(temperaturas.size() - 1).value;
        }
        return 0.0;
    }

    public static Double pegarCpu() {
        long[] prevTicks = PROCESSOR.getSystemCpuLoadTicks();
        long[][] prevProcTicks = PROCESSOR.getProcessorCpuLoadTicks();
        // Wait a second...
        Util.sleep(1000);
        long[] ticks = PROCESSOR.getSystemCpuLoadTicks();
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
        double usoCpu = INFO_SISTEMA.getHardware().getProcessor().getSystemCpuLoadBetweenTicks(prevTicks);
        return usoCpu * 100;
    }

    public static Double monitorarMemoria() {
        List<OSFileStore> memoria = INFO_SO.getFileSystem().getFileStores();
        Double memoriaLivre = 0.0;
        Double memoriaTotal = 0.0;
        for (OSFileStore reparticoes : memoria) {
            memoriaLivre += reparticoes.getFreeSpace();
            memoriaTotal += reparticoes.getTotalSpace();
            System.out.println(memoriaLivre * 100 / memoriaTotal);

        }
        return (memoriaTotal - memoriaLivre) * 100 / memoriaTotal;
    }

    public static Double pegarTamanhoDisco() {
        double totalMemoria = 0.0;
        List<OSFileStore> memoria = INFO_SO.getFileSystem().getFileStores();
        for (OSFileStore reparticoes : memoria) {
            Double memoriaGB = Double.valueOf(reparticoes.getTotalSpace()) / 1000000000;
            totalMemoria += memoriaGB;
        }

        return totalMemoria;
    }

    public static Double monitorarRam() {
        Double ramTotal = Converssao.bytesParaBits(INFO_HARDWARE.getMemory().getTotal());
        Double ramDisponivel = Converssao.bytesParaBits(INFO_HARDWARE.getMemory().getAvailable());
        Double ramRestante = ramTotal - ramDisponivel;
        System.out.println("ramRestante:" + ramRestante);

        return ramRestante * 100 / ramTotal;
    }

    public static Double monitorarConsumoDisco() {
//        SystemInfo sy = new SystemInfo();
//        OperatingSystem os = sy.getOperatingSystem();
//        FileSystem fileSystem = os.getFileSystem();
//        List<OSFileStore> osfs = fileSystem.getFileStores();
//
//        Double porcentagem = 0.0;
//        
//        Double usado = (double)osfs.get(0).getTotalSpace() 
//                       - (double)osfs.get(0).getFreeSpace();
//
//        Double total = (double)osfs.get(0).getTotalSpace();
//
//        porcentagem = (double)((usado / total)*100);
//        
//        return porcentagem;
        List<Disk> disco = JSensors.get.components().disks;
        int tamanhoDisco = disco.get(0).sensors.loads.size();
        return cpus.get(0).sensors.loads.get(tamanhoDisco - 1).value;
    }

    @Override
    public String toString() {
        return "Consumo{" + "cpuSize=" + cpuSize + ", cpuUso=" + cpuUso + ", consumoRAM=" + consumoRAM + ", consumoMemoria=" + consumoMemoria + ", consumoDisco=" + consumoDisco + ", tamanhoDisco=" + tamanhoDisco + ", comp=" + comp + '}';
    }

}
