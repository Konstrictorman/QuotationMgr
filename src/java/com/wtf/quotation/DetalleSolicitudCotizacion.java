/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wtf.quotation;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author KrullMan
 */
public class DetalleSolicitudCotizacion implements Serializable {
    
    private int idDetalleSolicitudCotizacion;
    private SolicitudCotizacion solicitudCotizacion;
    private Item item;
    private int quantity;
    private Proveedor proveedor;
    private float price;
    private float unitPrice;

    public DetalleSolicitudCotizacion(int idDetalleSolicitudCotizacion, SolicitudCotizacion solicitudCotizacion, Item item, int quantity) {
        this.idDetalleSolicitudCotizacion = idDetalleSolicitudCotizacion;
        this.solicitudCotizacion = solicitudCotizacion;
        this.item = item;
        this.quantity = quantity;
    }
    
    

    public int getIdDetalleSolicitudCotizacion() {
        return idDetalleSolicitudCotizacion;
    }

    public void setIdDetalleSolicitudCotizacion(int idDetalleSolicitudCotizacion) {
        this.idDetalleSolicitudCotizacion = idDetalleSolicitudCotizacion;
    }

    public SolicitudCotizacion getSolicitudCotizacion() {
        return solicitudCotizacion;
    }

    public void setSolicitudCotizacion(SolicitudCotizacion solicitudCotizacion) {
        this.solicitudCotizacion = solicitudCotizacion;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public String toString() {
        String str = "";
        str = getIdDetalleSolicitudCotizacion()+": ";
        str += "id: "+getItem().getIdItem() +"- ";
        str += "desc: "+getItem().getDesc()+"- ";
        str += "cant: "+getQuantity()+"- ";
        str += "unit: "+getUnitPrice()+"- ";        
        str += "total: "+getPrice()+"- ";
        if (getProveedor() != null) {
            str += "prov: "+getProveedor().getName();
        }
        
        return str;
    }
    
}
