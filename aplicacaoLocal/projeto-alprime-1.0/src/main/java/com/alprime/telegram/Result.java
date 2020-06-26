/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.telegram;

/**
 *
 * @author Gabriel Vieira
 */
public class Result {
    private Integer update_id;
    private Message message;

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" + "update_id=" + update_id + ", message=" + message + '}';
    }
    
    
}
