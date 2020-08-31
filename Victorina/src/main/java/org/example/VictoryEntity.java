package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

 public class VictoryEntity implements Serializable {
        private LinkedList<HashMap<Integer, String>> responds = new LinkedList<HashMap<Integer, String>>();
        private String question;

        VictoryEntity(String question) {
            this.question = question;
        }

        public void addRespond(String respond, int mark) {
            HashMap<Integer, String> tmp = new HashMap<Integer, String>();
            tmp.put(mark, respond);
            responds.add(tmp);
        }

        public LinkedList<HashMap<Integer, String>> getResponds() {
            return responds;
        }

        public String getQuestion() {
            return question;
        }

    }

