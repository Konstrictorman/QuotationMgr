package com.wtf.broker.jms.listener;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtf.broker.jms.ProducerApp;
import com.wtf.broker.jms.producer.MessageProducerNot;
import com.wtf.broker.jms.producer.SimpleMessageProducer;
import com.wtf.quotation.GestorCotizaciones;
import com.wtf.quotation.domain.Cotizacion;
import com.wtf.quotation.domain.SolicitudCotizacion;


public class AsyncReceiverQSolicitudes  {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncReceiverQSolicitudes.class);


	public void processMessage(String message) throws Exception {
		String mensaje[]  = message.split(";");
		if (mensaje[0].equals("SOLICITUD")) {
			LOG.info("Identificador de solicitud {}", mensaje[1]);
			SolicitudCotizacion solicitud = GestorCotizaciones.getInstance()
					.getSolicitud(Integer.parseInt( mensaje[1]));
			GestorCotizaciones.getInstance().registrarSolicitud(solicitud);
			
			ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/producer-jms-context.xml", ProducerApp.class);
	        SimpleMessageProducer producer = (SimpleMessageProducer) context.getBean("messageProducer");
	        LOG.info("Enviando mensajes a los proveedores");
	        producer.sendMessages("ZAPATOS",solicitud);        
	        producer.sendMessages("CAMISAS",solicitud);
	        producer.sendMessages("PANTALONES",solicitud);	
		} else {
			LOG.info("Cerrando solicitud Cotización cond id {}", mensaje[1]);
			SolicitudCotizacion solicitud  = GestorCotizaciones.getInstance().cerrarSolicitud(Integer.parseInt(mensaje[1]));
			Cotizacion cotiza = new Cotizacion();
			cotiza.setSolicitud(solicitud);
			
			ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/producer-notificaciones-jms-context.xml", ProducerApp.class);
			MessageProducerNot producer = (MessageProducerNot) context.getBean("messageProducer");
			LOG.info("Enviando mensaje a QNotificaiones");
			producer.sendMessages(fromJavaToJson(cotiza));
		}
        
	}
	
	/**
	 * Convert object to JSON String 
	 * @param object
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String fromJavaToJson(Serializable object)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper.writeValueAsString(object);
	}
	
}
