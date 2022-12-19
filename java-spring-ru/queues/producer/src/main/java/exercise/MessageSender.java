package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    // Класс, который даёт простой доступ к брокеру сообщений RabbitMQ
    // Позволяет отправлять и получать сообщения
    @Autowired
    RabbitTemplate rabbitTemplate;

    // BEGIN
    public void sendMessage(String msg) {
        LOGGER.info("Sending message: '" + msg + "' to the queue...");
        rabbitTemplate.convertAndSend("exchange", "key", msg);
        LOGGER.info("Message: '" + msg + "' sent successfully to the queue!!!");
    }

    // END
}
