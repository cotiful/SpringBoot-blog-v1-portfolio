package site.metacoding.blogv1.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv1.domain.post.Post;
import site.metacoding.blogv1.domain.post.PostRepository;
import site.metacoding.blogv1.domain.user.User;
import site.metacoding.blogv1.service.PostService;
import site.metacoding.blogv1.web.dto.ResponseDto;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final HttpSession session;
    private final PostService postService;

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

        Page<Post> pagePosts = postService.글목록보기(page);
        model.addAttribute("posts", pagePosts);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        return "post/list";
    }

    // 글 상세보기
    @GetMapping("/post/{id}")
    public String detailForm(@PathVariable Integer id, Model model) {
        User principal = (User) session.getAttribute("principal");

        Post postEntity = postService.글상세보기(id);

        if (postEntity == null) {
            return "error/page1";
        }
        if (principal != null) {
            if (principal.getId() == postEntity.getUser().getId()) {
                model.addAttribute("pageOwner", true);
            } else {
                model.addAttribute("pageOwner", false);
            }
        }
        String rawContent = postEntity.getContent();
        String encContent = rawContent.replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>",
                "lt;script/&gt;");
        postEntity.setContent(encContent);
        model.addAttribute("post", postEntity);
        return "post/detailForm";
    }

    // 글 수정페이지
    @GetMapping("/s/post/{id}/update-form")
    public String updateForm(@PathVariable Integer id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "error/page1";
        }
        Post postEntity = postService.글상세보기(id);
        if (postEntity.getUser().getId() != principal.getId()) {
            return "error/page1";
        }
        model.addAttribute("post", postEntity);
        return "post/updateForm";
    }

    // 글 삭제
    @DeleteMapping("/s/post/{id}")
    public @ResponseBody ResponseDto<String> delete(@PathVariable Integer id) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            return new ResponseDto<String>(-1, "로그인이 되지 않았습니다", null);
        }
        Post postEntity = postService.글상세보기(id);

        if (principal.getId() != postEntity.getUser().getId()) {
            return new ResponseDto<String>(-1, "해당글은 삭제하실 수 없습니다.", null);
        }
        postService.글삭제하기(id);
        return new ResponseDto<String>(1, "성공", null);
    }

    // update 글 수정
    @PutMapping("/s/post/{id}")
    public @ResponseBody ResponseDto<String> update(@PathVariable Integer id, @RequestBody Post post) {
        User principal = (User) session.getAttribute("principal");
        // 인증
        if (principal == null) {
            return new ResponseDto<String>(-1, "로그인 되지 않았습니다", null);
        }
        // 권한
        Post postEntity = postService.글상세보기(id);
        if (postEntity.getUser().getId() != principal.getId()) {
            return new ResponseDto<String>(-1, "해당 게시글을 수정할 권한이 없습니다.", null);
        }
        postService.글수정하기(post, id);

        return new ResponseDto<String>(1, "수정성공", null);

    }

    // POST 글쓰기
    @PostMapping("/s/post")
    public String write(Post post) {

        if (session.getAttribute("principal") == null) {
            return "redirect:/login-form";
        }
        User principal = (User) session.getAttribute("principal");
        postService.글쓰기(post, principal);

        return "redirect:/";
    }

}
