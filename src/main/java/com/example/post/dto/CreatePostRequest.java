package com.example.post.dto;

public record CreatePostRequest(
	String title,
	String content
) {
}
