package com.alprime.GUI;

import com.alprime.monitoramento.InformacoesComputador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import oshi.software.os.OSProcess;

public class Processos {

    private InformacoesComputador comp = new InformacoesComputador();
    private List<OSProcess> listaProcessos;

    public Processos() {
        listaProcessos = comp.getInfoSO().getProcesses();
    }

    public List<OSProcess> getListaProcessos() {
        return listaProcessos;
    }

    public static void matar(int pid) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String comando = String.format("taskkill /PID %d", pid);
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            rt.exec(comando);
        }
    }
}
