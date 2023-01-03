package com.mysite.sbb.Answer;

import com.mysite.sbb.Question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Question question; //N:1 (fk)

    @ManyToOne
    private SiteUser author;

    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
}