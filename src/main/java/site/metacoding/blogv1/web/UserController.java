package site.metacoding.blogv1.web;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv1.domain.user.User;
import site.metacoding.blogv1.domain.user.UserRepository;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

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
        User userEntity = userRepository.save(user);
        return "redirect:/login-form";

    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(User user) {
        User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
        if (userEntity == null) {
            System.out.println("아이디 혹은 패스워드가 틀렸습니다.");
        } else {
            System.out.println("로그인 되었습니다");
            session.setAttribute("principal", userEntity);
        }
        return "redirect:/";
    }

    // 유저 상세 페이지
    @GetMapping("/user/{id}")
    public String detailForm(@PathVariable Integer id, Model model) {

        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            return "error/page1";
        }
        if (principal.getId() != id) {
            return "error/page1";
        }

        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            model.addAttribute("user", userEntity);
            return "user/detailForm";
        } else {
            return "error/page1";
        }
    }

    // 유저 수정 페이지
    @GetMapping("/user/update-form")
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
        session.invalidate();
        return "redirect:/login-form";
    }

}
