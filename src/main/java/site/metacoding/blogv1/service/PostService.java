package site.metacoding.blogv1.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv1.domain.post.Post;
import site.metacoding.blogv1.domain.post.PostRepository;
import site.metacoding.blogv1.domain.user.User;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Page<Post> 글목록보기(Integer page) {
        PageRequest pq = PageRequest.of(page, 3, Sort.by(Direction.DESC, "id"));
        return postRepository.findAll(pq);
    }

    public Post 글상세보기(Integer id) {
        Optional<Post> postOp = postRepository.findById(id);
        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            return postEntity;
        } else {
            return null;
        }
    }

    @Transactional
    public void 글수정하기(Post post, Integer id) {
        Optional<Post> postOp = postRepository.findById(id);

        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            postEntity.setTitle(post.getTitle());
            postEntity.setContent(post.getContent());
        }
    }

    @Transactional
    public void 글삭제하기(Integer id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void 글쓰기(Post post, User principal) {
        post.setUser(principal); // User Fk 추가 글작성 완료하면
        postRepository.save(post);

    }
}
