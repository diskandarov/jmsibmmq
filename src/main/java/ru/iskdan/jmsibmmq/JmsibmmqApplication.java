package ru.iskdan.jmsibmmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JmsibmmqApplication {
	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(JmsibmmqApplication.class, args);
	}
}
