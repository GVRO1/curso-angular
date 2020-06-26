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
public class Message {
    private Integer message_id;
    private From from;
    private Chat chat;
    private Integer date;
    private boolean group_chat_created;

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public boolean isGroup_chat_created() {
        return group_chat_created;
    }

    public void setGroup_chat_created(boolean group_chat_created) {
        this.group_chat_created = group_chat_created;
    }

    @Override
    public String toString() {
        return "Message{" + "message_id=" + message_id + ", from=" + from + ", chat=" + chat + ", date=" + date + ", group_chat_created=" + group_chat_created + '}';
    }
    
    
}
