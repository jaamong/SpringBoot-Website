package com.mysite.sbb.Answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.Question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateAt(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);

        answerRepository.save(answer);
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        return optionalAnswer.orElseThrow(() -> {
            throw new DataNotFoundException("answer not found");
        });
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyAt(LocalDateTime.now());
        answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
