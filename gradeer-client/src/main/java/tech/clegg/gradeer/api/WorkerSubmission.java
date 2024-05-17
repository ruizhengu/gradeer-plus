package tech.clegg.gradeer.api;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkerSubmission {
    private final static String QUEUE_LOAD_SUBMISSION = "load_submission";
    private final static String QUEUE_RETURN_SUBMISSION = "return-submission";

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private MessageListener messageListener;

    public WorkerSubmission(MessageListener messageListener) throws IOException, TimeoutException {
        this.messageListener = messageListener;
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_LOAD_SUBMISSION, false, false, false, null);
        channel.queueDeclare(QUEUE_RETURN_SUBMISSION, false, false, false, null);
    }

    public void receiving() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            if (messageListener != null) {
                messageListener.onMessageReceived(message);
            }
            AMQP.BasicProperties props = delivery.getProperties();
            String replyTo = props.getReplyTo();
            String correlationId = props.getCorrelationId();
            sending(replyTo, correlationId);
        };
        channel.basicConsume(QUEUE_LOAD_SUBMISSION, true, deliverCallback, consumerTag -> {
        });
    }

    public void sending(String replyTo, String correlationId) throws IOException {
        String message = "Hello World!";
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .build();
        channel.basicPublish("", replyTo, props, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
