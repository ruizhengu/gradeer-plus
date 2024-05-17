package tech.clegg.gradeer.api;

public interface MessageListener {
    void onMessageReceived(String message, String replyTo, String correlationId);
}
