package com.mysite.sbb.Answer;

import com.mysite.sbb.Question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {

        Answer answer = Answer.builder()
                .content(content)
                .createAt(LocalDateTime.now())
                .question(question)
                .author(author)
                .build();

        answerRepository.save(answer);
    }
}
