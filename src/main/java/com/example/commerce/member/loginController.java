package com.example.commerce.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class loginController {

    private final MemberService memberService;

    @GetMapping(value = "/members/login")
    public String login(Model model){
        model.addAttribute("memberForm" , new MemberForm());
        return "members/login";
    }

    @PostMapping(value = "/members/login")
    public String loginSuc(@Valid MemberForm form, BindingResult result) {
        if(result.hasErrors()){
            return "members/login";
        }
        if (!memberService.findName(form.getName()).isEmpty()) {
            return "redirect:/";
        }
        else {
            return "members/login";
        }
    }
}
