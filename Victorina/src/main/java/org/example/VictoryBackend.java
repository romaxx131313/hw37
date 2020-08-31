package org.example;

import java.util.HashMap;
import java.util.LinkedList;

public class VictoryBackend {
    private DataObject dataObject;
    private User usedUser;

    VictoryBackend(String folderPath) {
        this.dataObject = new DataObject(folderPath);
    }

    public String authUser(String login, String password) {
        if (dataObject.getUsers().containsKey(login)) {
            if (dataObject.getUsers().get(login).checkPassword(password)) {
                this.usedUser = dataObject.getUsers().get(login);
                return "OK:Авторизация успешна";
            } else {
                return "FAIL:Неправильный пароль";
            }
        } else {
            return "FAIL:Пользователя не существует";
        }
    }

    public String registerUser(String name, String password, String birthDate) {
        HashMap<String, User> tmpUsers = dataObject.getUsers();
        if (!tmpUsers.containsKey(name)) {
            tmpUsers.put(name, new User(name, password, birthDate));
            this.usedUser = tmpUsers.get(name);
            dataObject.setUsers(tmpUsers);
            dataObject.saveData();
            return "Регистрация успешна";
        } else {
            return "Пользователь существует";
        }
    }

    public boolean isAuth() {
        return usedUser != null;
    }

    public void deAuth() {
        this.usedUser = null;
    }

    public User getUsedUser() {
        return usedUser;
    }

    public void save() {
        dataObject.saveData();
    }

    public void changeBirhDate(String newBirthDate) {
        HashMap<String, User> tmpUsers = dataObject.getUsers();
        User tmpUser = tmpUsers.get(usedUser.getLogin());
        tmpUser.setBirthDate(newBirthDate);
        usedUser.setBirthDate(newBirthDate);
        tmpUsers.remove(usedUser.getLogin());
        tmpUsers.put(usedUser.getLogin(), tmpUser);
        dataObject.setUsers(tmpUsers);
        dataObject.saveData();
    }

    public void changePassword(String newPassword) {
        HashMap<String, User> tmpUsers = dataObject.getUsers();
        User tmpUser = tmpUsers.get(usedUser.getLogin());
        tmpUser.setPassword(newPassword);
        usedUser.setPassword(newPassword);
        tmpUsers.remove(usedUser.getLogin());
        tmpUsers.put(usedUser.getLogin(), tmpUser);
        dataObject.setUsers(tmpUsers);
        dataObject.saveData();
    }

    public String getBirthDate() {
        return usedUser.getBirthDate();
    }

    public LinkedList<Victory> getVictories(String category) {
        return dataObject.getVictories().get(category);
    }

    public LinkedList<VictoryResult> getVictoryResult(Victory victory) {
        return dataObject.getVictoryResult().get(victory);
    }

    public void addVictoryResult(Victory victory, VictoryResult victoryResult) {
        HashMap<Victory,LinkedList<VictoryResult>> tmpResults = dataObject.getVictoryResult();
        LinkedList<VictoryResult> tmp =  tmpResults.get(victory) != null ? tmpResults.get(victory) : new LinkedList<VictoryResult>();
        tmp.add(victoryResult);
        tmpResults.put(victory, tmp);
        dataObject.setVictoryResult(tmpResults);
        dataObject.saveData();
    }

    public String[] getVictoryCategories() {
        String[] strings = new String[dataObject.getVictories().keySet().size()];
        dataObject.getVictories().keySet().toArray(strings);
        return strings;
    }

    public void addVictory(Victory victory, String category) {
        HashMap<String, LinkedList<Victory>> tmpVictories = dataObject.getVictories();
        LinkedList<Victory> victories = tmpVictories.get(category) != null ? tmpVictories.get(category) : new LinkedList<Victory>();
        victories.add(victory);
        tmpVictories.put(category, victories);
        dataObject.setVictories(tmpVictories);
        dataObject.saveData();
    }
}
