package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class DataObject {
    private String pathToFolder;
    private Data data = new Data();

    DataObject(String pathToFolder) {
        pathToFolder = this.makeFolder(pathToFolder);
        this.pathToFolder = pathToFolder;
        try {
            data = (Data) this.read(pathToFolder + "data.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Файл " + pathToFolder + "data.dat" + " отсутсвует. Создаю");
            data = new Data();
            this.save(data, pathToFolder + "data.dat");
        }
    }

    public HashMap<String, User> getUsers() {
        return data.users;
    }

    public void setUsers(HashMap<String, User> users) {
        data.users = users;
    }

    public void saveData() {
        save(data, pathToFolder + "data.dat");
    }

    public void setVictories(HashMap<String, LinkedList<Victory>> victories) {
        data.victories = victories;
    }


    public HashMap<String, LinkedList<Victory>> getVictories() {
        return data.victories;
    }

    public void setVictoryResult(HashMap<Victory, LinkedList<VictoryResult>> victoryResult) {
        data.victoryResults = victoryResult;
    }

    public HashMap<Victory, LinkedList<VictoryResult>> getVictoryResult() {
        return data.victoryResults;
    }


    private static Object read(String filename) throws IOException, ClassNotFoundException {
        Object obj = null;
        ObjectInput input = new ObjectInputStream(new FileInputStream(filename));
        obj = input.readObject();
        return obj;
    }

    private String makeFolder(String pathToFolder) {
        if (!pathToFolder.endsWith("/")) {
            if (new File(pathToFolder).mkdir()) {
                System.err.println("Папка " + pathToFolder + " отсутсвует. Создаю");
            }
            pathToFolder = pathToFolder + "/";
        } else {
            if (!pathToFolder.equals("./")) {
                pathToFolder = pathToFolder.substring(0, pathToFolder.length() - 1);
                if (new File(pathToFolder).mkdir()) {
                    System.err.println("Папка " + pathToFolder + " отсутсвует. Создаю");
                }
                pathToFolder = pathToFolder + "/";
            }
        }
        return pathToFolder;
    }

    private static void save(Object obj, String filename) {
        ObjectOutput output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(filename));
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Data implements Serializable {
    public HashMap<String, User> users = new HashMap<>();
    public HashMap<String, LinkedList<Victory>> victories = new HashMap<String, LinkedList<Victory>>();
    public HashMap<Victory, LinkedList<VictoryResult>> victoryResults = new HashMap<Victory, LinkedList<VictoryResult>>();

}
