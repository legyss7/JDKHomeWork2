package server;

import server.client.ClientGUI;
import server.server.ServerWindowGUI;

public class Main {
    public static void main(String[] args) {
        ServerWindowGUI serverWindow = new ServerWindowGUI();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
