package com.wtf.broker.jms.listener;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtf.broker.jms.ProducerApp;
import com.wtf.broker.jms.producer.MessageProducerNot;
import com.wtf.quotation.GestorCotizaciones;
import com.wtf.quotation.domain.Cotizacion;


public class AsyncReceiverQCotizaciones  {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncReceiverQCotizaciones.class);

	public void handleMessage(Serializable  mensaje) throws Exception {
		LOG.info("Received message de QCotizaciones: " , mensaje );
		GestorCotizaciones.getInstance().registrarCotizacion(((Cotizacion)mensaje).getSolicitud().getIdSolicitudCotizacion(),
				(Cotizacion)mensaje);

		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/producer-notificaciones-jms-context.xml", ProducerApp.class);
		MessageProducerNot producer = (MessageProducerNot) context.getBean("messageProducer");
        LOG.info("Enviando mensajes ");
        producer.sendMessages(mensaje);
	}

}
