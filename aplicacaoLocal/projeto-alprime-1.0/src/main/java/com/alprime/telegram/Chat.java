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
class Chat {
    private Integer id;
    private String title;
    private String type;
    private boolean all_members_are_administrators;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAll_members_are_administrators() {
        return all_members_are_administrators;
    }

    public void setAll_members_are_administrators(boolean all_members_are_administrators) {
        this.all_members_are_administrators = all_members_are_administrators;
    }

    @Override
    public String toString() {
        return "Chat{" + "id=" + id + ", title=" + title + ", type=" + type + ", all_members_are_administrators=" + all_members_are_administrators + '}';
    }
    
    
}
