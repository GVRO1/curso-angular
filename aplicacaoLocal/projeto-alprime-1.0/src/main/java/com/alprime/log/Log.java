/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.log;

import com.alprime.monitoramento.Converssao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Vieira
 */
public class Log {

    private LocalDateTime dataHora;
    private Integer idMaquina;
    private List<MensagemLog> mensagens;
    private String caminhoArquivo;
    private String caminhoDiretorio;
    private File arquivo;
    private int numero;

    public Log(Integer idMaquina, int numero) {
        LocalDateTime tempo = LocalDateTime.now();
        this.mensagens = new ArrayList<>();
        this.idMaquina = idMaquina;
        this.dataHora = tempo;
        this.numero = numero;
        this.caminhoDiretorio = javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "//Logs";
        String titulo = String.format("alprime_%s_%s_%d", this.idMaquina, Converssao.dataHoraLogTitulo(tempo), this.numero);
        String caminhoArquivoCompleto = String.format("%s\\%s.txt", this.caminhoDiretorio, titulo);
        this.caminhoArquivo = caminhoArquivoCompleto;
        File diretorio = new File(this.caminhoDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        File file = new File(caminhoArquivoCompleto);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(String.format("Falha ao criar o arquivo:'%s' ", titulo));
        }
        this.arquivo = file;
    }

    public Log(Integer idMaquina, String caminhoDiretorio, String nomeArquivo) {
        LocalDateTime tempo = LocalDateTime.now();
        this.mensagens = new ArrayList<>();
        this.idMaquina = idMaquina;
        this.dataHora = tempo;
        this.caminhoDiretorio = caminhoDiretorio;
        String caminhoArquivoCompleto = String.format("%s\\%s.txt", caminhoDiretorio, nomeArquivo);
        this.caminhoArquivo = caminhoArquivoCompleto;
        File diretorio = new File(caminhoDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        File file = new File(caminhoArquivoCompleto);
        this.arquivo = file;
    }

    public Log(Log log) {
        this.mensagens = new ArrayList<>();
        this.idMaquina = log.getIdMaquina();
        this.dataHora = log.getDataHora();
        this.numero = log.getNumero() + 1;
        String titulo = String.format("alprime_%s_%s_%d", this.idMaquina, Converssao.dataHoraLogTitulo(LocalDateTime.now()), this.numero);
        String caminhoArquivoCompleto = String.format("%s\\%s.txt", log.getCaminhoDiretorio(), titulo);
        this.caminhoArquivo = caminhoArquivoCompleto;
        this.caminhoDiretorio = log.getCaminhoDiretorio();
        File diretorio = new File(log.caminhoDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        File file = new File(caminhoArquivoCompleto);
        try {
            file.createNewFile(); // aqui ele só cria o arquivo no diretório ja definido acima
        } catch (IOException e) {
            System.out.println(String.format("Falha ao criar o diretório: '%s' ", this.caminhoDiretorio));
        }
        this.arquivo = file;

    }

    public void escrever(MensagemLog mensagem) {
        FileWriter escreverArquivo;
        if (this.arquivo.length() < 200000) {
            mensagens.add(mensagem);
            try {
                try {
                    escreverArquivo = new FileWriter(this.arquivo, true);
                    PrintWriter gravarArq = new PrintWriter(escreverArquivo);
                    gravarArq.println(mensagem.toString());
                    gravarArq.close();
                } catch (FileNotFoundException e) {
                    System.out.println(String.format("Não foi possivel encontrar arquivo: '%s' ", this.getCaminhoArquivo()));
                }
            } catch (IOException ex) {
                System.out.println(String.format("Não foi possivel escrever no arquivo '%s' ", this.getCaminhoArquivo()));
            }

        } else {
            Log novoLog = new Log(this);
            this.arquivo = novoLog.getArquivo();
            this.caminhoArquivo = novoLog.caminhoArquivo;
            this.caminhoDiretorio = novoLog.caminhoDiretorio;
            this.dataHora = novoLog.getDataHora();
            this.numero = novoLog.getNumero();
            this.mensagens = novoLog.getMensagens();
            novoLog.mensagens.add(mensagem);
            try {
                escreverArquivo = new FileWriter(novoLog.getArquivo(), true);
                PrintWriter gravarArq = new PrintWriter(escreverArquivo);
                gravarArq.println(mensagem.toString());
                gravarArq.close();
            } catch (IOException ex) {
                System.out.println(String.format("Não foi possivel escrever no arquivo '%s' ", this.getCaminhoArquivo()));
            }
        }

    }

    @Override
    public String toString() {
        return "Log{" + "dataHora=" + dataHora + ", idMaquina=" + idMaquina + ", mensagens=" + mensagens + ", caminhoArquivo=" + caminhoArquivo + ", caminhoDiretorio=" + caminhoDiretorio + ", arquivo=" + arquivo + ", numero=" + numero + '}';
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public List<MensagemLog> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<MensagemLog> mensagens) {
        this.mensagens = mensagens;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCaminhoDiretorio() {
        return caminhoDiretorio;
    }

    public void setCaminhoDiretorio(String caminhoDiretorio) {
        this.caminhoDiretorio = caminhoDiretorio;
    }

}
