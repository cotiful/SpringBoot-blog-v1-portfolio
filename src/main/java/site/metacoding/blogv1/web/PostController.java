package site.metacoding.blogv1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class PostController {

    // 글쓰기 페이지
    @GetMapping("/post/write-form")
    public String writeForm() {
        return "post/writeForm";
    }

    // 메인 페이지
    @GetMapping({ "/", "/post/list" })
    public String list() {
        return "post/list";
    }

    // 글 수정페이지
    @GetMapping("/post/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {
        return "post/updateForm";
    }

    // 글 삭제
    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable Integer id) {
        return "redirect:/post" + id;
    }

    // update 글 수정
    @PutMapping("/post/{id}")
    public String update(@PathVariable Integer id) {
        return "redirect:/post/" + id;
    }

    @PostMapping("/post")
    public String write() {
        return "redirect:/";
    }

}
