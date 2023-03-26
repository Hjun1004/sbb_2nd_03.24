package com.mysite.sbb_v2.answer.answerController;

import com.mysite.sbb_v2.answer.service.AnswerService;
import com.mysite.sbb_v2.question.entity.Question;
import com.mysite.sbb_v2.question.questionRepository.QuestionRepository;
import com.mysite.sbb_v2.question.service.QuestionService;
import groovyjarjarpicocli.CommandLine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/2answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, String content){
        Question q = this.questionService.getQuestion(id);
        this.answerService.create(q, content);
        return "redirect:/2question/detail/%d".formatted(id);
    }
}
