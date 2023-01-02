package com.mysite.sbb.Answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
//@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnswerForm {

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}