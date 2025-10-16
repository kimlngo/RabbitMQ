package exchange.basic.json;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class JsonPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        publishJsonMessage();
    }

    public static void publishJsonMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "";
        String queueName = "Queue-1";
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("from_date", "20250101");
        jsonMessage.put("end_date", "20250131");
        jsonMessage.put("email", "recipient@outlook.com");
        jsonMessage.put("extracted_fields", "user_id,user_name,creation_date");

        channel.basicPublish(exchange, queueName, null, jsonMessage.toString()
                                                                   .getBytes());

        channel.close();
        connection.close();
    }
}
