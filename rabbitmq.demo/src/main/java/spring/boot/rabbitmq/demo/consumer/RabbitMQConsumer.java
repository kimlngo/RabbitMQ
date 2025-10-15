package spring.boot.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import spring.boot.rabbitmq.demo.model.Person;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class RabbitMQConsumer {

    /*
    Need to set SPRING_AMQP_DESERIALIZATION_TRUST_ALL=true in the environment variables
     */
//    @RabbitListener(queues = "Mobile")
//    public void getMessage(Person person) {
//        System.out.println(person.getName());
//    }


    @RabbitListener(queues = "Mobile")
    public void getMessage(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput in = new ObjectInputStream(bis);

        Person person = (Person) in.readObject();
        in.close();
        bis.close();

        System.out.println(person.getName());
    }
}
