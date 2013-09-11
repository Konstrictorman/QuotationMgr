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
		LOG.info("Enviando mensaje a QNotificaiones");
		producer.sendMessages(fromJavaToJson(mensaje));
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
