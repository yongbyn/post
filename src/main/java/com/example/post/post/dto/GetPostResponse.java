package com.example.post.post.dto;

public record GetPostResponse(
	Long id,
	String title,
	String content,
	String createdAt,
	String updatedAt
) {
}
