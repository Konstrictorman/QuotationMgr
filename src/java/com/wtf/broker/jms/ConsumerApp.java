package com.wtf.broker.jms;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author efren
 *
 */
public class ConsumerApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerApp.class);
    
    /**
     * Run the app and tell the producer to send its messages. 
     * 
     * @param args
     * @throws JMSException
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws JMSException, InterruptedException {
    	new ClassPathXmlApplicationContext("/META-INF/spring/consumer-jms-context.xml", ConsumerApp.class);
    	LOG.info("QuotationMgr ready...");
    }
    
}
