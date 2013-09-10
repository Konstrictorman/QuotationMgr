/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wtf.quotation;

import java.io.Serializable;

/**
 *
 * @author KrullMan
 */
public class Item implements Serializable {
    
    private int idItem;
    private String desc;
    private int category;
    private String referencia;

    public Item(int idItem,  String desc, int category, String referencia) {
        this.idItem = idItem;
        this.desc = desc;
        this.category = category;
        this.referencia = referencia;
    }


    
    

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    
    
}
