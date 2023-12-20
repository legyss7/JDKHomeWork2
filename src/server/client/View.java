package server.client;

public interface View {
    void connectedToServer();
    void disconnectedFromServer();

    void sendMessage(String message);
}
