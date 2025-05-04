package model;

public abstract class NotificationTemplate {
    public final void sendNotification(String message) {
        connectToService();
        formatMessage(message);
        deliverMessage(message);
    }

    protected abstract void connectToService();
    protected abstract void formatMessage(String message);
    protected abstract void deliverMessage(String message);
}

