package com.mysite.sbb_v2.question.questionController;

import com.mysite.sbb_v2.question.entity.Question;
import com.mysite.sbb_v2.question.questionRepository.QuestionRepository;
import com.mysite.sbb_v2.question.service.QuestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String detail(Model model, @PathVariable("id") Integer id){
        Question q = this.questionService.getQuestion(id);
        model.addAttribute("question", q);
        return "question_detail";
    }


}
