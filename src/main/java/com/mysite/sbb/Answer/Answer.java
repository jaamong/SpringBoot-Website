package com.mysite.sbb.Answer;

import com.mysite.sbb.Question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question; //N:1 (fk)

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
}