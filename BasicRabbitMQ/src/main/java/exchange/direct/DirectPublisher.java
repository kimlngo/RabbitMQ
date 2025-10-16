package exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "Direct-Exchange";
        String routingKey = "tv";
        String message = "Message sent to the tv queue";

        channel.basicPublish(exchange, routingKey, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
