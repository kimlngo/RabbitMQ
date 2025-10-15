package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "Topic-Exchange";
        String routingKey = "tv.mobile.ac"; //Routing keys for AC: #.ac, Mobile: *.mobile.*, AC: *.ac.*
        String message = "Message Mobile and AC";

        channel.basicPublish(exchange, routingKey, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
