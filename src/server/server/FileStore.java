package server.server;

import java.io.FileReader;
import java.io.FileWriter;

public class FileStore implements Repository {
    private static final String LOG_PATH = "src/server/server/log.txt";
    private static final String LOG_SERVICE_PATH = "src/server/server/log_service.txt";
    @Override
    public void saveData(String stringData) {
        save(stringData, LOG_PATH);
    }

    @Override
    public String readData() {
        return read(LOG_PATH);
    }

    @Override
    public void saveServiceData(String stringData) {
        save(stringData, LOG_SERVICE_PATH);
    }

    @Override
    public String readServiceData() {
        return read(LOG_SERVICE_PATH);
    }

    public void save(String string, String path) {
        try (FileWriter writer = new FileWriter(path, true)){
            writer.write(string);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public String read(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(path);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
