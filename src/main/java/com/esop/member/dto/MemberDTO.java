package com.esop.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor //기본생성자
@ToString //필드 값 출력
public class MemberDTO { //필드 선언
    private Long id;
    private String memberEmail; //save.html의 name과 동일한 필드명
    private String memberPassword;
    private String memberName;
}
