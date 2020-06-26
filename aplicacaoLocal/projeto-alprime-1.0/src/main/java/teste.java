import com.alprime.monitoramento.Consumo;
import com.profesorfalken.jsensors.JSensors;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oshi.SystemInfo;
import oshi.driver.linux.proc.DiskStats;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.PowerSource;
import oshi.hardware.Sensors;
import oshi.hardware.SoundCard;
import oshi.hardware.UsbDevice;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;

import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;

import oshi.software.os.OSProcess;
import oshi.software.os.OSService;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

/**
 *
 * @author brain
 */
public class teste  {
    
//           static List<String> oshi = new ArrayList<>();
//       static List<String> teste = new ArrayList<>();
//    
//           @SuppressWarnings("empty-statement")
//    public static void main(String[] args) {
//        
//        SystemInfo si = new SystemInfo();
//        HardwareAbstractionLayer hal = si.getHardware();
//        OperatingSystem os = si.getOperatingSystem();
//        printFileSystem(os.getFileSystem());
//       
//
//           
//        Double temperaturaCpu = hal.getSensors().getCpuTemperature();
//        System.out.println("\nTemperature");
//        System.out.println(temperaturaCpu.toString());
//        
//        
//        teste.add(hal.getMemory().toString());;
//        System.out.println(teste);
//        printOperatingSystem(os);
//        printComputerSystem(hal.getComputerSystem());
//        printProcessor(hal.getProcessor());
//        printMemory(hal.getMemory());
//        printCpu(hal.getProcessor());
//        printServices(os);
//        printSensors(hal.getSensors());
//        printPowerSources(hal.getPowerSources());
//        printDisks(hal.getDiskStores());
//   
//
//        System.out.println(hal.getNetworkIFs());
//        printNetworkInterfaces(hal.getNetworkIFs());
//        printNetworkParameters(os.getNetworkParams());
//        printDisplays(hal.getDisplays());
//        printUsbDevices(hal.getUsbDevices(true));
//        printSoundCards(hal.getSoundCards());
//        printGraphicsCards(hal.getGraphicsCards());;;
//        
//          printFileSystem(os.getFileSystem());
//          System.out.println(oshi);
//  
//    }
//    
//    
//    
//    
//    
//    
//    private static void printFileSystem(FileSystem fileSystem) {
//        oshi.add("\n\nSISTEMA DE ARQUIVOS:");
//
//        oshi.add(String.format("\n Descritor de Arquivo: %d/%d", fileSystem.getOpenFileDescriptors(),
//            fileSystem.getMaxFileDescriptors()));
//
//        List<OSFileStore> fsArray = fileSystem.getFileStores();
//        for (OSFileStore fs : fsArray) {
//            long disponivel = fs.getUsableSpace();
//            long total = fs.getTotalSpace();
//            oshi.add(String.format(
//                    "\n-%s -/- %s"
//                            + "\n  |--- Sistema de Arquivo: [%s] "
//                            + "\n  |--- Armazenamento Disponível: %s (%.1f%%)"
//                            + "\n  |--- Armazenamento Total: %s"
//                            + "\n  |--- Volume: " + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s"),
//                    fs.getName(), // A
//                    fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), //  B
//                    fs.getType(), // C
//                    FormatUtil.formatBytes(disponivel), //  D
//                    100d * disponivel / total, // F
//                    FormatUtil.formatBytes(total), // E
//                    fs.getVolume(), // J
//                    fs.getLogicalVolume() // K
//                    ) 
//            );
//        }
//    }
//    
//    
//    
//    
//    
//    
//    private static void printOperatingSystem(final OperatingSystem os) {
//        oshi.add("\nSISTEMA OPERACIONAL: " + os);
//        oshi.add("\n*Horário do Boot: " + Instant.ofEpochSecond(os.getSystemBootTime()));
//        oshi.add("\n*Tempo Ligado: " + FormatUtil.formatElapsedSecs(os.getSystemUptime()));
//        oshi.add("\n*Rodando " + (os.isElevated() ? "com" : "sem") + " permissões elevadas.");
//    }
//    
//    
//    
//    private static void printComputerSystem(final ComputerSystem computerSystem) {
//        oshi.add("\nSYSTEM: " + computerSystem.toString());
//        oshi.add("\nFIRMWARE: " + computerSystem.getFirmware().toString());
//        oshi.add("\nBASEBOARD: " + computerSystem.getBaseboard().toString());
//    }
//
//    private static void printProcessor(CentralProcessor processor) {
//        oshi.add("\n\nPROCESSADOR: " + processor.toString());
//    }
//    
//    
//
//    private static void printMemory(GlobalMemory memory) {
//        oshi.add("\n\nMEMÓRIA: " + memory.toString());
//        VirtualMemory vm = memory.getVirtualMemory();
//        List<PhysicalMemory> pmArray = memory.getPhysicalMemory();
//        if (pmArray.size() > 0) {
//            oshi.add("\n*Memória Física: ");
//            for (PhysicalMemory pm : pmArray) {
//                oshi.add(" " + pm.toString());
//            }
//        }
//        oshi.add("\n*Swap: " + vm.toString());
//    }
//
//    private static void printCpu(CentralProcessor processor) {
//        oshi.add("\nContext Switches/Interrupts: " + processor.getContextSwitches() + " / " + processor.getInterrupts());
//
//        long[] prevTicks = processor.getSystemCpuLoadTicks();
//        long[][] prevProcTicks = processor.getProcessorCpuLoadTicks();
//        oshi.add("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
//        // Wait a second...
//        Util.sleep(1000);
//        long[] ticks = processor.getSystemCpuLoadTicks();
//        oshi.add("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
//        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
//        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
//        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
//        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
//        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
//        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
//        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
//        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
//        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
//
//        oshi.add(String.format(
//                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%",
//                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
//                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu));
//        oshi.add(String.format("CPU load: %.1f%%", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100));
//        double[] loadAverage = processor.getSystemLoadAverage(3);
//        oshi.add("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
//                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
//                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
//        // per core CPU
//        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
//        double[] load = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
//        for (double avg : load) {
//            procCpu.append(String.format(" %.1f%%", avg * 100));
//        }
//        oshi.add(procCpu.toString());
//        long freq = processor.getProcessorIdentifier().getVendorFreq();
//        if (freq > 0) {
//            oshi.add("Vendor Frequency: " + FormatUtil.formatHertz(freq));
//        }
//        freq = processor.getMaxFreq();
//        if (freq > 0) {
//            oshi.add("Max Frequency: " + FormatUtil.formatHertz(freq));
//        }
//        long[] freqs = processor.getCurrentFreq();
//        if (freqs[0] > 0) {
//            StringBuilder sb = new StringBuilder("Current Frequencies: ");
//            for (int i = 0; i < freqs.length; i++) {
//                if (i > 0) {
//                    sb.append(", ");
//                }
//                sb.append(FormatUtil.formatHertz(freqs[i]));
//            }
//            oshi.add(sb.toString());
//        }
//    }
//
//    private static void printServices(OperatingSystem os) {
//        oshi.add("\n\nSERVIÇOS: ");
//        oshi.add("\n   PID  |  State  |  Name");
//        // DO 5 each of running and stopped
//        int i = 0;
//        for (OSService s : os.getServices()) {
//            if (s.getState().equals(OSService.State.RUNNING) && i++ < 5) {
//                oshi.add(String.format("\n %5d  | %7s | %s", s.getProcessID(), s.getState(), s.getName()));
//            }
//        }
//        i = 0;
//        for (OSService s : os.getServices()) {
//            if (s.getState().equals(OSService.State.STOPPED) && i++ < 5) {
//                oshi.add(String.format("\n %5d  | %7s | %s", s.getProcessID(), s.getState(), s.getName()));
//            }
//        }
//    }
//
//    private static void printSensors(Sensors sensors) {
//        oshi.add("\n\nSENSORES: " + sensors.toString());
//    }
//    
//    private String printarTemperature(Sensors sensors) {
//        return ("" + sensors.getCpuTemperature());
//    }
//
//    private static void printPowerSources(List<PowerSource> powerSources) {
//        StringBuilder sb = new StringBuilder("\n\nALIMENTAÇÃO: ");
//        if (powerSources.size() == 0) {
//            sb.append("Unknown");
//        }
//        for (PowerSource powerSource : powerSources) {
//            sb.append("\n ").append(powerSource.toString());
//        }
//        oshi.add(sb.toString());
//    }
//
//    private static void printDisks(List<HWDiskStore>diskStores) {
//        oshi.add("\n\nDISCOS:");
//        for (HWDiskStore disk : diskStores) {
//            oshi.add("\n " + disk.getPartitions());
//        }
//
//    }
//
//    private static void printNetworkInterfaces(List<NetworkIF> networkIFs) {
//      
//        for (NetworkIF net : networkIFs) {    
//            if ("wlan0".equals(net.getDisplayName())) {
//                System.out.println(net.getMacaddr());
//            }
//        }
//        
//    }
//
//    private static String printNetworkParameters(NetworkParams networkParams) {
//        return (networkParams.getIpv4DefaultGateway());
//    }
//    
//    
//    
//
//    private static void printDisplays(List<Display> displays) {
//        oshi.add("\nDisplays:");
//        int i = 0;
//        for (Display display : displays) {
//            oshi.add(" Display " + i + ":");
//            oshi.add(String.valueOf(display));
//            i++;
//        }
//    }
//
//    private static void printUsbDevices(List<UsbDevice> usbDevices) {
//        oshi.add("\nUSB Devices:");
//        for (UsbDevice usbDevice : usbDevices) {
//            oshi.add(String.valueOf(usbDevice));
//        }
//    }
//
//    private static void printSoundCards(List<SoundCard> cards) {
//        oshi.add("\nSound Cards:");
//        for (SoundCard card : cards) {
//            oshi.add(" " + String.valueOf(card));
//        }
//    }
//
//    private static void printGraphicsCards(List<GraphicsCard> cards) {
//        oshi.add("\nGraphics Cards:");
//        if (cards.size() == 0) {
//            oshi.add(" None detected.");
//        } else {
//            for (GraphicsCard card : cards) {
//                oshi.add(" " + String.valueOf(card));
//            }
//        }
//    }
//    
    
//        disco = JSensors.get.components().disks;;
//        tamanhoDisco = disco.get(0).sensors.loads.size();
//        consumoDisco = cpus.get(0).sensors.loads.get(tamanhoDisco - 1).value;
    
}
