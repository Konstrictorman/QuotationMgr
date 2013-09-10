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
public class DetalleCotizacion implements Serializable, Comparable<DetalleCotizacion> {
    
    private int idDetalleCotizacion;
    private Cotizacion cotizacion;
    private Item item;
    private float price;
    private int quantity;

    public DetalleCotizacion(int idDetalleCotizacion, Cotizacion cotizacion, Item item, float price) {
        this.idDetalleCotizacion = idDetalleCotizacion;
        this.cotizacion = cotizacion;
        this.item = item;
        this.price = price;
    }
    
    

    public int getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(int idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(DetalleCotizacion o) {
        int x = 0;
        if (((DetalleCotizacion)o).getPrice() < getPrice()) {
            x = 1;
        } else if (((DetalleCotizacion)o).getPrice() > getPrice()) {
            x = -1;
        }
        return x;
    }
    
    
}
