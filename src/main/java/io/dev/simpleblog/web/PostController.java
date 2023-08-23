package io.dev.simpleblog.web;

import io.dev.simpleblog.domain.post.PostDto;
import io.dev.simpleblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findOne(id));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> findAll() {
        //TODO Pageable 적용
        return null;
    }


}
