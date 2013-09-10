/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wtf.quotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;

/**
 *
 * @author KrullMan
 */
public class GestorCotizaciones {     
    
    static {
        System.setProperty("log.name", System.getProperty("user.dir") + "/logs/log.log");
    }
    
    private static GestorCotizaciones instance;
    //private static Logger log = Logger.getLogger(GestorCotizaciones.class.getName());
    public static List<Item> generalItems = new ArrayList<Item>();
    private Map<SolicitudCotizacion, List<Cotizacion>> solicitudes;
    
    private GestorCotizaciones () {
        
    }
    
    public static GestorCotizaciones getInstance() {
        if (instance == null) {
            instance = new GestorCotizaciones();
        }
        return instance;
    }
    
    public void registrarSolicitud(SolicitudCotizacion solicitud) {
        List cotizaciones = new ArrayList<Cotizacion>();
        solicitudes = new TreeMap<SolicitudCotizacion, List<Cotizacion>>();
        solicitudes.put(solicitud, cotizaciones);        
    }
    
    private List<Cotizacion> getCotizacionesBySolicitudId(int idSolicitud) throws Exception {
       SolicitudCotizacion solicitud = getSolicitudCotizacionById(idSolicitud);
        if (solicitud == null) {
            throw new IllegalArgumentException("No existe solicitud con id: "+idSolicitud);
        }
        if (!solicitud.isOpen()) {
            throw new IllegalStateException("La solicitud con id: "+idSolicitud+" ya está cerrada");
        }
        List<Cotizacion> cotizaciones = solicitudes.get(solicitud);
        return cotizaciones;
    }
    
    public void registrarCotizacion(int idSolicitud, Cotizacion cotizacion) throws Exception {
        List<Cotizacion> cotizaciones = getCotizacionesBySolicitudId(idSolicitud);
        cotizaciones.add(cotizacion);
    }
    
    public SolicitudCotizacion cerrarSolicitud(int id) throws Exception {
        SolicitudCotizacion solicitud = getSolicitudCotizacionById(id);
        if (solicitud == null) {
            throw new IllegalArgumentException("No existe solicitud con id: "+id);
        }
        if (!solicitud.isOpen()) {
            throw new IllegalStateException("La solicitud con id: "+id+" ya está cerrada");
        }
        List<DetalleSolicitudCotizacion> detallesSolicitud = solicitud.getDetalles();
        List<Cotizacion> cotizaciones = getCotizacionesBySolicitudId(id);
        Iterator<DetalleSolicitudCotizacion> iterator = detallesSolicitud.iterator();
        while(iterator.hasNext()) {
            DetalleSolicitudCotizacion dsc = iterator.next();
            int idDetalleSolicitud = dsc.getIdDetalleSolicitudCotizacion();
            List<DetalleCotizacion> detallesCotizacion = new ArrayList<DetalleCotizacion>();            
            Iterator<Cotizacion> itx = cotizaciones.iterator();
            while (itx.hasNext()){
                Cotizacion cotizacion = itx.next();
                DetalleCotizacion dc = cotizacion.getDetalleCotizacionById(idDetalleSolicitud);
                if (dc != null) {
                    detallesCotizacion.add(dc);
                }
            }
            optimize(dsc, detallesCotizacion);
            solicitud.setOpen(false);
        }
        
        return solicitud;
    }
    
    private SolicitudCotizacion getSolicitudCotizacionById(int id) {
        SolicitudCotizacion solicitud = null;
        Set<SolicitudCotizacion> keys = solicitudes.keySet();        
        Iterator<SolicitudCotizacion> it = keys.iterator();
        while (it.hasNext()) {
            SolicitudCotizacion sc = it.next();
            if (sc.getIdSolicitudCotizacion() == id) {
                solicitud = sc;
                break;
            }
        }
        return solicitud;
    }
    
    private void optimize(DetalleSolicitudCotizacion dsc, List<DetalleCotizacion> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            return;
        }
        DetalleCotizacion detalleMinimo = Collections.min(detalles);
        dsc.setPrice(detalleMinimo.getPrice()*dsc.getQuantity());
        dsc.setUnitPrice(detalleMinimo.getPrice());
        dsc.setProveedor(detalleMinimo.getCotizacion().getProveedor());
    }
    
    private void initGeneralItems() {
        generalItems.add(new Item(101, "Botas diélectricas", 100, "RF:AD522"));
        generalItems.add(new Item(102, "Botas industriales", 100, "RF:DYX87"));
        generalItems.add(new Item(103, "Botas militares", 100, "RF:RHS53"));
        generalItems.add(new Item(104, "Zapato casual negro", 100, "RF:ASR65"));
        generalItems.add(new Item(105, "Zapato casual marrón", 100, "RF:XAB72"));
        generalItems.add(new Item(201, "Pantalón pescador", 200, "RF:BA572"));
        generalItems.add(new Item(202, "Pantalón industrial", 200, "RF:5FYXR7"));
        generalItems.add(new Item(203, "Pantalón lino", 200, "RF:5RH5T3"));
        generalItems.add(new Item(204, "Pantalón drill", 200, "RF:9DARF5"));
        generalItems.add(new Item(205, "Jean Levis 501", 200, "RF:8XB7O2"));
        generalItems.add(new Item(301, "Camisa manga larga cuadros", 300, "RF:C88572"));
        generalItems.add(new Item(302, "Camisa manga larga líneas verticales", 300, "RF:C88YXR7"));
        generalItems.add(new Item(303, "Camisa manga larga líneas horizontales", 300, "RF:C98H5T3"));
        generalItems.add(new Item(304, "Camisa manga larga blanca", 300, "RF:C85ARF5"));
        generalItems.add(new Item(305, "Camisa manga corta blanca", 300, "RF:C8X7O2D"));        
    }
    
    
    public static void main(String[] args) {
        GestorCotizaciones gc = getInstance();
        gc.initGeneralItems();
        
        SolicitudCotizacion sc = new SolicitudCotizacion(1);
        Item i1 = GestorCotizaciones.generalItems.get(2);
        Item i2 = GestorCotizaciones.generalItems.get(3);
        Item i3 = GestorCotizaciones.generalItems.get(4);
        Item i4 = GestorCotizaciones.generalItems.get(7);
        Item i5 = GestorCotizaciones.generalItems.get(8);
        Item i6 = GestorCotizaciones.generalItems.get(9);        
        
        DetalleSolicitudCotizacion dsc1 = new DetalleSolicitudCotizacion(1, sc, i1, 3);
        DetalleSolicitudCotizacion dsc2 = new DetalleSolicitudCotizacion(2, sc, i2, 10);        
        DetalleSolicitudCotizacion dsc3 = new DetalleSolicitudCotizacion(3, sc, i3, 5);        
        DetalleSolicitudCotizacion dsc4 = new DetalleSolicitudCotizacion(4, sc, i4, 3);
        DetalleSolicitudCotizacion dsc5 = new DetalleSolicitudCotizacion(5, sc, i5, 1);
        DetalleSolicitudCotizacion dsc6 = new DetalleSolicitudCotizacion(6, sc, i6, 1);
        
        List<DetalleSolicitudCotizacion> detallesSolicitud = new ArrayList<DetalleSolicitudCotizacion>();
        detallesSolicitud.add(dsc1);
        detallesSolicitud.add(dsc2);
        detallesSolicitud.add(dsc3);
        detallesSolicitud.add(dsc4);
        detallesSolicitud.add(dsc5);
        detallesSolicitud.add(dsc6);
        
        sc.setDetalles(detallesSolicitud);
        
        Proveedor p1 = new Proveedor(1, "AC");
        Cotizacion c1 = new Cotizacion(1,p1);
        DetalleCotizacion dc1 = new DetalleCotizacion(1, c1, i6, 2000);
        List detallesCot = new ArrayList<DetalleCotizacion> ();
        detallesCot.add(dc1);
        c1.setDetalles(detallesCot);

        Proveedor p2 = new Proveedor(2, "Garvi");
        Cotizacion c2 = new Cotizacion(2,p2);
        DetalleCotizacion dc2 = new DetalleCotizacion(1, c2, i6, 1800);
        List detallesCot2 = new ArrayList<DetalleCotizacion> ();
        detallesCot2.add(dc2);
        c2.setDetalles(detallesCot2);        
        
        try {
            gc.registrarSolicitud(sc);
            gc.registrarCotizacion(1, c1);
            gc.registrarCotizacion(1, c2);
            SolicitudCotizacion sol = gc.cerrarSolicitud(sc.getIdSolicitudCotizacion());
            System.out.println(sol.toString());
        } catch (Exception e) {
            //log.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
