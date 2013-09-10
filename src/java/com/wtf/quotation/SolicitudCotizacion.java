/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wtf.quotation;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;


/**
 *
 * @author KrullMan
 */
public class SolicitudCotizacion implements Serializable, Comparable<SolicitudCotizacion> {
    
    private int idSolicitudCotizacion;
    private boolean open;
    private Calendar fechaSolicitud;
    private List<DetalleSolicitudCotizacion> detalles;
    private static Logger log = Logger.getLogger(SolicitudCotizacion.class);
    public static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public SolicitudCotizacion(int idSolicitudCotizacion) {
        this.idSolicitudCotizacion = idSolicitudCotizacion;
        this.open = true;
        this.fechaSolicitud = Calendar.getInstance();
        detalles = new ArrayList<DetalleSolicitudCotizacion>();
    }
    
    

    public int getIdSolicitudCotizacion() {
        return idSolicitudCotizacion;
    }

    public void setIdSolicitudCotizacion(int idSolicitudCotizacion) {
        this.idSolicitudCotizacion = idSolicitudCotizacion;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Calendar getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Calendar fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public List<DetalleSolicitudCotizacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleSolicitudCotizacion> detalles) {
        this.detalles = detalles;
    }
    
    @Override
    public String toString() {
        String str = "";
        str = "id: "+getIdSolicitudCotizacion();
        str += "fecha: "+formatter.format(getFechaSolicitud().getTime())+"\n";
        Iterator<DetalleSolicitudCotizacion> it = getDetalles().iterator();
        while (it.hasNext()) {
            DetalleSolicitudCotizacion dsc = it.next();
            str+= dsc.toString()+"\n";
        }
        
        return str;        
    }

    @Override
    public int compareTo(SolicitudCotizacion o) {
        int x = 0;
        if (o.getIdSolicitudCotizacion() < getIdSolicitudCotizacion()) {
            x = 1;
        } else if (o.getIdSolicitudCotizacion() > getIdSolicitudCotizacion()) {
            x = -1;
        }
        return x;
    }
    
}
