package com.alprime.GUI;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import oshi.software.os.OSProcess;

/**
 *
 * @author Gabriel Vieira
 */
public class ProcessosTableModel extends AbstractTableModel {

    private Processos processo = new Processos();
    private List<OSProcess> dados = processo.getListaProcessos();
    private String[] colunas = {"Nome do Processo", "PID", "Prioridade", "Usuario", "Estado"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getName();
            case 1:
                return dados.get(linha).getProcessID();
            case 2:
                return dados.get(linha).getPriority();
            case 3:
                return dados.get(linha).getUser();
            case 4:
                return dados.get(linha).getState();
        }
        return null;
    }

    public List<OSProcess> getDados() {

        return dados;
    }

    public void setDados(List<OSProcess> dados) {
        this.dados = dados;
    }

    public List<OSProcess> getDadosAtuais() {

        return processo.getListaProcessos();
    }

}
