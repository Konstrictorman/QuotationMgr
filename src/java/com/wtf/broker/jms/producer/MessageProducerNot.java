package com.wtf.broker.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageProducerNot {
    
    private static final Logger LOG = LoggerFactory.getLogger(MessageProducerNot.class);
    
    protected JmsTemplate jmsTemplate; 
    
    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessages(final String mensaje) throws JMSException {
		//MessageCreator messageCreator;
		//jmsTemplate.send(messageCreator) .convertAndSend(mensaje);
		jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(mensaje); 
                LOG.info("Sending message xml '{}'", mensaje);
                return message;
            }
        });
		
    }
}
