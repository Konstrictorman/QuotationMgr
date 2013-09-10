package com.wtf.broker.jms.listener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

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
        producer.sendMessages(solicitud);
        
	}

	public void processMessage(byte[] message) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(message);
		ObjectInputStream is = new ObjectInputStream(in);
		is.readObject();
		LOG.info("Consumed bytes message number {}", message);
	}

	public void processMessage(Map message) {
		LOG.info("Consumed map message number {}", message);
	}

	public void processMessage(Object message) {
		LOG.info("Consumed object message number {}", message);
	}

	
}
