package com.mysite.sbb_v2.answer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
    @NotEmpty(message = "내용을 필수항목입니다.")
    @Size(max = 20000)
    private String content;
}
