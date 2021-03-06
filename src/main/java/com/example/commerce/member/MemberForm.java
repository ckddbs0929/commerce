package com.example.commerce.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
// DTO 역할을 하는 클래스
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    private String city;
    private String street;
    private String zipcode;
}
