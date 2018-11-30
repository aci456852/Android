package com.example.administrator.highlevel;

public class ChatMessage {
    private int id;
    private String name;
    private String words;
    private int place;//1左2右

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWords() {
        return words;
    }

    public int getPlace() {
        return place;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
