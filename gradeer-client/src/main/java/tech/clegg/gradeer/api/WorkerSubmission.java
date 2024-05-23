package tech.clegg.gradeer.api;

import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class WorkerSubmission {
    private final static String QUEUE_SEND = "load-submission-send";
    private final static String QUEUE_RECEIVE = "load-submission-receive";

    private final Channel channel;
    private final MessageListener messageListener;
    private ArrayList<String> identifiers;

    public WorkerSubmission(MessageListener messageListener) throws IOException, TimeoutException {
        this.messageListener = messageListener;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_SEND, false, false, false, null);
        channel.queueDeclare(QUEUE_RECEIVE, false, false, false, null);
        identifiers = new ArrayList<>();
    }

    public void receiving() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            AMQP.BasicProperties props = delivery.getProperties();
            String replyTo = props.getReplyTo();
            String correlationId = props.getCorrelationId();
            if (messageListener != null) {
                messageListener.onMessageReceived(message, replyTo, correlationId);
            }
        };
        channel.basicConsume(QUEUE_SEND, true, deliverCallback, consumerTag -> {
        });
    }

    public void sending(String replyTo, String correlationId) throws IOException {
        String message = new Gson().toJson(identifiers);
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .build();
        channel.basicPublish("", replyTo, props, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // empty the identifiers list
        identifiers = new ArrayList<>();
    }

    public void addIdentifier(String identifier) {
        identifiers.add(identifier);
    }
}
