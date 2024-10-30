package org.sopt.diary.domain;

import jakarta.persistence.*;

@Entity
public class DiaryEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private SoptMember soptMember;

    protected DiaryEntity() {
    }

    public DiaryEntity(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void updateDiary(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
