package io.dev.simpleblog.service;

import io.dev.simpleblog.domain.post.Post;
import io.dev.simpleblog.domain.post.PostRepository;
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

}
