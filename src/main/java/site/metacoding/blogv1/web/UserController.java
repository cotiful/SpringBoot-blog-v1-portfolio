package site.metacoding.blogv1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class UserController {

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/joinForm";
    }

    // 회원가입 페이지 - 로그인 하기전
    @PostMapping("/join")
    public String join() {
        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login() {
        return "메이페이지 돌려주기";
    }

    // 유저 상세 페이지
    @GetMapping("/user/{id}")
    public String detailForm(@PathVariable Integer id) {
        return "user/detail";
    }

    // 유저 수정 페이지
    @GetMapping("/user/{id}/update-form")
    public String updateForm() {
        return "user/updateForm";
    }

    // 유저수정 - 로그인 하고 난 후
    @PutMapping("/user/{id}")
    public String update(@PathVariable Integer id) {
        return "redirect:/user/" + id;
    }

    @GetMapping("/logout")
    public String logout() {
        return "메인페이지를 돌려주면 됨";
    }

}
