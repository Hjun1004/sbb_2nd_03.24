package com.mysite.sbb_v2.answer.service;

import com.mysite.sbb_v2.answer.answerRepository.AnswerRepository;
import com.mysite.sbb_v2.answer.entity.Answer;
import com.mysite.sbb_v2.question.entity.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question q, String content){
        Answer a = new Answer();
        a.setQuestion(q);
        a.setContent(content);
        this.answerRepository.save(a);
    }
}
