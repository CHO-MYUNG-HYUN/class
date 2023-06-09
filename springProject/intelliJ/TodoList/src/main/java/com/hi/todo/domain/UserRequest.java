package com.hi.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserRequest {

    // 변수 선언은 파라미터 이름과 동일하게 선언
    private String keyword;
    private int tno;
    private int page;
}
