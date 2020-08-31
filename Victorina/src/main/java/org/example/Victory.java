package org.example;

import java.io.Serializable;

public class Victory implements Serializable {
    private String authorName;
    private String victoryName;
    private String victoryCategory;
    private VictoryEntity[] questions = new VictoryEntity[20];
    private int counter=0;

    Victory(String authorName, String victoryName, String victoryCategory) {
        this.authorName = authorName;
        this.victoryName = victoryName;
        this.victoryCategory = victoryCategory;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getVictoryName() {
        return victoryName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void addQuestion(VictoryEntity victoryEntity) {
        if (counter < 20) {
            this.questions[this.counter] = victoryEntity;
            counter++;
        }
    }

    public VictoryEntity getQuestion(int questionId){
        VictoryEntity ve=null;
        if(questionId<counter){
            ve = questions[questionId];
        }
        return ve;
    }
}
