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
public class Proveedor implements Serializable {
    
    private int idProveedor;
    private String name;

    public Proveedor(int idProveedor, String name) {
        this.idProveedor = idProveedor;
        this.name = name;
    }

    
    
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
