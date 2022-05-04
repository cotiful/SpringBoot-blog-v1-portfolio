package site.metacoding.blogv1.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv1.domain.post.Post;
import site.metacoding.blogv1.domain.post.PostRepository;
import site.metacoding.blogv1.domain.user.User;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final HttpSession session;
    private final PostRepository postRepository;

    // 글쓰기 페이지
    @GetMapping("/s/post/write-form")
    public String writeForm() {

        if (session.getAttribute("principal") == null) {
            return "redirect:/login-form";
        }
        return "post/writeForm";
    }

    // 메인 페이지, 글목록페이지
    @GetMapping({ "/", "/post/list" })
    public String list(@RequestParam(defaultValue = "0") Integer page, Model model) {
        // model.addAttribute("posts",
        // postRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        PageRequest pq = PageRequest.of(page, 3);
        model.addAttribute("posts", postRepository.findAll(pq));
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        return "post/list";
    }

    // 글 상세보기
    @GetMapping("/post/{id}")
    public String detailForm(@PathVariable Integer id, Model model) {
        Optional<Post> postOp = postRepository.findById(id);

        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            model.addAttribute("post", postEntity);
            System.out.println("===========================================");
            return "post/detailForm";
        } else {
            return "error/page1";
        }

    }

    // 글 수정페이지
    @GetMapping("/s/post/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {
        return "post/updateForm";
    }

    // 글 삭제
    @DeleteMapping("/s/post/{id}")
    public String delete(@PathVariable Integer id) {
        return "redirect:/";
    }

    // update 글 수정
    @PutMapping("/s/post/{id}")
    public String update(@PathVariable Integer id) {
        return "redirect:/post/" + id;
    }

    // POST 글쓰기
    @PostMapping("/s/post")
    public String write(Post post) {

        if (session.getAttribute("principal") == null) {
            return "redirect:/login-form";
        }
        User principal = (User) session.getAttribute("principal");
        post.setUser(principal);

        postRepository.save(post);

        return "redirect:/";
    }

}
