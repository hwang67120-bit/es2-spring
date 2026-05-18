package com.nodeajva.ec2spring.dto;

public record FileDownloadUrlResponse(

	String url
) {

	public FileDownloadUrlResponse(String url) {
		this.url = url;
	}
}