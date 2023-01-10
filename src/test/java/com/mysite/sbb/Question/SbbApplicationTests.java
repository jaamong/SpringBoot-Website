package com.mysite.sbb.Question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository qRepo;

    @Autowired
    private QuestionService questionService;

    @Test
    void saveQuestion() {
        Question q1 = new Question();
        q1.setSubject("sbb1");
        q1.setContent("content1");
        q1.setCreateAt(LocalDateTime.now());
        qRepo.save(q1);

        Question q2 = new Question();
        q1.setSubject("sbb2");
        q1.setContent("content2");
        q1.setCreateAt(LocalDateTime.now());
        qRepo.save(q2);
    }

    @Test
    void findAll() {
        List<Question> questions = qRepo.findAll();
        assertEquals(2, questions.size());

        Question q = questions.get(0);
        assertEquals("sbb1", q.getSubject());
    }

    @Test
    void findById() {
        Optional<Question> optQuestion = qRepo.findById(1);
        if (optQuestion.isPresent()) {
            Question q = optQuestion.get();
            assertEquals("sbb1", q.getSubject());
        }
    }

    @Test
    void findBySubject() {
        Question findQuestion = qRepo.findBySubject("sbb1");
        assertEquals(7, findQuestion.getId());
    }

    @Test
    void findBySubjectAndContent() {
        Question findQuestion = qRepo.findBySubjectAndContent("new", "newContent");
        assertEquals(9, findQuestion.getId());
    }

    @Test
    void findBySubjectLike() {
        List<Question> questionList = qRepo.findBySubjectLike("sb%");
        Question q = questionList.get(0);
        assertEquals("sbb1", q.getSubject());
    }

    @Test
    void updateSubject() {
        Optional<Question> optQuestion = qRepo.findById(7);
        assertTrue(optQuestion.isPresent());

        Question q = optQuestion.get();
        q.setSubject("변경된 제목");
        qRepo.save(q);
    }

    @Test
    void deleteQuestion() {
        assertEquals(3, qRepo.count());

        Optional<Question> optQuestion = qRepo.findById(7);
        assertTrue(optQuestion.isPresent());

        Question q = optQuestion.get();
        qRepo.delete(q);
        assertEquals(2, qRepo.count());
    }

    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다 : [%03d]", i);
            String content = "내용무";
            questionService.create(subject, content, null);
        }
    }
}