package org.example;

import java.io.Serializable;

public class VictoryResult implements Serializable {
    private String name;
    private int points;

    VictoryResult(String name, int points){
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}

