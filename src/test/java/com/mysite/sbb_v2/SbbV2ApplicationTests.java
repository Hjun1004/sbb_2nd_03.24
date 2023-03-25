package com.mysite.sbb_v2;

import com.mysite.sbb_v2.question.entity.Question;
import com.mysite.sbb_v2.question.questionRepository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SbbV2ApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;


	@Test
	void t001() {
		Question q = new Question();
		q.setSubject("sbb2가 무엇인가요?");
		q.setContent("sbb2에 대해서 알고싶습니다.");
		this.questionRepository.save(q);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		this.questionRepository.save(q2);
	}

	@Test
	@DisplayName("findAll()")
	void t002(){
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2,all.size());

		Question q = all.get(0);
		assertEquals("sbb2가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("findbyId()")
	void t003(){
		Question q = this.questionRepository.findById(2).orElse(null);
		assertNotNull(q);
		assertEquals("스프링부트 모델 질문입니다.", q.getSubject());

//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()){
//			Question q = oq.get();
//			assertEquals("sbb2가 무엇인가요?", q.getSubject());
//		}
	}

	@Test
	@DisplayName("findbySubject()")
	void t004(){
		Question q = this.questionRepository.findBySubject("sbb2가 무엇인가요?");

		assertEquals(1, q.getId());
	}

	@Test
	@DisplayName("findbySubjectAndContent")
	void t005(){
		Question q = this.questionRepository.findBySubjectAndContent("스프링부트 모델 질문입니다.", "id는 자동으로 생성되나요?");

		assertEquals(2, q.getId());
	}

}
