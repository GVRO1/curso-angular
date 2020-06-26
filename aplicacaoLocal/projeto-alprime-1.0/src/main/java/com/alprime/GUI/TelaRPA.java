package com.alprime.GUI;

import com.alprime.bancoDados.Query.QueryBD;
import com.alprime.bancoDados.tabelas.Maquina;
import com.alprime.bancoDados.tabelas.Venda;
import com.alprime.monitoramento.Converssao;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.util.List;

public class TelaRPA extends javax.swing.JFrame {

    private Venda venda;
    private final Maquina maquinaBD;
    private static boolean statusMonitoramento = true;

    public TelaRPA(Integer idMaquina) {
        this.maquinaBD = QueryBD.procurarIdMaquina(idMaquina);
        initComponents();
        jpnFinalizar.setVisible(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo_alprime_reduzido.png")));
        Thread threadDataHora = new Thread(this::atualizarHora);
        threadDataHora.start();
        lblEstacao.setText(maquinaBD.getLocalizacao().getNomeLocalizacao());
        lblLinha.setText(maquinaBD.getLocalizacao().getTipoLinha());
        lblConfirmacao.setVisible(false);
        btnConfirmar.setVisible(false);
        jpnConfirmar.setVisible(false);
        lblErroSenha.setVisible(false);
        lblSucesso.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jpnConfirmar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblSucesso = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnConfirmarVoltar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblErroSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jpnFinalizar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnFinalizarCompra = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblMensagemFinalizar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        botao50reais = new javax.swing.JButton();
        botao5reais = new javax.swing.JButton();
        botao20reais = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        AdministrativoPalavraLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblLinha = new javax.swing.JLabel();
        lblHora1 = new javax.swing.JLabel();
        AdministrativoPalavraLabel4 = new javax.swing.JLabel();
        lblData1 = new javax.swing.JLabel();
        lblConfirmacao = new javax.swing.JLabel();
        btnCarregar = new javax.swing.JButton();
        lblEstacao = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        jpnConfirmar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 52, 148), 5));
        jpnConfirmar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cadeado.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(111, 44, 145));
        jLabel8.setText("Senha");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        lblSucesso.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblSucesso.setForeground(new java.awt.Color(82, 186, 54));
        lblSucesso.setText("Erro");
        jPanel1.add(lblSucesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 110, 30));

        jLabel9.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(111, 44, 145));
        jLabel9.setText("Confirme seu o usuário");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        btnConfirmarVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_confirmar_pequeno.png"))); // NOI18N
        btnConfirmarVoltar.setBorderPainted(false);
        btnConfirmarVoltar.setContentAreaFilled(false);
        btnConfirmarVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmarVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarVoltarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfirmarVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 140, 50));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_cancelar.png"))); // NOI18N
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 70, 40));

        lblErroSenha.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblErroSenha.setForeground(new java.awt.Color(255, 33, 77));
        lblErroSenha.setText("Erro");
        jPanel1.add(lblErroSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 120, 30));

        txtSenha.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(82, 186, 54));
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 160, 30));

        jpnConfirmar.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 200));

        jPanel2.add(jpnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 380, 220));

        jpnFinalizar.setBackground(new java.awt.Color(255, 255, 255));
        jpnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        jpnFinalizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(111, 44, 145));
        jLabel2.setText("Não se esqueça de retirar o cartão");
        jpnFinalizar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        btnFinalizarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_finalizar.png"))); // NOI18N
        btnFinalizarCompra.setBorderPainted(false);
        btnFinalizarCompra.setContentAreaFilled(false);
        btnFinalizarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarCompraActionPerformed(evt);
            }
        });
        jpnFinalizar.add(btnFinalizarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dubai", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(111, 44, 145));
        jLabel6.setText("Boa Viagem!");
        jpnFinalizar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        lblMensagemFinalizar.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        lblMensagemFinalizar.setForeground(new java.awt.Color(111, 44, 145));
        lblMensagemFinalizar.setText("Sua recarga de R$ %.2f foi efetuada com sucesso ");
        jpnFinalizar.add(lblMensagemFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_editado.png"))); // NOI18N
        jpnFinalizar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 180, 80));

        jPanel2.add(jpnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        txtValor.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        txtValor.setForeground(new java.awt.Color(51, 153, 0));
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 44, 145), 2));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        jPanel2.add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 150, 33));

        botao50reais.setBackground(new java.awt.Color(204, 204, 204));
        botao50reais.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botao50reais.setForeground(new java.awt.Color(102, 0, 102));
        botao50reais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/btn_50_reais.png"))); // NOI18N
        botao50reais.setBorderPainted(false);
        botao50reais.setContentAreaFilled(false);
        botao50reais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao50reais.setFocusPainted(false);
        botao50reais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao50reaisActionPerformed(evt);
            }
        });
        jPanel2.add(botao50reais, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, -1));

        botao5reais.setBackground(new java.awt.Color(204, 204, 204));
        botao5reais.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botao5reais.setForeground(new java.awt.Color(102, 0, 102));
        botao5reais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/btn_5_reais.png"))); // NOI18N
        botao5reais.setBorderPainted(false);
        botao5reais.setContentAreaFilled(false);
        botao5reais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao5reais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao5reaisActionPerformed(evt);
            }
        });
        jPanel2.add(botao5reais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 180, 80));

        botao20reais.setBackground(new java.awt.Color(204, 204, 204));
        botao20reais.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botao20reais.setForeground(new java.awt.Color(102, 0, 102));
        botao20reais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/btn_20_reais.png"))); // NOI18N
        botao20reais.setBorderPainted(false);
        botao20reais.setContentAreaFilled(false);
        botao20reais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao20reais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao20reaisActionPerformed(evt);
            }
        });
        jPanel2.add(botao20reais, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 160, 80));

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(111, 44, 145));
        jLabel3.setText("Outro valor:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, 30));

        btnConfirmar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(102, 0, 102));
        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_confirmar.png"))); // NOI18N
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        jPanel2.add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, -1, 70));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 57, 83, -1));

        AdministrativoPalavraLabel.setFont(new java.awt.Font("Dubai", 1, 36)); // NOI18N
        AdministrativoPalavraLabel.setForeground(new java.awt.Color(111, 44, 145));
        AdministrativoPalavraLabel.setText("    Bem-vindo! ");
        jPanel2.add(AdministrativoPalavraLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 220, 30));

        jLabel18.setBackground(new java.awt.Color(111, 44, 145));
        jLabel18.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(111, 44, 145));
        jLabel18.setText("Linha:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 30));

        jLabel19.setBackground(new java.awt.Color(111, 44, 145));
        jLabel19.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(111, 44, 145));
        jLabel19.setText("Estação:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, 50));

        lblLinha.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblLinha.setForeground(new java.awt.Color(111, 44, 145));
        lblLinha.setText("Linha");
        jPanel2.add(lblLinha, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 30));

        lblHora1.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblHora1.setForeground(new java.awt.Color(111, 44, 145));
        lblHora1.setText("HH:MM");
        jPanel2.add(lblHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        AdministrativoPalavraLabel4.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        AdministrativoPalavraLabel4.setForeground(new java.awt.Color(111, 44, 145));
        AdministrativoPalavraLabel4.setText("Selecione o valor para recarga");
        jPanel2.add(AdministrativoPalavraLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 280, 30));

        lblData1.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblData1.setForeground(new java.awt.Color(111, 44, 145));
        lblData1.setText("DD/MM/AAAA");
        jPanel2.add(lblData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        lblConfirmacao.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        lblConfirmacao.setForeground(new java.awt.Color(111, 44, 145));
        lblConfirmacao.setText("Sua recarga é de R$ %.0f,00 ?");
        jPanel2.add(lblConfirmacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, -1, 40));

        btnCarregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCarregar.setForeground(new java.awt.Color(102, 0, 102));
        btnCarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_ok.png"))); // NOI18N
        btnCarregar.setBorderPainted(false);
        btnCarregar.setContentAreaFilled(false);
        btnCarregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCarregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, 90, 50));

        lblEstacao.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblEstacao.setForeground(new java.awt.Color(111, 44, 145));
        lblEstacao.setText("Estacao");
        jPanel2.add(lblEstacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, 50));

        btnFechar.setBackground(new java.awt.Color(111, 44, 145));
        btnFechar.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(111, 44, 145));
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/voltar.png"))); // NOI18N
        btnFechar.setText("Voltar");
        btnFechar.setBorder(null);
        btnFechar.setBorderPainted(false);
        btnFechar.setContentAreaFilled(false);
        btnFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFechar.setFocusPainted(false);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel2.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 70, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_editado.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 180, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
    }//GEN-LAST:event_txtValorActionPerformed

    private void botao5reaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao5reaisActionPerformed
        double valor = 5;
        venda = new Venda(null, valor, LocalDateTime.now().toString(), maquinaBD);
        lblConfirmacao.setText(String.format("Sua recarga é de R$ %.0f,00 ?", valor));
        lblConfirmacao.setVisible(true);
        btnConfirmar.setVisible(true);
        System.out.println(venda);
    }//GEN-LAST:event_botao5reaisActionPerformed

    private void botao20reaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao20reaisActionPerformed
        double valor = 20;
        venda = new Venda(null, valor, LocalDateTime.now().toString(), maquinaBD);
        System.out.println(venda);
        lblConfirmacao.setText(String.format("Sua recarga é de R$ %.0f,00 ?", valor));
        lblConfirmacao.setVisible(true);
        btnConfirmar.setVisible(true);
        System.out.println(venda);
    }//GEN-LAST:event_botao20reaisActionPerformed

    private void botao50reaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao50reaisActionPerformed
        double valor = 50;
        venda = new Venda(null, valor, LocalDateTime.now().toString(), maquinaBD);
        lblConfirmacao.setText(String.format("Sua recarga é de R$ %.0f,00 ?", valor));
        lblConfirmacao.setVisible(true);
        btnConfirmar.setVisible(true);
        System.out.println(venda);

    }//GEN-LAST:event_botao50reaisActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        QueryBD.insertVenda(venda);
        jpnFinalizar.setVisible(true);
        lblMensagemFinalizar.setText(String.format("Sua recarga de R$ %.2f foi efetuada com sucesso!", venda.getValor()));
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        double valor = Double.parseDouble(txtValor.getText());
        venda = new Venda(null, valor, LocalDateTime.now().toString(), maquinaBD);
        lblConfirmacao.setText(String.format("Sua recarga é de R$ %.2f ?", valor));
        lblConfirmacao.setVisible(true);
        btnConfirmar.setVisible(true);
        System.out.println(venda);

    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        jpnConfirmar.setVisible(true);
        botao20reais.setVisible(false);
        botao50reais.setVisible(false);
        botao5reais.setVisible(false);
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarCompraActionPerformed
        jpnFinalizar.setVisible(false);
        txtValor.setText("");
        lblConfirmacao.setVisible(false);
        btnConfirmar.setVisible(false);
    }//GEN-LAST:event_btnFinalizarCompraActionPerformed

    private void btnConfirmarVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarVoltarActionPerformed
        if (txtSenha.getText().equals(maquinaBD.getCodMaquina())) {
            statusMonitoramento = false;
            lblSucesso.setText("Senha aceita");
            lblSucesso.setVisible(true);
            lblErroSenha.setVisible(false);
        } else {
            lblErroSenha.setText("Senha incorreta");
            lblSucesso.setVisible(false);
            lblErroSenha.setVisible(true);
        }
    }//GEN-LAST:event_btnConfirmarVoltarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtSenha.setText("");
        lblErroSenha.setVisible(false);
        botao20reais.setVisible(true);
        botao50reais.setVisible(true);
        botao5reais.setVisible(true);
        jpnConfirmar.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    public void atualizarHora() {
        while (true) {
            List<String> dataHora = Converssao.dataHoraFormatoBrasileiro(LocalDateTime.now().toString());
            lblData1.setText(dataHora.get(0));
            lblHora1.setText(dataHora.get(1));
        }
    }

    public static boolean isStatusMonitoramento() {
        return statusMonitoramento;
    }

    public static void setStatusMonitoramento(boolean statusMonitoramento) {
        TelaRPA.statusMonitoramento = statusMonitoramento;
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
            java.util.logging.Logger.getLogger(TelaRPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRPA(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdministrativoPalavraLabel;
    private javax.swing.JLabel AdministrativoPalavraLabel4;
    private javax.swing.JButton botao20reais;
    private javax.swing.JButton botao50reais;
    private javax.swing.JButton botao5reais;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnConfirmarVoltar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnFinalizarCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel jpnConfirmar;
    private javax.swing.JPanel jpnFinalizar;
    private javax.swing.JLabel lblConfirmacao;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblErroSenha;
    private javax.swing.JLabel lblEstacao;
    private javax.swing.JLabel lblHora1;
    private javax.swing.JLabel lblLinha;
    private javax.swing.JLabel lblMensagemFinalizar;
    private javax.swing.JLabel lblSucesso;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
