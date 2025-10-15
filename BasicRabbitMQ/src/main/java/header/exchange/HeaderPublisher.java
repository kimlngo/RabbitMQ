package header.exchange;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeaderPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "Header-Exchange";
        String message = "Message for Mobile and TV";

        Map<String, Object> prop = new HashMap<>();
        prop.put("item1", "mobile");
        prop.put("item2", "television");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        basicProperties = basicProperties.builder()
                                         .headers(prop)
                                         .build();

        channel.basicPublish(exchange, "", basicProperties, message.getBytes());

        channel.close();
        connection.close();
    }
}
