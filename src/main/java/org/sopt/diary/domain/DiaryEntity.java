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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private SoptMember soptMember;

    protected DiaryEntity() {
    }

    public DiaryEntity(String title, String content, Category category, boolean isPublic, SoptMember soptMember) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isPublic = isPublic;
        this.soptMember = soptMember;
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

    public Category getCategory() {
        return category;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public SoptMember getSoptMember() {
        return soptMember;
    }

    public void updateDiary(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
