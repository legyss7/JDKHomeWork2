package server.server;

import server.client.Client;


import java.util.ArrayList;
import java.util.List;


public class Server {
    private boolean work = false;
    private final List<Client> clientList;
    private final ViewServer viewServer;
    private final Repository repository;
    private String serviceLog;
    private String messageLog;

    public Server(ViewServer viewServer, Repository repository) {
        this.viewServer = viewServer;
        this.repository = repository;
        clientList = new ArrayList<>();
    }

    public void startServer() {
        work = true;
        messageLog = getHistory();
        serviceLog = getServiceLog();
        viewServer.sendLogService(serviceLog);
        serviceLog = "";
    }

    public void stopServer() {
        saveHistory(messageLog);
        saveServiceLog(serviceLog);
        while (!clientList.isEmpty()) {
            disconnectUser(clientList.getLast());
        }
        work = false;
    }

    public boolean statusServer() {
        return work;
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        viewServer.sendLogService("Подключен к серверу: " + client.getInfoUser());
        clientList.add(client);
        return true;
    }

    public void disconnectUser(Client client) {
        clientList.remove(client);
        client.disconnectedFromServer();
        viewServer.sendLogService("Отключен от сервера: " + client.getInfoUser());
    }

    public void addInfoServiceLog(String text) {
        serviceLog += text;
    }
    public String getHistory() {
        return repository.readData();
    }
    public void saveHistory(String history) {
        repository.saveData(history);
    }

    public String getServiceLog() {
        return repository.readServiceData();
    }

    public void saveServiceLog(String text) {
        repository.saveServiceData(text);
    }

    public void sendMessage(String message) {
        if (!statusServer()) {
            return;
        }
        viewServer.sendLogService(message);
        answerAll(message);
        messageLog += message + "\n";
    }
    private void answerAll(String text) {
        for (Client client : clientList) {
            client.answerFromServer(text);
        }
    }


}
