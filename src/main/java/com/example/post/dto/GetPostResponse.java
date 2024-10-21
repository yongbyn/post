package com.example.post.dto;

public record GetPostResponse(
	Long id,
	String title,
	String content,
	String createdAt,
	String updatedAt
) {
}
