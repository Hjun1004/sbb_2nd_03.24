package com.mysite.sbb_v2;

import com.mysite.sbb_v2.answer.answerRepository.AnswerRepository;
import com.mysite.sbb_v2.answer.entity.Answer;
import com.mysite.sbb_v2.question.entity.Question;
import com.mysite.sbb_v2.question.questionRepository.QuestionRepository;
import jakarta.transaction.Transactional;
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

	@Autowired
	private AnswerRepository answerRepository;


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

	@Test
	@DisplayName("findbySubjectLike")
	void t006(){
		List<Question> lq = this.questionRepository.findBySubjectLike("sbb2%");
		Question q = lq.get(0);
		assertEquals("sbb2가 무엇인가요?", q.getSubject());
		/*
		sbb%: "sbb"로 시작하는 문자열
		%sbb: "sbb"로 끝나는 문자열
		%sbb%: "sbb"를 포함하는 문자열
		*/
	}

	@Test
	@DisplayName("ModifyData")
	void t007(){
		Question q = this.questionRepository.findById(1).orElse(null);
		assertEquals("sbb2가 무엇인가요?", q.getSubject());
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	@DisplayName("DeleteData")
	void t008(){
		assertEquals(2, this.questionRepository.count());
		Question q = this.questionRepository.findById(1).orElse(null);
		assertEquals("수정된 제목", q.getSubject());

		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());

	}

	@Test
	@DisplayName("AnswerData")
	void t009(){
		Question q = this.questionRepository.findById(2).orElse(null);
		if(q!=null){
			Answer answer = new Answer();
			answer.setContent("네 자동으로 생성됩니다.");
			answer.setQuestion(q);
			this.answerRepository.save(answer);
		}
	}

	@Test
	@Transactional
	@DisplayName("SelectAnswer")
	void t010(){
//		Question q = this.questionRepository.findBySubject("스프링부트 모델 질문입니다.");
//		Answer a = q.getAnswerList().get(0);
//		assertEquals("네 자동으로 생성됩니다.", a.getContent());

		Optional<Answer> oa = this.answerRepository.findById(1);
		if(oa.isPresent()){
			Answer a = oa.get();
			assertEquals(2, a.getQuestion().getId());
		}
	}

	@Test
	@Transactional
	@DisplayName("SelectAnswerfromQuestion")
	void t011(){
		Question q = this.questionRepository.findById(2).orElse(null);
		if(q!=null){
			assertEquals(1,q.getAnswerList().size());
			Answer a = q.getAnswerList().get(0);
			assertEquals("네 자동으로 생성됩니다.", a.getContent());
		}
	}


}
