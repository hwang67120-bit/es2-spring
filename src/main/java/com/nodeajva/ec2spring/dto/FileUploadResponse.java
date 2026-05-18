package com.nodeajva.ec2spring.dto;

public record FileUploadResponse(
	String key
) {

	public FileUploadResponse(String key) {
		this.key = key;
	}
}