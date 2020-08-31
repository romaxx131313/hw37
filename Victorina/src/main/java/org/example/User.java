package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String birthDate;

    public User(String login, String password, String birthDate) {
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }
  public boolean checkPassword(String password){
        return password.equals(this.password);
  }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
