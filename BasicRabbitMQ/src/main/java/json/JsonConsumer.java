package json;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class JsonConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody());
            JSONObject jsonObject = new JSONObject(msg);
            System.out.println("Message Received: " + jsonObject);
            System.out.println("From date: "+ jsonObject.get("from_date"));
            System.out.println("End date: "+ jsonObject.get("end_date"));
            System.out.println("Email: "+ jsonObject.get("email"));
            System.out.println("Extracted Fields: "+ jsonObject.get("extracted_fields"));
        };

        channel.basicConsume("Queue-1", true, deliverCallback, consumerTag -> {
        });
    }
}
