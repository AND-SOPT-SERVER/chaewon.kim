package org.sopt.diary.api.service;

public class Diary {
    private long id;
    private String name;

    public Diary(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
