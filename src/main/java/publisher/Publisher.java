package publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        directRabbitMQPublish();
    }

    private static void directRabbitMQPublish() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "";
        String queueName = "Queue-1";
        String message = "First Message from RabbitMQ";
        channel.basicPublish(exchange, queueName, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
