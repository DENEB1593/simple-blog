package io.dev.simpleblog.service;

import io.dev.simpleblog.domain.post.Post;
import io.dev.simpleblog.domain.post.PostDto;
import io.dev.simpleblog.domain.post.PostRepository;
import io.dev.simpleblog.exception.BusinessException;
import io.dev.simpleblog.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public Post write(Post post) {
        return postRepository.save(post);
    }

    public PostDto findOne(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Post.class, id))
                .toDto();
    }
}
