package org.sopt.diary.domain;

import jakarta.persistence.*;

@Entity
public class SoptMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    protected SoptMember() {};

    public SoptMember(String nickname, String password, String name, int age) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
