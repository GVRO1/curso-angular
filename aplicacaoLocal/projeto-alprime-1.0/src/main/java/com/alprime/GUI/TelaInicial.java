package com.alprime.GUI;

import com.alprime.bancoDados.Query.QueryBD;
import com.alprime.bancoDados.tabelas.Maquina;
import com.alprime.log.Log;
import com.alprime.log.MensagemLog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Gabriel Vieira
 */
public class TelaInicial extends javax.swing.JFrame {

    private static Integer idMaquina = 0;
    private static boolean statusTelaInicial = true;
    private static Integer botaoEscolhido = 0;

    public TelaInicial() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo_alprime_reduzido.png")));
        lblSucesso.setVisible(false);
        lblErroSenha.setVisible(false);
        lblSucesso.setVisible(false);
        lblErroCodigo.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdMaquina = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblErroSenha = new javax.swing.JLabel();
        lblSucesso = new javax.swing.JLabel();
        lblErroCodigo = new javax.swing.JLabel();
        btnVendaBilhete = new javax.swing.JRadioButton();
        btnMonitoramento = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        btnMostrarSenha = new javax.swing.JCheckBox();
        txtSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(111, 44, 145));
        jLabel1.setText("S T A");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dubai Light", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(111, 44, 145));
        jLabel2.setText("Sistema de Totens da Alprime ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        txtIdMaquina.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        txtIdMaquina.setForeground(new java.awt.Color(82, 186, 54));
        txtIdMaquina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMaquinaActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 160, -1));

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(111, 44, 145));
        jLabel3.setText("Senha");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(111, 44, 145));
        jLabel4.setText("Código da Maquina");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botao_login.png"))); // NOI18N
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 140, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_editado.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 180, 80));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/totenicone.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 20, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cadeado.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metro.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cptm.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        lblErroSenha.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblErroSenha.setForeground(new java.awt.Color(255, 33, 77));
        lblErroSenha.setText("Erro");
        jPanel1.add(lblErroSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 30));

        lblSucesso.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblSucesso.setForeground(new java.awt.Color(82, 186, 54));
        lblSucesso.setText("Erro");
        jPanel1.add(lblSucesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, 20));

        lblErroCodigo.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        lblErroCodigo.setForeground(new java.awt.Color(255, 33, 77));
        lblErroCodigo.setText("Erro");
        jPanel1.add(lblErroCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, 30));

        buttonGroup1.add(btnVendaBilhete);
        btnVendaBilhete.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnVendaBilhete.setForeground(new java.awt.Color(111, 44, 145));
        btnVendaBilhete.setText("Venda de bilhete");
        btnVendaBilhete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 44, 145), 3));
        btnVendaBilhete.setContentAreaFilled(false);
        btnVendaBilhete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendaBilhete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaBilheteActionPerformed(evt);
            }
        });
        jPanel1.add(btnVendaBilhete, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        buttonGroup1.add(btnMonitoramento);
        btnMonitoramento.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnMonitoramento.setForeground(new java.awt.Color(111, 44, 145));
        btnMonitoramento.setText("Monitoramento");
        btnMonitoramento.setActionCommand("  Tela de Monitoramento");
        btnMonitoramento.setContentAreaFilled(false);
        btnMonitoramento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnMonitoramento, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(111, 44, 145));
        jLabel10.setText("Selecione a tela desejada");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        btnMostrarSenha.setBackground(new java.awt.Color(111, 44, 145));
        btnMostrarSenha.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        btnMostrarSenha.setForeground(new java.awt.Color(82, 186, 54));
        btnMostrarSenha.setText("  Mostrar Senha");
        btnMostrarSenha.setActionCommand("     Mostrar Senha");
        btnMostrarSenha.setContentAreaFilled(false);
        btnMostrarSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(btnMostrarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, 20));

        txtSenha.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(82, 186, 54));
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 160, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMaquinaActionPerformed
    }//GEN-LAST:event_txtIdMaquinaActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String idMaquinaString = txtIdMaquina.getText();
        try {
            idMaquina = Integer.valueOf(idMaquinaString);
        } catch (NumberFormatException e) {
            lblErroCodigo.setVisible(true);
            lblErroCodigo.setText("Somente Numeros");
            lblErroSenha.setVisible(false);
        }

        // Se o botão da Tela de Monitoramento estiver clicado
        if (btnMonitoramento.isSelected()) {
            if (idMaquina > 0) {
                Maquina maquina = new Maquina();
                try {
                    maquina = QueryBD.procurarIdMaquina(idMaquina);
                    if (maquina.getCodMaquina().equals(txtSenha.getText())) {
                        lblSucesso.setText("Login Aceito");
                        lblSucesso.setVisible(true);
                        lblErroSenha.setVisible(false);
                        lblErroCodigo.setVisible(false);
                        statusTelaInicial = false;
                        botaoEscolhido = 1;
                        System.out.println("Botão Tela de Monitoramento escolhido");
                        Log log = new Log(maquina.getIdMaquina(), 1);
                        String mensagem = String.format("Usuario %s logado com sucesso", maquina.getLocalizacao().getUsuario().getNomeUsuario());
                        MensagemLog mensagemLog = new MensagemLog(maquina.getIdMaquina(), mensagem, "INFO");
                        log.escrever(mensagemLog);
                    } else {
                        lblErroSenha.setText("Senha Incorreta");
                        lblErroSenha.setVisible(true);
                        lblErroCodigo.setVisible(false);
                    }
                } catch (EmptyResultDataAccessException e) {
                    lblErroCodigo.setText("Código da maquina inválido");
                    lblErroCodigo.setVisible(true);
                    lblErroSenha.setVisible(false);
                } catch (NullPointerException e) {
                    lblErroCodigo.setText("Erro de conexão");
                    lblErroCodigo.setVisible(true);
                    lblErroSenha.setVisible(false);
                }
            }
        } else if (btnVendaBilhete.isSelected()) {
            if (idMaquina > 0) {
                Maquina maquina = new Maquina();
                try {
                    maquina = QueryBD.procurarIdMaquina(idMaquina);
                    if (maquina.getCodMaquina().equals(txtSenha.getText())) {
                        lblSucesso.setText("Login Aceito");
                        lblSucesso.setVisible(true);
                        lblErroSenha.setVisible(false);
                        lblErroCodigo.setVisible(false);
                        botaoEscolhido = 2;
                        System.out.println("Botão Tela de Venda Bilhete escolhido");

                        statusTelaInicial = false;
                        Log log = new Log(maquina.getIdMaquina(), 1);
                        String mensagem = String.format("Usuario %s logado com sucesso", maquina.getLocalizacao().getUsuario().getNomeUsuario());
                        MensagemLog mensagemLog = new MensagemLog(maquina.getIdMaquina(), mensagem, "INFO");
                        log.escrever(mensagemLog);
                    } else {
                        lblErroSenha.setText("Senha Incorreta");
                        lblErroSenha.setVisible(true);
                        lblErroCodigo.setVisible(false);
                    }
                } catch (EmptyResultDataAccessException e) {
                    lblErroCodigo.setText("Código da maquina inválido");
                    lblErroCodigo.setVisible(true);
                    lblErroSenha.setVisible(false);
                }
            }
        }
        System.out.println("");
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarSenhaActionPerformed
        if (btnMostrarSenha.isSelected()) {
            txtSenha.setEchoChar((char) 0);
        } else {
            txtSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_btnMostrarSenhaActionPerformed

    private void btnVendaBilheteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaBilheteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVendaBilheteActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    public static boolean isStatusTelaInicial() {
        return statusTelaInicial;
    }

    public static void setStatusTelaInicial(boolean statusTelaInicial) {
        TelaInicial.statusTelaInicial = statusTelaInicial;
    }

    public static Integer getIdMaquina() {
        return idMaquina;
    }

    public JTextField getTxtIdMaquina() {
        return txtIdMaquina;
    }

    public void setTxtIdMaquina(JTextField txtIdMaquina) {
        this.txtIdMaquina = txtIdMaquina;
    }

    public JTextField getTxtSenha() {
        return txtSenha;
    }

    public JLabel getLblSucesso() {
        return lblSucesso;
    }

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        TelaInicial telaLogin = new TelaInicial();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                telaLogin.setVisible(true);
                Thread loopEntrar = new Thread(this::loopEntrar);
                loopEntrar.start();
            }

            public void loopEntrar() {
                while (true) {
                    System.out.print("");
                    System.out.println("");
                    if (statusTelaInicial == false && botaoEscolhido == 1) {
                        TelaMonitoramento monitoramento = new TelaMonitoramento(telaLogin.getIdMaquina());
                        monitoramento.setVisible(true);
                        telaLogin.setVisible(false);
                        while (true) {
                            if (monitoramento.isStatusMonitoramento() == false) {
                                monitoramento.setVisible(false);
                                telaLogin.getTxtIdMaquina().setText("");
                                telaLogin.getTxtSenha().setText("");
                                telaLogin.getLblSucesso().setVisible(false);
                                telaLogin.setVisible(true);
                                monitoramento.setStatusMonitoramento(true);

                                break;
                            }
                            System.out.print("");
                        }
                        statusTelaInicial = true;
                        System.out.print("");
                    }

                    if (statusTelaInicial == false && botaoEscolhido == 2) {
                        TelaRPA telarpa = new TelaRPA(telaLogin.getIdMaquina());
                        telarpa.setVisible(true);
                        telaLogin.setVisible(false);
                        while (true) {
                            if (TelaRPA.isStatusMonitoramento() == false) {
                                telarpa.setVisible(false);
                                telaLogin.getTxtIdMaquina().setText("");
                                telaLogin.getTxtSenha().setText("");
                                telaLogin.getLblSucesso().setVisible(false);
                                telaLogin.setVisible(true);
                                telarpa.setStatusMonitoramento(true);
                                break;
                            }
                            System.out.print("");
                        }
                        statusTelaInicial = true;
                        System.out.print("");
                    }
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JRadioButton btnMonitoramento;
    private javax.swing.JCheckBox btnMostrarSenha;
    private javax.swing.JRadioButton btnVendaBilhete;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblErroCodigo;
    private javax.swing.JLabel lblErroSenha;
    private javax.swing.JLabel lblSucesso;
    private javax.swing.JTextField txtIdMaquina;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
