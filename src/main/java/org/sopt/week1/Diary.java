package org.sopt.week1;


import java.io.Serializable;
import java.time.LocalDate;

public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String body;
    private LocalDate updatedAt;
    private int patchCount;

    public Diary() {
    }

    private Diary(Long id, String body) {
        this.id = id;
        this.body = body;
        this.patchCount = 0;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public int getPatchCount() {
        return patchCount;
    }

    public static Diary of(Long id, String body) {
        return new Diary(id, body);
    }

    public void updateBody(String body) {
        this.body = body;
    }

    public void updateUpdatedAt() {
        updatedAt = LocalDate.now();
    }

    public void incrementPatchCount() {
        patchCount++;
    }

    public void resetPatchCount() {
        patchCount = 1;
    }
}