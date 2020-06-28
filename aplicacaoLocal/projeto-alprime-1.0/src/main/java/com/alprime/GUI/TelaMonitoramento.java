/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.GUI;

import com.alprime.bancoDados.Query.TemperaturaAlerta;
import com.alprime.bancoDados.Query.Administrativo;
import com.alprime.bancoDados.Query.QueryBD;
import com.alprime.bancoDados.tabelas.Aviso;
import com.alprime.bancoDados.tabelas.Localizacao;
import com.alprime.bancoDados.tabelas.Maquina;
import com.alprime.bancoDados.tabelas.Registro;
import com.alprime.log.Log;
import com.alprime.log.MensagemLog;
import com.alprime.monitoramento.Converssao;
import com.alprime.telegram.BotTelegram;
import com.mysql.cj.Query;
import java.awt.Color;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import oshi.SystemInfo;

/**
 *
 * @author Gabriel Vieira
 */
public class TelaMonitoramento extends javax.swing.JFrame {

    private boolean monitorando = false;
    private static boolean logado = true;
    private boolean statusMonitoramento = true;
    private Localizacao localizacao;
    private Maquina maquinaBD;
    private Maquina maquinaAtualizada;
    private boolean telaProcessos = false;

    public TelaMonitoramento(Integer idMaquina) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        maquinaBD = QueryBD.procurarIdMaquina(idMaquina);
        String enderecoImagem = String.format("/linha_%s.png", maquinaBD.getLocalizacao().getTipoLinha());
        System.out.println(enderecoImagem);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource(enderecoImagem))); // NOI18N  
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo_alprime_reduzido.png")));
        System.out.println(QueryBD.procurarIdMaquina(idMaquina));
        localizacao = maquinaBD.getLocalizacao();
        maquinaAtualizada = new Maquina(localizacao);
        QueryBD.atualizarMaquina(idMaquina, maquinaAtualizada);
        Administrativo adm = QueryBD.mediaAdministrativo(maquinaBD);
        // Aqui é onde os dados irão na tela de monitoramento
        lblTotalMemoriaRam.setText(String.valueOf(maquinaAtualizada.getRamTotal()));
        lblHD.setText(String.format("%.2f GiB", maquinaAtualizada.getCapacidadeMemoria()));
        lblSistemaOp.setText(maquinaAtualizada.getSistemaOperacional());
        lblUsuarioComputador.setText(maquinaAtualizada.getHostname());
        lblFabricante.setText(maquinaAtualizada.getFabricante());
        lblModelo.setText(maquinaAtualizada.getModelo());
        lblCPU.setText(maquinaAtualizada.getTipoProcessador());
        lblLinha.setText(localizacao.getTipoLinha());
        lblEstacao.setText(localizacao.getNomeLocalizacao());
        lblUsuario.setText(localizacao.getUsuario().getNomeUsuario());
        lblLucro.setText(String.format("R$%.2f", adm.calcularLucro()));
        lblGasto.setText(String.format("R$%.2f", adm.getGasto()));
        lblClientesDia.setText(String.format("%d clientes/dia", adm.getClientesDia()));
        lblMediaMes.setText(String.format("%.1f clientes/mês", adm.getMediaClientes()));
        // Informações de Data e Hora:
        Thread threadDataHora = new Thread(this::atualizarHora);
        threadDataHora.start();
        //Animação de captura de dados
        lblAvisoCapturar.setVisible(false);
        lblReticencias1.setVisible(false);
        lblReticencias2.setVisible(false);
        lblReticencias3.setVisible(false);
    }

    public void atualizarHora() {
        while (true) {
            List<String> dataHora = Converssao.dataHoraFormatoBrasileiro(LocalDateTime.now().toString());
            lblData1.setText(dataHora.get(0));
            lblHora1.setText(dataHora.get(1));
        }
    }

    public void reticencias() {
        while (monitorando) {
            lblAvisoCapturar.setVisible(true);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            lblReticencias1.setVisible(true);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            lblReticencias2.setVisible(true);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            lblReticencias3.setVisible(true);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            lblReticencias1.setVisible(false);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            lblReticencias2.setVisible(false);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            lblReticencias3.setVisible(false);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        lblAvisoCapturar.setVisible(false);
        lblReticencias1.setVisible(false);
        lblReticencias2.setVisible(false);
        lblReticencias3.setVisible(false);
    }

    public void atualizarDados() {
        Integer valor = Integer.valueOf(spnAtualizacao.getValue().toString());
        Integer tempo = (valor - 5) * 1000;
        int contadorAlertaTemperatura = 0;
        int contadorAlertaProcessador = 0;
        int contadorPerigoTemperatura = 0;
        int contadorPerigoProcessador = 0;
        System.out.println("Antes de atualizar dados " + monitorando);
        while (monitorando) {
            Registro registro = new Registro(maquinaBD);
            QueryBD.insertRegistro(registro);
            SystemInfo infoSistema = new SystemInfo();
            if (infoSistema.getOperatingSystem().getFamily().equals("Windows")) {
            } else {
                registro.setTempCpu(0.0);
            }
            TemperaturaAlerta temperaturaAlerta = QueryBD.mediaTemperatura(maquinaBD);

//            registro.setPorcProcessador(91.0);
//            registro.setPorcRam(91.0);
//            registro.setTempCpu(100.0);
//            registro.setTempCpu(111.0);
            if (pgbUsoCpu.getValue() > 50 && pgbUsoCpu.getValue() < 80) {
                pgbUsoCpu.setForeground(new Color(215, 217, 58));
            }
            if (pgbUsoCpu.getValue() < 50) {
                pgbUsoCpu.setForeground(new Color(82, 186, 54));
            }
            if (pgbUsoCpu.getValue() > 80) {
                pgbUsoCpu.setForeground(new Color(255, 33, 77));
            }

            if (pgbUsoDisco.getValue() > 50 && pgbUsoDisco.getValue() < 80) {
                pgbUsoDisco.setForeground(new Color(215, 217, 58));
            }
            if (pgbUsoDisco.getValue() < 50) {
                pgbUsoDisco.setForeground(new Color(82, 186, 54));
            }
            if (pgbUsoDisco.getValue() > 80) {
                pgbUsoDisco.setForeground(new Color(255, 33, 77));
            }
            if (pgbUsoRAM.getValue() > 50 && pgbUsoRAM.getValue() < 80) {
                pgbUsoRAM.setForeground(new Color(215, 217, 58));
            }
            if (pgbUsoRAM.getValue() < 50) {
                pgbUsoRAM.setForeground(new Color(82, 186, 54));
            }
            if (pgbUsoRAM.getValue() > 80) {
                pgbUsoRAM.setForeground(new Color(255, 33, 77));
            }
            if (pgbTempCPU.getValue() > 50 && pgbTempCPU.getValue() < 80) {
                pgbTempCPU.setForeground(new Color(215, 217, 58));
            }
            if (pgbTempCPU.getValue() < 50) {
                pgbTempCPU.setForeground(new Color(82, 186, 54));
            }
            if (pgbTempCPU.getValue() > 80) {
                pgbTempCPU.setForeground(new Color(255, 33, 77));
            }
            if (registro.getTemperaturaCpu() != 0.0) {
                if (registro.getTemperaturaCpu() >= temperaturaAlerta.getTempPerigo()) {
                    if (contadorPerigoTemperatura == 0) {
                        BotTelegram botTelegram = new BotTelegram(maquinaBD, maquinaBD.getLocalizacao().getUsuario().getChatId());
                        botTelegram.enviarMensagem(temperaturaAlerta.mensagemPerigo(maquinaBD, registro));
                        Log log = new Log(maquinaBD.getIdMaquina(), 1);
                        String mensagem = String.format("Alerta de temperatura sobre a maquina '%d', enviada para o chat: %s", maquinaBD.getIdMaquina(), maquinaBD.getLocalizacao().getUsuario().getChatId());
                        MensagemLog mensagemLog = new MensagemLog(maquinaBD.getIdMaquina(), mensagem, "ALERTA");
                        log.escrever(mensagemLog);
                        contadorPerigoTemperatura++;
                        Aviso aviso = new Aviso(1, "CRITICA", "Temperatura do processador muito alta", false, maquinaBD);
                        QueryBD.inserirAviso(aviso);
                    }

                } else if (registro.getTemperaturaCpu() >= temperaturaAlerta.getTempAtencao()) {
                    if (contadorAlertaTemperatura == 0) {
                        BotTelegram botTelegram = new BotTelegram(maquinaBD, maquinaBD.getLocalizacao().getUsuario().getChatId());
                        botTelegram.enviarMensagem(temperaturaAlerta.mensagemAtencao(maquinaBD, registro));
                        Log log = new Log(maquinaBD.getIdMaquina(), 1);
                        String mensagem = String.format("Aviso urgênte de temperatura sobre a maquina '%d', enviada para o chat: %s", maquinaBD.getIdMaquina(), maquinaBD.getLocalizacao().getUsuario().getChatId());
                        MensagemLog mensagemLog = new MensagemLog(maquinaBD.getIdMaquina(), mensagem, "PERIGO");
                        log.escrever(mensagemLog);
                        Aviso aviso = new Aviso(1, "ALERTA", "Temperatura do processador acima do normal", false, maquinaBD);
                        QueryBD.inserirAviso(aviso);
                        contadorAlertaTemperatura++;
                    }
                }
            }
            if (registro.getPorcRam() > 90 && registro.getPorcProcessador() > 90) {
                if (contadorPerigoProcessador == 0) {
                    BotTelegram botTelegram = new BotTelegram(maquinaBD, maquinaBD.getLocalizacao().getUsuario().getChatId());
                    botTelegram.enviarMensagem(String.format("O Totem %d está com o uso muito elevado"
                            + " \n Uso do processador: %.2f "
                            + " \n Uso da memória:     %.2f "
                            + " \n Envie um técnico para uma verificação urgente", maquinaBD.getIdMaquina(), registro.getPorcProcessador(), registro.getPorcRam()));
                    Log log = new Log(maquinaBD.getIdMaquina(), 1);
                    String mensagem = String.format("Alerta de uso sobre a maquina '%d', enviada para o chat: %s", maquinaBD.getIdMaquina(), maquinaBD.getLocalizacao().getUsuario().getChatId());
                    MensagemLog mensagemLog = new MensagemLog(maquinaBD.getIdMaquina(), mensagem, "ALERTA");
                    log.escrever(mensagemLog);
                    Aviso aviso = new Aviso(1, "CRITICA", "Uso da memoria e do processador muito elevado", false, maquinaBD);
                    QueryBD.inserirAviso(aviso);
                    contadorPerigoProcessador++;
                }
            } else if (registro.getPorcRam() > 80 && registro.getPorcProcessador() > 80) {
                if (contadorAlertaProcessador == 0) {
                    BotTelegram botTelegram = new BotTelegram(maquinaBD, maquinaBD.getLocalizacao().getUsuario().getChatId());
                    botTelegram.enviarMensagem(String.format("O Totem %d está com o uso elevado dos recursos"
                            + " \n Uso do processador: %.2f "
                            + " \n Uso da memória:     %.2f "
                            + " \n Envie um técnico para uma verificação preventiva", maquinaBD.getIdMaquina(), registro.getPorcProcessador(), registro.getPorcRam()));
                    Log log = new Log(maquinaBD.getIdMaquina(), 1);
                    String mensagem = String.format("Alerta de uso sobre a maquina '%d', enviada para o chat: %s", maquinaBD.getIdMaquina(), maquinaBD.getLocalizacao().getUsuario().getChatId());
                    MensagemLog mensagemLog = new MensagemLog(maquinaBD.getIdMaquina(), mensagem, "ALERTA");
                    log.escrever(mensagemLog);
                    Aviso aviso = new Aviso(1, "ALERTA", "Uso da memória e do processador acima do normal", false, maquinaBD);
                    QueryBD.inserirAviso(aviso);
                    contadorAlertaProcessador++;
                }
            }
            lblUsoProcessador.setText(String.valueOf(registro.getPorcProcessador()));
            if(registro.getTemperaturaCpu() != 0.0){
            pgbTempCPU.setValue(registro.getTemperaturaCpu().intValue());
            lblTempCPU.setText(String.valueOf(registro.getTemperaturaCpu().intValue()) + "ºC");
            pgbUsoCpu.setValue(registro.getPorcProcessador().intValue());
            }else{
                pgbTempCPU.setValue(0);
                lblTempCPU.setText("Temperatura não suportada");
            }
            lblUsoProcessador.setText(String.format("%.2f %%", registro.getPorcProcessador()));
            lblUsoRAM.setText(String.format("%.2f %%", registro.getPorcRam()));
            pgbUsoRAM.setValue(registro.getPorcRam().intValue());

            pgbUsoMemoria.setValue(registro.getPorcMemoria().intValue());
            lblUsoMemoria.setText(String.format("%.2f %%", registro.getPorcMemoria()));

            pgbUsoDisco.setValue(registro.getPorcDisco().intValue());
            lblUsoDisco.setText(String.format("%.2f %%", registro.getPorcDisco()));

            Administrativo adm = QueryBD.mediaAdministrativo(maquinaBD);
            lblLucro.setText(String.format("R$%.2f", adm.calcularLucro()));
            lblGasto.setText(String.format("R$%.2f", adm.getGasto()));
            lblClientesDia.setText(String.format("%d clientes/dia", adm.getClientesDia()));
            lblMediaMes.setText(String.format("%.1f clientes/mês", adm.getMediaClientes()));
            try {
                Thread.sleep(tempo);
            } catch (InterruptedException e) {
                Log log = new Log(maquinaBD.getIdMaquina(), 1);
                String mensagem = String.format("Interrupção inesperada na atualização dos dados");
                MensagemLog mensagemLog = new MensagemLog(maquinaBD.getIdMaquina(), mensagem, "CRITICA");
                log.escrever(mensagemLog);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        lblData1 = new javax.swing.JLabel();
        lblHora1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblLinha = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblEstacao = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        spnAtualizacao = new javax.swing.JSpinner();
        jLabel32 = new javax.swing.JLabel();
        btnMonitorar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        btnProcessos = new javax.swing.JButton();
        lblAvisoCapturar = new javax.swing.JLabel();
        lblReticencias1 = new javax.swing.JLabel();
        lblReticencias3 = new javax.swing.JLabel();
        lblReticencias2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        AdministrativoPalavraLabel1 = new javax.swing.JLabel();
        ClientesPalavraLabel1 = new javax.swing.JLabel();
        MediaMensalPalavraLabel1 = new javax.swing.JLabel();
        LucroMensalPalavraLabel1 = new javax.swing.JLabel();
        GastoMensalPalavraLabel1 = new javax.swing.JLabel();
        lblGasto1 = new javax.swing.JLabel();
        lblMediaMes1 = new javax.swing.JLabel();
        lblClientesDia1 = new javax.swing.JLabel();
        lblLucro1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFabricante = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblHD = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTotalMemoriaRam = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSistemaOp = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblUsuarioComputador = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCPU = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        AdministrativoPalavraLabel3 = new javax.swing.JLabel();
        ClientesPalavraLabel3 = new javax.swing.JLabel();
        MediaMensalPalavraLabel3 = new javax.swing.JLabel();
        LucroMensalPalavraLabel3 = new javax.swing.JLabel();
        GastoMensalPalavraLabel3 = new javax.swing.JLabel();
        lblGasto3 = new javax.swing.JLabel();
        lblMediaMes3 = new javax.swing.JLabel();
        lblClientesDia3 = new javax.swing.JLabel();
        lblLucro3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblTempCPU = new javax.swing.JLabel();
        lblUsoProcessador = new javax.swing.JLabel();
        lblUsoMemoria = new javax.swing.JLabel();
        lblUsoDisco = new javax.swing.JLabel();
        pgbUsoMemoria = new javax.swing.JProgressBar();
        pgbUsoCpu = new javax.swing.JProgressBar();
        pgbTempCPU = new javax.swing.JProgressBar();
        jLabel24 = new javax.swing.JLabel();
        pgbUsoDisco = new javax.swing.JProgressBar();
        lblUsoRAM = new javax.swing.JLabel();
        pgbUsoRAM = new javax.swing.JProgressBar();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        AdministrativoPalavraLabel4 = new javax.swing.JLabel();
        ClientesPalavraLabel4 = new javax.swing.JLabel();
        MediaMensalPalavraLabel4 = new javax.swing.JLabel();
        LucroMensalPalavraLabel4 = new javax.swing.JLabel();
        GastoMensalPalavraLabel4 = new javax.swing.JLabel();
        lblGasto = new javax.swing.JLabel();
        lblMediaMes = new javax.swing.JLabel();
        lblClientesDia = new javax.swing.JLabel();
        lblLucro = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        AdministrativoPalavraLabel5 = new javax.swing.JLabel();
        ClientesPalavraLabel5 = new javax.swing.JLabel();
        MediaMensalPalavraLabel5 = new javax.swing.JLabel();
        LucroMensalPalavraLabel5 = new javax.swing.JLabel();
        GastoMensalPalavraLabel5 = new javax.swing.JLabel();
        lblGasto5 = new javax.swing.JLabel();
        lblMediaMes5 = new javax.swing.JLabel();
        lblClientesDia5 = new javax.swing.JLabel();
        lblLucro5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(760, 590));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_editado.png"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(111, 44, 145));
        lblUsuario.setText("Usuario");

        btnSair.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnSair.setForeground(new java.awt.Color(111, 44, 145));
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setToolTipText("");
        btnSair.setBorder(null);
        btnSair.setBorderPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/linha_Lilas.png"))); // NOI18N

        lblData1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblData1.setForeground(new java.awt.Color(111, 44, 145));
        lblData1.setText("DD/MM/AAAA");

        lblHora1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblHora1.setForeground(new java.awt.Color(111, 44, 145));
        lblHora1.setText("HH:MM");

        jLabel18.setBackground(new java.awt.Color(111, 44, 145));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(111, 44, 145));
        jLabel18.setText("Linha:");

        lblLinha.setBackground(new java.awt.Color(111, 44, 145));
        lblLinha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblLinha.setForeground(new java.awt.Color(111, 44, 145));
        lblLinha.setText("Linha");

        jLabel19.setBackground(new java.awt.Color(111, 44, 145));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(111, 44, 145));
        jLabel19.setText("Estação:");

        lblEstacao.setBackground(new java.awt.Color(111, 44, 145));
        lblEstacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblEstacao.setForeground(new java.awt.Color(111, 44, 145));
        lblEstacao.setText("Estação");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(111, 44, 145));
        jLabel31.setText("segundos");

        spnAtualizacao.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        spnAtualizacao.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnAtualizacao.setAutoscrolls(true);
        spnAtualizacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 44, 145)));
        spnAtualizacao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(111, 44, 145));
        jLabel32.setText("Tempo de atualização:");

        btnMonitorar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_monitorar.png"))); // NOI18N
        btnMonitorar.setBorderPainted(false);
        btnMonitorar.setContentAreaFilled(false);
        btnMonitorar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMonitorar.setFocusPainted(false);
        btnMonitorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitorarActionPerformed(evt);
            }
        });

        btnParar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_parar.png"))); // NOI18N
        btnParar.setBorderPainted(false);
        btnParar.setContentAreaFilled(false);
        btnParar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParar.setFocusPainted(false);
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        btnProcessos.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        btnProcessos.setForeground(new java.awt.Color(111, 44, 145));
        btnProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista.png"))); // NOI18N
        btnProcessos.setText("Processos");
        btnProcessos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(111, 44, 145), 1, true));
        btnProcessos.setContentAreaFilled(false);
        btnProcessos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProcessos.setFocusPainted(false);
        btnProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessosActionPerformed(evt);
            }
        });

        lblAvisoCapturar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        lblAvisoCapturar.setForeground(new java.awt.Color(111, 44, 145));
        lblAvisoCapturar.setText("Capturando informações");

        lblReticencias1.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        lblReticencias1.setForeground(new java.awt.Color(111, 44, 145));
        lblReticencias1.setText(".");

        lblReticencias3.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        lblReticencias3.setForeground(new java.awt.Color(111, 44, 145));
        lblReticencias3.setText(".");

        lblReticencias2.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        lblReticencias2.setForeground(new java.awt.Color(111, 44, 145));
        lblReticencias2.setText(".");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 52, 148), 3));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 52, 148), 3));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdministrativoPalavraLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        AdministrativoPalavraLabel1.setForeground(new java.awt.Color(111, 44, 145));
        AdministrativoPalavraLabel1.setText("Administrativo");
        jPanel4.add(AdministrativoPalavraLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 140, 30));

        ClientesPalavraLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ClientesPalavraLabel1.setForeground(new java.awt.Color(111, 44, 145));
        ClientesPalavraLabel1.setText("Clientes atendidos no dia:");
        jPanel4.add(ClientesPalavraLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 20));

        MediaMensalPalavraLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MediaMensalPalavraLabel1.setForeground(new java.awt.Color(111, 44, 145));
        MediaMensalPalavraLabel1.setText("Media de clientes:");
        jPanel4.add(MediaMensalPalavraLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, 20));

        LucroMensalPalavraLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LucroMensalPalavraLabel1.setForeground(new java.awt.Color(111, 44, 145));
        LucroMensalPalavraLabel1.setText("Lucro: ");
        jPanel4.add(LucroMensalPalavraLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 50, 30));

        GastoMensalPalavraLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        GastoMensalPalavraLabel1.setForeground(new java.awt.Color(111, 44, 145));
        GastoMensalPalavraLabel1.setText("Gasto: ");
        jPanel4.add(GastoMensalPalavraLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 40, 30));

        lblGasto1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGasto1.setForeground(new java.awt.Color(111, 44, 145));
        lblGasto1.setText("Gasto");
        jPanel4.add(lblGasto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, 30));

        lblMediaMes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMediaMes1.setForeground(new java.awt.Color(111, 44, 145));
        lblMediaMes1.setText("Clientes/dia");
        jPanel4.add(lblMediaMes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, 40));

        lblClientesDia1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblClientesDia1.setForeground(new java.awt.Color(111, 44, 145));
        lblClientesDia1.setText("Clientes");
        jPanel4.add(lblClientesDia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 40));

        lblLucro1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblLucro1.setForeground(new java.awt.Color(111, 44, 145));
        lblLucro1.setText("Lucro");
        jPanel4.add(lblLucro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema.png"))); // NOI18N
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 290, 160));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(111, 44, 145));
        jLabel3.setText("Sistema");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(111, 44, 145));
        jLabel4.setText("Fabricante:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 10));

        lblFabricante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblFabricante.setForeground(new java.awt.Color(111, 44, 145));
        lblFabricante.setText("Fabricante");
        jPanel2.add(lblFabricante, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 10));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(111, 44, 145));
        jLabel5.setText("Modelo:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        lblModelo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblModelo.setForeground(new java.awt.Color(111, 44, 145));
        lblModelo.setText("Modelo");
        jPanel2.add(lblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, 20));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(111, 44, 145));
        jLabel14.setText("Tamanho do Disco");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 10));

        lblHD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblHD.setForeground(new java.awt.Color(111, 44, 145));
        lblHD.setText("Tamanho do Disco");
        jPanel2.add(lblHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, 10));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(111, 44, 145));
        jLabel12.setText("Memoria Ram Total:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 10));

        lblTotalMemoriaRam.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTotalMemoriaRam.setForeground(new java.awt.Color(111, 44, 145));
        lblTotalMemoriaRam.setText("Total de Memoria Ram");
        jPanel2.add(lblTotalMemoriaRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, 10));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(111, 44, 145));
        jLabel7.setText("Sistema Op:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 10));

        lblSistemaOp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblSistemaOp.setForeground(new java.awt.Color(111, 44, 145));
        lblSistemaOp.setText("S.O");
        jPanel2.add(lblSistemaOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, 10));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(111, 44, 145));
        jLabel6.setText("Computador:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 10));

        lblUsuarioComputador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUsuarioComputador.setForeground(new java.awt.Color(111, 44, 145));
        lblUsuarioComputador.setText("Computador");
        jPanel2.add(lblUsuarioComputador, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, 10));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(111, 44, 145));
        jLabel8.setText("CPU:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 10));

        lblCPU.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCPU.setForeground(new java.awt.Color(111, 44, 145));
        lblCPU.setText("CPU");
        jPanel2.add(lblCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 10));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 52, 148), 3));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitorando.png"))); // NOI18N
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 52, 148), 3));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdministrativoPalavraLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        AdministrativoPalavraLabel3.setForeground(new java.awt.Color(111, 44, 145));
        AdministrativoPalavraLabel3.setText("Administrativo");
        jPanel6.add(AdministrativoPalavraLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 140, 30));

        ClientesPalavraLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ClientesPalavraLabel3.setForeground(new java.awt.Color(111, 44, 145));
        ClientesPalavraLabel3.setText("Clientes atendidos no dia:");
        jPanel6.add(ClientesPalavraLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 20));

        MediaMensalPalavraLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MediaMensalPalavraLabel3.setForeground(new java.awt.Color(111, 44, 145));
        MediaMensalPalavraLabel3.setText("Media de clientes:");
        jPanel6.add(MediaMensalPalavraLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, 20));

        LucroMensalPalavraLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LucroMensalPalavraLabel3.setForeground(new java.awt.Color(111, 44, 145));
        LucroMensalPalavraLabel3.setText("Lucro: ");
        jPanel6.add(LucroMensalPalavraLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 50, 30));

        GastoMensalPalavraLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        GastoMensalPalavraLabel3.setForeground(new java.awt.Color(111, 44, 145));
        GastoMensalPalavraLabel3.setText("Gasto: ");
        jPanel6.add(GastoMensalPalavraLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 40, 30));

        lblGasto3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGasto3.setForeground(new java.awt.Color(111, 44, 145));
        lblGasto3.setText("Gasto");
        jPanel6.add(lblGasto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, 30));

        lblMediaMes3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMediaMes3.setForeground(new java.awt.Color(111, 44, 145));
        lblMediaMes3.setText("Clientes/dia");
        jPanel6.add(lblMediaMes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, 40));

        lblClientesDia3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblClientesDia3.setForeground(new java.awt.Color(111, 44, 145));
        lblClientesDia3.setText("Clientes");
        jPanel6.add(lblClientesDia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 40));

        lblLucro3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblLucro3.setForeground(new java.awt.Color(111, 44, 145));
        lblLucro3.setText("Lucro");
        jPanel6.add(lblLucro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema.png"))); // NOI18N
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 290, 160));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(111, 44, 145));
        jLabel20.setText("Temperatura da CPU");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 120, 40));

        lblTempCPU.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTempCPU.setForeground(new java.awt.Color(111, 44, 145));
        lblTempCPU.setText("0%");
        jPanel5.add(lblTempCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 70, 20));

        lblUsoProcessador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUsoProcessador.setForeground(new java.awt.Color(111, 44, 145));
        lblUsoProcessador.setText("0%");
        jPanel5.add(lblUsoProcessador, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 60, 20));

        lblUsoMemoria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUsoMemoria.setForeground(new java.awt.Color(111, 44, 145));
        lblUsoMemoria.setText("0%");
        jPanel5.add(lblUsoMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 60, 40));

        lblUsoDisco.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUsoDisco.setForeground(new java.awt.Color(111, 44, 145));
        lblUsoDisco.setText("0%");
        jPanel5.add(lblUsoDisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 80, 20));

        pgbUsoMemoria.setForeground(new java.awt.Color(82, 186, 54));
        jPanel5.add(pgbUsoMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 150, 20));

        pgbUsoCpu.setForeground(new java.awt.Color(82, 186, 54));
        jPanel5.add(pgbUsoCpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 150, 20));

        pgbTempCPU.setForeground(new java.awt.Color(82, 186, 54));
        jPanel5.add(pgbTempCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 150, 20));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(111, 44, 145));
        jLabel24.setText("Uso de RAM");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 70, 40));

        pgbUsoDisco.setForeground(new java.awt.Color(82, 186, 54));
        jPanel5.add(pgbUsoDisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 150, 20));

        lblUsoRAM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUsoRAM.setForeground(new java.awt.Color(111, 44, 145));
        lblUsoRAM.setText("0%");
        jPanel5.add(lblUsoRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 70, 20));

        pgbUsoRAM.setForeground(new java.awt.Color(82, 186, 54));
        jPanel5.add(pgbUsoRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 150, 20));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(111, 44, 145));
        jLabel21.setText("Uso Mém. de Massa");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 140, 40));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(111, 44, 145));
        jLabel22.setText("Uso do Disco Principal");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 140, 40));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(111, 44, 145));
        jLabel25.setText("Monitoramento");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(111, 44, 145));
        jLabel26.setText("Uso da CPU");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 70, 40));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 52, 148), 3));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdministrativoPalavraLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        AdministrativoPalavraLabel4.setForeground(new java.awt.Color(111, 44, 145));
        AdministrativoPalavraLabel4.setText("Administrativo");
        jPanel7.add(AdministrativoPalavraLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, 30));

        ClientesPalavraLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ClientesPalavraLabel4.setForeground(new java.awt.Color(111, 44, 145));
        ClientesPalavraLabel4.setText("Clientes atendidos no dia:");
        jPanel7.add(ClientesPalavraLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 20));

        MediaMensalPalavraLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MediaMensalPalavraLabel4.setForeground(new java.awt.Color(111, 44, 145));
        MediaMensalPalavraLabel4.setText("Media de clientes:");
        jPanel7.add(MediaMensalPalavraLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, 20));

        LucroMensalPalavraLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LucroMensalPalavraLabel4.setForeground(new java.awt.Color(82, 186, 54));
        LucroMensalPalavraLabel4.setText("Lucro: ");
        jPanel7.add(LucroMensalPalavraLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 50, 30));

        GastoMensalPalavraLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        GastoMensalPalavraLabel4.setForeground(new java.awt.Color(255, 33, 77));
        GastoMensalPalavraLabel4.setText("Gasto: ");
        jPanel7.add(GastoMensalPalavraLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 40, 30));

        lblGasto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGasto.setForeground(new java.awt.Color(255, 33, 77));
        lblGasto.setText("Gasto");
        jPanel7.add(lblGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, 30));

        lblMediaMes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMediaMes.setForeground(new java.awt.Color(111, 44, 145));
        lblMediaMes.setText("Clientes/dia");
        jPanel7.add(lblMediaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, 40));

        lblClientesDia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblClientesDia.setForeground(new java.awt.Color(111, 44, 145));
        lblClientesDia.setText("Clientes");
        jPanel7.add(lblClientesDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 40));

        lblLucro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblLucro.setForeground(new java.awt.Color(82, 186, 54));
        lblLucro.setText("Lucro");
        jPanel7.add(lblLucro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adm.png"))); // NOI18N
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 52, 148), 3));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdministrativoPalavraLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        AdministrativoPalavraLabel5.setForeground(new java.awt.Color(111, 44, 145));
        AdministrativoPalavraLabel5.setText("Administrativo");
        jPanel8.add(AdministrativoPalavraLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 140, 30));

        ClientesPalavraLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ClientesPalavraLabel5.setForeground(new java.awt.Color(111, 44, 145));
        ClientesPalavraLabel5.setText("Clientes atendidos no dia:");
        jPanel8.add(ClientesPalavraLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 20));

        MediaMensalPalavraLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        MediaMensalPalavraLabel5.setForeground(new java.awt.Color(111, 44, 145));
        MediaMensalPalavraLabel5.setText("Media de clientes:");
        jPanel8.add(MediaMensalPalavraLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, 20));

        LucroMensalPalavraLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LucroMensalPalavraLabel5.setForeground(new java.awt.Color(111, 44, 145));
        LucroMensalPalavraLabel5.setText("Lucro: ");
        jPanel8.add(LucroMensalPalavraLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 50, 30));

        GastoMensalPalavraLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        GastoMensalPalavraLabel5.setForeground(new java.awt.Color(111, 44, 145));
        GastoMensalPalavraLabel5.setText("Gasto: ");
        jPanel8.add(GastoMensalPalavraLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 40, 30));

        lblGasto5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGasto5.setForeground(new java.awt.Color(111, 44, 145));
        lblGasto5.setText("Gasto");
        jPanel8.add(lblGasto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, 30));

        lblMediaMes5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMediaMes5.setForeground(new java.awt.Color(111, 44, 145));
        lblMediaMes5.setText("Clientes/dia");
        jPanel8.add(lblMediaMes5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, 40));

        lblClientesDia5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblClientesDia5.setForeground(new java.awt.Color(111, 44, 145));
        lblClientesDia5.setText("Clientes");
        jPanel8.add(lblClientesDia5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 40));

        lblLucro5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblLucro5.setForeground(new java.awt.Color(111, 44, 145));
        lblLucro5.setText("Lucro");
        jPanel8.add(lblLucro5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema.png"))); // NOI18N
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 290, 160));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUsuario)
                .addGap(18, 18, 18)
                .addComponent(btnSair)
                .addGap(127, 127, 127)
                .addComponent(lblData1)
                .addGap(32, 32, 32)
                .addComponent(lblHora1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel18)
                        .addGap(16, 16, 16)
                        .addComponent(lblLinha))
                    .addComponent(jLabel32))
                .addGap(9, 9, 9)
                .addComponent(spnAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel31)
                .addGap(14, 14, 14)
                .addComponent(btnMonitorar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnParar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblEstacao)))
                .addGap(25, 25, 25)
                .addComponent(lblAvisoCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblReticencias1)
                .addGap(7, 7, 7)
                .addComponent(lblReticencias2)
                .addGap(7, 7, 7)
                .addComponent(lblReticencias3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHora1)
                        .addComponent(lblData1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(lblLinha))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(spnAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnMonitorar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnParar))
                            .addComponent(lblEstacao)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAvisoCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblReticencias1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblReticencias2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblReticencias3)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spnAtualizacaoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnAtualizacaoPropertyChange
    }//GEN-LAST:event_spnAtualizacaoPropertyChange

    private void spnAtualizacaoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spnAtualizacaoInputMethodTextChanged
    }//GEN-LAST:event_spnAtualizacaoInputMethodTextChanged

    private void spnAtualizacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnAtualizacaoMouseClicked
    }//GEN-LAST:event_spnAtualizacaoMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        statusMonitoramento = false;
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnMonitorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitorarActionPerformed
        Integer valor = Integer.valueOf(spnAtualizacao.getValue().toString());
        if (!monitorando) {
            if (valor < 6) {
                spnAtualizacao.setValue(6);
            } else {
                Thread threadMonitoramento = new Thread(this::atualizarDados);
                Thread threadReticencias = new Thread(this::reticencias);
                monitorando = true;

                threadMonitoramento.start();
                threadReticencias.start();

                System.out.println("Acao do botao = " + monitorando);
            }
        }
    }//GEN-LAST:event_btnMonitorarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        monitorando = false;
    }//GEN-LAST:event_btnPararActionPerformed

    private void btnProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessosActionPerformed
        TelaProcessos janelaProcessos = new TelaProcessos(maquinaBD);
        janelaProcessos.setVisible(true);
    }//GEN-LAST:event_btnProcessosActionPerformed

    public static boolean isLogin() {
        return logado;
    }

    public void setStatusMonitoramento(boolean statusMonitoramento) {
        this.statusMonitoramento = statusMonitoramento;
    }

    public boolean isStatusMonitoramento() {
        return statusMonitoramento;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMonitoramento(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdministrativoPalavraLabel1;
    private javax.swing.JLabel AdministrativoPalavraLabel3;
    private javax.swing.JLabel AdministrativoPalavraLabel4;
    private javax.swing.JLabel AdministrativoPalavraLabel5;
    private javax.swing.JLabel ClientesPalavraLabel1;
    private javax.swing.JLabel ClientesPalavraLabel3;
    private javax.swing.JLabel ClientesPalavraLabel4;
    private javax.swing.JLabel ClientesPalavraLabel5;
    private javax.swing.JLabel GastoMensalPalavraLabel1;
    private javax.swing.JLabel GastoMensalPalavraLabel3;
    private javax.swing.JLabel GastoMensalPalavraLabel4;
    private javax.swing.JLabel GastoMensalPalavraLabel5;
    private javax.swing.JLabel LucroMensalPalavraLabel1;
    private javax.swing.JLabel LucroMensalPalavraLabel3;
    private javax.swing.JLabel LucroMensalPalavraLabel4;
    private javax.swing.JLabel LucroMensalPalavraLabel5;
    private javax.swing.JLabel MediaMensalPalavraLabel1;
    private javax.swing.JLabel MediaMensalPalavraLabel3;
    private javax.swing.JLabel MediaMensalPalavraLabel4;
    private javax.swing.JLabel MediaMensalPalavraLabel5;
    private javax.swing.JButton btnMonitorar;
    private javax.swing.JButton btnParar;
    private javax.swing.JButton btnProcessos;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAvisoCapturar;
    private javax.swing.JLabel lblCPU;
    private javax.swing.JLabel lblClientesDia;
    private javax.swing.JLabel lblClientesDia1;
    private javax.swing.JLabel lblClientesDia3;
    private javax.swing.JLabel lblClientesDia5;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblEstacao;
    private javax.swing.JLabel lblFabricante;
    private javax.swing.JLabel lblGasto;
    private javax.swing.JLabel lblGasto1;
    private javax.swing.JLabel lblGasto3;
    private javax.swing.JLabel lblGasto5;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblHora1;
    private javax.swing.JLabel lblLinha;
    private javax.swing.JLabel lblLucro;
    private javax.swing.JLabel lblLucro1;
    private javax.swing.JLabel lblLucro3;
    private javax.swing.JLabel lblLucro5;
    private javax.swing.JLabel lblMediaMes;
    private javax.swing.JLabel lblMediaMes1;
    private javax.swing.JLabel lblMediaMes3;
    private javax.swing.JLabel lblMediaMes5;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblReticencias1;
    private javax.swing.JLabel lblReticencias2;
    private javax.swing.JLabel lblReticencias3;
    private javax.swing.JLabel lblSistemaOp;
    private javax.swing.JLabel lblTempCPU;
    private javax.swing.JLabel lblTotalMemoriaRam;
    private javax.swing.JLabel lblUsoDisco;
    private javax.swing.JLabel lblUsoMemoria;
    private javax.swing.JLabel lblUsoProcessador;
    private javax.swing.JLabel lblUsoRAM;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioComputador;
    private javax.swing.JProgressBar pgbTempCPU;
    private javax.swing.JProgressBar pgbUsoCpu;
    private javax.swing.JProgressBar pgbUsoDisco;
    private javax.swing.JProgressBar pgbUsoMemoria;
    private javax.swing.JProgressBar pgbUsoRAM;
    private javax.swing.JSpinner spnAtualizacao;
    // End of variables declaration//GEN-END:variables
}
