/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.telegram;

import java.util.List;

/**
 *
 * @author Gabriel Vieira
 */
public class ApiTelegram {
    private boolean ok;
    private List<Result> result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApiTelegram{" + "ok=" + ok + ", result=" + result + '}';
    }
    
}
