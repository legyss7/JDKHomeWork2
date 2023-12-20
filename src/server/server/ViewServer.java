package server.server;

public interface ViewServer {
    void star();
    void stop();
    boolean status();
    void getInfoLog(String text);
    void sendLogService(String text);
}
