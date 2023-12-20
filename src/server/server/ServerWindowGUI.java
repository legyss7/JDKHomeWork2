package server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindowGUI extends JFrame implements ViewServer {
    private final Server server;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnStart, btnStop;
    private JTextArea log;


    public ServerWindowGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
        server = new Server(this, new FileStore());
    }

    private void appendLog(String text) {
        getInfoLog(text + "\n");
        log.append(text + "\n");
    }
    private void createPanel() {
        log = new JTextArea();
        add(new JScrollPane(log));
        add(createButtons(), BorderLayout.SOUTH);
    }
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status()) {
                    appendLog("Сервер уже был запущен");
                } else {
                    star();
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!status()) {
                    appendLog("Сервер уже был остановлен");
                } else {
                    appendLog("Сервер остановлен!");
                    stop();
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
    public Server getConnection() {
        return this.server;
    }
    @Override
    public void star() {
        server.startServer();
    }

    @Override
    public void stop() {
        server.stopServer();
    }

    @Override
    public boolean status() {
        return server.statusServer();
    }

    @Override
    public void getInfoLog(String text) {
        server.addInfoServiceLog(text);
    }

    @Override
    public void sendLogService(String text) {
        appendLog(text);
    }
}