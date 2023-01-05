package com.mysite.sbb.Question;

import com.mysite.sbb.Answer.Answer;
import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("question is not found"));
    }

    public void create(String subject, String content, SiteUser user) {
        Question question = new Question();

        question.setSubject(subject);
        question.setContent(content);
        question.setAuthor(user);
        question.setCreateAt(LocalDateTime.now());

        questionRepository.save(question);
    }

    public Page<Question> getList(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createAt"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return questionRepository.findAllByKeyword(keyword, pageable);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyAt(LocalDateTime.now());
        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        questionRepository.save(question);
    }

    /**
     * Specification : <인터페이스> 정교한 쿼리 작성을 도와주는 JPA 도구
     * q  : Root, 기준을 의미하는 Question 엔티티 객체
     * u1 : Question 엔티티와 SiteUser 엔티티를 OuterJoin해서 만든 SiteUser 엔티티 객체
     *      Question 엔티티와 SiteUser는 author 속성으로 연결되어 있기 때문에 q.join("author")처럼 조인해야 함
     * a  : Question 엔티티와 Answer를 OuterJoin해서 만든 Answer 엔티티 객체
     *      Question 엔티티와 Answer 엔티티는 answers 속성으로 연결되어 있기 때문에 q.join("answers")처럼 조인해야 함
     * u2 : a 객체와 SiteUser 엔티티와 OuterJoin해서 만든 SiteUser 객체
     */
    private Specification<Question> search(String keyword) {
        return new Specification<Question>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //select 중복 제거
                query.distinct(true);

                //outer join
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answers", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);

                //where
                return cb.or(cb.like(q.get("subject"), "%" + keyword + "%"), //제목
                        cb.like(q.get("content"), "%" + keyword + "%"),      //내용
                        cb.like(u1.get("username"), "%" + keyword + "%"),    //질문 작성자
                        cb.like(a.get("content"), "%" + keyword + "%"),      //답변 내용
                        cb.like(u2.get("username"), "%" + keyword + "%"));   //답변 작성자
            }
        };
    }
}
