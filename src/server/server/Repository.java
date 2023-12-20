package server.server;

public interface Repository {
    void saveData(String stringData);

    default String readData() {
        return null;
    }

    void saveServiceData(String stringData);

    default String readServiceData() {
        return null;
    }
}
