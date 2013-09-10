package com.wtf.broker.jms.listener;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtf.broker.jms.ProducerApp;
import com.wtf.broker.jms.producer.SimpleMessageProducer;
import com.wtf.quotation.GestorCotizaciones;
import com.wtf.quotation.domain.SolicitudCotizacion;


public class AsyncReceiverQSolicitudes  {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncReceiverQSolicitudes.class);


	public void processMessage(String message) throws JMSException {
		LOG.info("Consumed text message number {}", message);
		SolicitudCotizacion solicitud = GestorCotizaciones.getInstance()
				.getSolicitud(Integer.parseInt(message));
		GestorCotizaciones.getInstance().registrarSolicitud(solicitud);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/producer-jms-context.xml", ProducerApp.class);
        SimpleMessageProducer producer = (SimpleMessageProducer) context.getBean("messageProducer");
        LOG.info("Enviando mensajes a los proveedores");
        producer.sendMessages("ZAPATOS",solicitud);        
        producer.sendMessages("CAMISAS",solicitud);
        producer.sendMessages("PANTALONES",solicitud);
        
	}
	
}
