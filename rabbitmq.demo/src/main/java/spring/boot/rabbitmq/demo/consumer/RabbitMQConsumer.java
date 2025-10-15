package spring.boot.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import spring.boot.rabbitmq.demo.model.Person;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "Mobile")
    public void getMessage(Person person) {
        System.out.println(person.getName());
    }
}
