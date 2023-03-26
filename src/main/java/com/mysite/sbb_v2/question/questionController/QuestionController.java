package com.mysite.sbb_v2.question.questionController;

import com.mysite.sbb_v2.answer.AnswerForm;
import com.mysite.sbb_v2.question.QuestionForm;
import com.mysite.sbb_v2.question.entity.Question;
import com.mysite.sbb_v2.question.questionRepository.QuestionRepository;
import com.mysite.sbb_v2.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@Controller
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/2question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;


    @GetMapping("/list")
    public String list(Model model){
        List<Question> lq = this.questionService.getList();
        model.addAttribute("questionList", lq);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question q = this.questionService.getQuestion(id);
        model.addAttribute("question", q);
        return "question_detail";
    }

    @GetMapping("/create")
    public String createQuestion(QuestionForm questionForm){
        return "question_form";
    }

    @PostMapping("/create")
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/2question/list";
    }


}
