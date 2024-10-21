package com.example.post.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.post.domain.Post;
import com.example.post.dto.CreatePostRequest;
import com.example.post.dto.GetPostResponse;
import com.example.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	// 게시글 생성
	public void createPost(CreatePostRequest createPostRequest) {
		Post post = new Post(createPostRequest.title(), createPostRequest.content());
		postRepository.save(post);
	}

	// 모든 게시글 조회
	public List<GetPostResponse> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		List<GetPostResponse> getPostResponses = new ArrayList<>();

		for (Post post : posts) {
			GetPostResponse getPostResponse = convertToGetPostResponse(post);
			getPostResponses.add(getPostResponse);
		}

		return getPostResponses;
	}

	// 특정 게시글 조회
	public GetPostResponse getPostById(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
		return convertToGetPostResponse(post);
	}

	// 게시글 수정
	public void updatePost(Long id, CreatePostRequest createPostRequest) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
		post.update(createPostRequest.title(), createPostRequest.content());
		postRepository.save(post);
	}

	// 게시글 삭제
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	private GetPostResponse convertToGetPostResponse(Post post) {
		return new GetPostResponse(
			post.getId(),
			post.getTitle(),
			post.getContent(),
			post.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
			post.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
		);
	}
}
