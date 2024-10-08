package org.sopt.week1;

public class Diary {
    private Long id;
    private final String body;

    private Diary(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public static Diary of(Long id, String body) {
        return new Diary(id, body);
    }
}
