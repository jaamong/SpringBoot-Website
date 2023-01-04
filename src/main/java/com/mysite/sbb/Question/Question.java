package com.mysite.sbb.Question;

import com.mysite.sbb.Answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 질문 하나에는 여러개의 답변이 작성될 수 있다.
     * 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서
     * @OneToMany 속성으로 cascade = CascadeType.REMOVE 사용
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser author;

    /**
     * 하나의 질문에 여러 사람이 추천할 수 있음
     * 한 사람이 여러 개의 질문을 추천할 수 있음
     * 따라서 부모 자식 관계가 아닌 대등한 관계 -> @ManyToMany
     */
    @ManyToMany
    Set<SiteUser> voter; //중복 X

    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
}
