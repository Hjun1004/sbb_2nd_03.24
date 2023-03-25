package com.mysite.sbb_v2.question.questionRepository;


import com.mysite.sbb_v2.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {


    Question findBySubject(String sub);
}
