package ru.iskdan.jmsibmmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JmsibmmqApplication implements CommandLineRunner {

	private final JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(JmsibmmqApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String message = "<req>\n" +
				"<query>Виктор Иван</query>\n" +
				"<count>7</count>\n" +
				"</req>";
		log.info("Sending an email message");
		jmsTemplate.convertAndSend("JMSqueue2", message);
	}


}
