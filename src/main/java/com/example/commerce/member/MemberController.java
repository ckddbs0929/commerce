package com.example.commerce.member;

import com.example.commerce.common.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        // @Valid 를 사용해서 MemberForm 클래스에 설정해놓은 (이름 필수)기능이 적용됨
        // BindingResult 는 설정해놓은 것을 입력하지 않았을 경우 오류를 값으로 가짐
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();

        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
