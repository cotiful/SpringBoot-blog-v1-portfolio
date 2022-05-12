package site.metacoding.blogv1.web;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv1.domain.user.User;
import site.metacoding.blogv1.domain.user.UserRepository;
import site.metacoding.blogv1.service.UserService;
import site.metacoding.blogv1.web.dto.ResponseDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    // 유저중복
    @GetMapping("/api/user/username/same-check")
    public @ResponseBody ResponseDto<String> sameCheck(String username) {
        String data = userService.유저네임중복검사(username);
        return new ResponseDto<String>(1, "통신성공", data);

    }

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/joinForm";
    }

    // 회원가입 페이지 - 로그인 하기전
    @PostMapping("/join")
    public String join(User user) {

        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return "redirect:/join-form";
        }
        if (user.getUsername().equals(" ") || user.getPassword().equals(" ") || user.getEmail().equals(" ")) {
            return "redirect:/join-form";
        }

        // 핵심로직
        userService.회원가입(user);
        return "redirect:/login-form";

    }

    @GetMapping("/login-form")
    public String loginForm(HttpServletRequest request, Model model) {

        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies(); // JS
            for (Cookie cookie : cookies) {
                System.out.println("쿠키값" + cookie.getName());
                if (cookie.getName().equals("remember")) {
                    model.addAttribute("remember", cookie.getValue());
                    System.out.println("쿠키 밸류 어떻게 생겼노" + cookie.getValue());
                }
            }
        }

        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(User user, HttpServletResponse response) {

        User userEntity = userService.로그인(user);

        if (userEntity != null) {
            session.setAttribute("principal", userEntity); // 세션을 principal이라는 key 값으로 정해줌

            if (user.getRemember() != null && user.getRemember().equals("on")) {
                response.setHeader("Set-Cookie", "remember=" + user.getUsername());
            }
            return "redirect:/";
        } else {
            return "redirect:/login-form";
        }
    }

    // 유저 상세 페이지
    @GetMapping("/s/user/{id}")
    public String detailForm(@PathVariable Integer id, Model model) {

        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            return "error/page1";
        }
        if (principal.getId() != id) {
            return "error/page1";
        }

        User userEntity = userService.유저정보보기(id);
        if (userEntity == null) {
            return "error/page1";
        } else {
            model.addAttribute("user", userEntity);
            return "user/detailForm";
        }
    }

    // 유저 수정 페이지
    @GetMapping("/s/user/update-form")
    public String updateForm() {
        return "user/updateForm";
    }

    // requestbody -> bufferedReader
    // responseBody -> BufferedWriter
    // 유저수정 - 로그인 하고 난 후
    @PutMapping("/s/user/{id}")
    public @ResponseBody ResponseDto<String> update(@PathVariable Integer id, @RequestBody User user) {
        User principal = (User) session.getAttribute("principal");

        // 1. 인증체크
        if (principal == null) {
            return new ResponseDto<String>(1, "인증안됨", null);
        }
        // 2.권한체크
        if (principal.getId() != id) {
            return new ResponseDto<String>(1, "권한 없어", null);
        }

        User userEntity = userService.유저수정(id, user);
        session.setAttribute("principal", userEntity);
        return new ResponseDto<String>(1, "성공", null);
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/login-form";
    }

}
