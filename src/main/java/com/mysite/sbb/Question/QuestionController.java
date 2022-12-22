package com.mysite.sbb.Question;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

/*    @GetMapping("/question/list")
    public String list() {
        return "question_list";
    }*/

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questions = questionService.getList();
        model.addAttribute("questions", questions);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    /**
     * @Valid : 적용 시 QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작
     * BindingResult : @Valid로 인해검증이 수행된 결과를 의미하는 객체
     */
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "question_form";
        questionService.create(questionForm.getSubject(), questionForm.getSubject());
        return "redirect:/question/list";
    }
}
