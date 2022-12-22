package com.mysite.sbb.Answer;

import com.mysite.sbb.Question.Question;
import com.mysite.sbb.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable Integer id,
                               @RequestParam String content) {
        Question question = questionService.getQuestion(id);
        answerService.create(question, content);

        return String.format("redirect:/question/detail/%s", id);
    }
}
