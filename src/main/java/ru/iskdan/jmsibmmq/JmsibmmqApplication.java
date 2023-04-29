package ru.iskdan.jmsibmmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import ru.iskdan.jmsibmmq.mq.Receiver;

@SpringBootApplication
@EnableJms
public class JmsibmmqApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JmsibmmqApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		String message = "<req>\n" +
				"<query>Виктор Иван</query>\n" +
				"<count>7</count>\n" +
				"</req>";
		System.out.println("Sending an email message.");
		jmsTemplate.convertAndSend("JMSqueue2",message);
	}

}
