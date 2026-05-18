package com.nodeajva.ec2spring.controller;

import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nodeajva.ec2spring.dto.FileDownloadUrlResponse;
import com.nodeajva.ec2spring.dto.FileUploadResponse;
import com.nodeajva.ec2spring.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class FileController {

	private final S3Service s3Service;

	@PostMapping("/{id}/profile-image")
	public ResponseEntity<FileUploadResponse> uploadProfileImage(
		@PathVariable Long id,
		@RequestParam("file") MultipartFile file
	) {
		String key = s3Service.upload(file);
		// TODO: DB에 key 저장
		return ResponseEntity.ok(new FileUploadResponse(key));
	}

	@GetMapping("/{id}/profile-image")
	public ResponseEntity<FileDownloadUrlResponse> getProfileImage(
		@PathVariable Long id
	) {
		// TODO: DB에서 key 조회
		String key = "uploads/f5ed36b6-3978-427e-8bda-49dba8cdf97d_스크린샷 2026-05-18 154340.png";
		URL url = s3Service.getDownloadUrl(key);
		return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
	}
}