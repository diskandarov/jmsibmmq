package ru.iskdan.jmsibmmq.config;

import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.mq.spring.boot.MQConnectionFactoryFactory;
import com.ibm.mq.jms.MQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Slf4j
@Configuration
@EnableJms
public class IbmMqConfiguration {

    /**
     * Бин для чтения конфигурации соединения с брокером очередей
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "client.publ.jms")
    public MQConfigurationProperties configProperties() {
        return new MQConfigurationProperties();
    }

    /**
     * Бин для создания соединения с брокером очередей
     * @param configProperties
     * @return
     */
    @Bean
    public MQConnectionFactory connectionFactory(MQConfigurationProperties configProperties) {
        return new MQConnectionFactoryFactory(configProperties, null)
                .createConnectionFactory(MQConnectionFactory.class);
    }

    /**
     * Бин для настройки контекста обмена сообщениями (тип, конвертация, транзакционность, кэширование, таймауты, обработка ошибок)
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("5-10");
        // You could still override some of Boot's default if necessary.
        return factory;
    }
}
