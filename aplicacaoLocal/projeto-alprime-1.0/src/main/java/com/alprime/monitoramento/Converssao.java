package com.alprime.monitoramento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oshi.util.FormatUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gabriel Vieira
 */
public class Converssao {

    public static Double bytesParaBits(long bytes) {
        Integer multiplicador = 1;
        String formatado = "";
        String restante = FormatUtil.formatBytes(bytes);
        String[] somenteNumero = restante.split(" ");
        String[] semVirgula = somenteNumero[0].split("");
        String[] tamanho = somenteNumero[1].split("");
        if (tamanho[0].equals("G")) {
            multiplicador = 1000000000;
        } else if (tamanho[0].equals("M")) {
            multiplicador = 1000000;
        } else if (tamanho[0].equals("K")) {
            multiplicador = 1000;
        }
        if (semVirgula.length == 1) {
            formatado = String.format("%s.0", semVirgula[0]);
        } else {
            if (semVirgula[0].indexOf(".") >= 0) {
                formatado = String.format("%s.%s", semVirgula[0], semVirgula[2]);
            } else if (semVirgula[1].equals(",")) {
                formatado = String.format("%s%s", semVirgula[0], semVirgula[2]);
            }else {
                formatado = String.format("%s%s", semVirgula[0], semVirgula[1]);
            }
        }
        return Double.valueOf(formatado) * multiplicador;
    }

    public static String dataHoraFormatoSQL(String dataHora) {

        String data = dataHora.split("T")[0];
        String hora = dataHora.split("T")[1];
        String horas[] = hora.split(":");
        String segundos[] = horas[2].split("");
        String horaFormatada = String.format("%s:%s:%s%s", horas[0], horas[1], segundos[0], segundos[1]);

        return String.format("%s %s", data, horaFormatada);
    }

    public static List<String> dataHoraFormatoBrasileiro(String dataHora) {

        List<String> dataHoraFormatada = new ArrayList();
        String data = dataHora.split("T")[0];
        String hora = dataHora.split("T")[1];

        String datas[] = data.split("-");
        String dataFormatada = String.format("%s/%s/%s", datas[2], datas[1], datas[0]);
        dataHoraFormatada.add(dataFormatada);
        String horas[] = hora.split(":");
        String horaFormatada = String.format("%s:%s", horas[0], horas[1]);
        dataHoraFormatada.add(horaFormatada);

        return dataHoraFormatada;
    }

    public static String dataHoraLogTitulo(LocalDateTime data) {
        String dataString = data.toString();
        String arrayDataHora[] = dataString.split("T");
        String arrayData[] = arrayDataHora[0].split("-");
        return String.format("%s%s%s", arrayData[0], arrayData[1], arrayData[2]);
    }

    public static String dataHoraLogMensagem(LocalDateTime data) {
        String dataString = data.toString();
        String arrayDataHora[] = dataString.split("T");
        String arrayData[] = arrayDataHora[0].split("-");
        String arrayHora[] = arrayDataHora[1].split(":");
        String segundos[] = arrayHora[2].split("");
        return String.format("%s/%s/%s-%s:%s:%s%s", arrayData[2], arrayData[1], arrayData[0], arrayHora[0], arrayHora[1], segundos[0], segundos[1]);
    }

    public static String dataHoraSqlServer(String dataHora) {
        String dataHoraSplit[] = dataHora.split("[.]");
        return dataHoraSplit[0];
    }
}
