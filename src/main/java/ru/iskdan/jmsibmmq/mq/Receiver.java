package ru.iskdan.jmsibmmq.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Receiver {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "${client.publ.jms.queueName}", containerFactory = "myFactory")
    public void receiveMessage(final Message message) {
        try {
            log.info("Message has been received. Id: {}; content: {}", message.getHeaders().getId(), message.getPayload());
            // TODO Расскоментируй если нужно будет отправить в другую очередб
            //jmsTemplate.convertAndSend();
        } catch (Exception e) {
            log.error("Error receiving of message. Message: {}. Error:", message, e);
        }
    }
}
