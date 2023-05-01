package ru.iskdan.jmsibmmq.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import ru.iskdan.jmsibmmq.ReadFromFile;

import java.util.ArrayList;


@Slf4j
@Component
@RequiredArgsConstructor
public class Receiver {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "${client.publ.jms.queueName}", containerFactory = "myFactory")
    public void receiveMessage(final Message message) {
        try {
            log.info("Message has been received. Id: {}; content: {}", message.getHeaders().getId(), message.getPayload());
            //Обработка сообщения
            ArrayList<String[]> tagList = ReadFromFile.mainRead();
            String messageFinal = (String) message.getPayload();
            for (String[] datastring : tagList) {
                messageFinal = replaceTextBetweenTag(messageFinal, datastring[0], datastring[1]);
            }
            //Отправка сообщения в очередь
            jmsTemplate.convertAndSend("JMSqueue2", messageFinal);
            log.info("Message has been send - " + messageFinal);
        } catch (Exception e) {
            log.error("Error receiving of message. Message: {}. Error:", message, e);
        }
    }
    public String replaceTextBetweenTag(
            final String input,
            final String tag,
            final String mask
    ) {
        final String openingTag = String.format("<%s>", tag);
        final String endTag = String.format("</%s>", tag);
        return input.replaceAll(String.format("(%s).+?(%s)",openingTag,endTag), openingTag + mask + endTag);
    }
}
