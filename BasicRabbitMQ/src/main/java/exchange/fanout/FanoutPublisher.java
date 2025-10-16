package exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "Fanout-Exchange";
        String routingKey = "";
        String message = "Message sent to the Mobile and AC queue";

        //Empty routingKey is expected, don't put null
        channel.basicPublish(exchange, routingKey, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
