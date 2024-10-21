package com.example.post.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.post.dto.CreatePostRequest;
import com.example.post.dto.GetPostResponse;
import com.example.post.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	// 게시글 생성
	@PostMapping
	public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
		postService.createPost(createPostRequest);
		return ResponseEntity.ok().build();
	}

	// 게시글 목록 조회
	@GetMapping
	public ResponseEntity<List<GetPostResponse>> getAllPosts() {
		List<GetPostResponse> posts = postService.getAllPosts();
		return ResponseEntity.ok().body(posts);
	}

	// 특정 게시글 조회
	@GetMapping("/{id}")
	public ResponseEntity<GetPostResponse> getPostById(@PathVariable Long id) {
		GetPostResponse postResponse = postService.getPostById(id);
		return ResponseEntity.ok(postResponse);
	}

	// 게시글 수정
	@PatchMapping("/{id}")
	public ResponseEntity<void> updatePost(@PathVariable Long id, @RequestBody @Valid CreatePostRequest createPostRequest) {
		postService.updatePost(id, createPostRequest);
		return ResponseEntity.ok().build();
	}

	// 게시글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok().build();
	}
}
