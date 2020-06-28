
import com.alprime.bancoDados.Query.QueryBD;
import com.alprime.monitoramento.Consumo;
import com.mysql.cj.Query;
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
public class teste {
     
    public static void main(String[] args) {
        SystemInfo infoSistema = new SystemInfo();
    HardwareAbstractionLayer infoHardware = infoSistema.getHardware();
     OperatingSystem infoSO = infoSistema.getOperatingSystem();
        System.out.println(infoSistema.getOperatingSystem().getFamily());
    }
}
