package com.mysite.sbb_v2.question.service;

import com.mysite.sbb_v2.DataNotFoundException;
import com.mysite.sbb_v2.question.entity.Question;
import com.mysite.sbb_v2.question.questionRepository.QuestionRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class QuestionService {
    private final QuestionRepository questionRepository;


    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question> oq = this.questionRepository.findById(id);
        if(oq.isPresent()){
            return oq.get();
        } else {
            throw new DataNotFoundException("question not found");
        }

    }

    public void create(String sub, String con) {
        Question q = new Question();
        q.setContent(con);
        q.setSubject(sub);
        this.questionRepository.save(q);
    }
}
