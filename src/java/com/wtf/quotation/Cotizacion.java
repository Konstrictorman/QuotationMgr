/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wtf.quotation;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author KrullMan
 */
public class Cotizacion {
    
    private int idCotizacion;
    private Calendar fechaCotizacion;
    private Proveedor proveedor;
    private List<DetalleCotizacion> detalles;

    public Cotizacion(int idCotizacion, Proveedor proveedor) {
        this.idCotizacion = idCotizacion;
        this.proveedor = proveedor;
        this.fechaCotizacion = Calendar.getInstance();
    }
    
    

    public int getIdCotizacion() {
        return idCotizacion;
    }
    
    public DetalleCotizacion getDetalleCotizacionById(int id) {
        DetalleCotizacion detalle = null;
        Iterator<DetalleCotizacion> it = detalles.iterator();
        while (it.hasNext()) {
            DetalleCotizacion dc = it.next();
            if (dc.getIdDetalleCotizacion() == id) {
                detalle = dc;
                break;
            }
        }
        return detalle;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Calendar getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(Calendar fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleCotizacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCotizacion> detalles) {
        this.detalles = detalles;
    }

    
    
    
}
