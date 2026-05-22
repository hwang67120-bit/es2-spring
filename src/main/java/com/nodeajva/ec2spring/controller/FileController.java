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

/**
 * 파일(프로필 이미지) 관리 컨트롤러
 * S3 버킷에 이미지 업로드 및 Presigned URL 조회 기능 제공
 */

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class FileController {

	private final S3Service s3Service;

	/**
	 * 프로필 이미지 업로드
	 * @param id 회원 ID
	 * @param file 업로드할 이미지 파일
	 * @return FileUploadResponse - S3에 저장된 파일의 키(경로)
	 */
	@PostMapping("/{id}/profile-image")
	public ResponseEntity<FileUploadResponse> uploadProfileImage(
		@PathVariable Long id,
		@RequestParam("file") MultipartFile file
	) {
		String key = s3Service.upload(file);
		// TODO: DB에 key 저장
		return ResponseEntity.ok(new FileUploadResponse(key));
	}

	/**
	 * 프로필 이미지 조회 (Presigned URL)
	 * @param id 회원 ID
	 * @return FileDownloadUrlResponse - 7일 유효한 Presigned URL
	 */
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