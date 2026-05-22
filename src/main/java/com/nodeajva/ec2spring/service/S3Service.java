package com.nodeajva.ec2spring.service;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

/**
 * S3 파일 관리 서비스
 * S3 버킷에 파일 업로드 및 Presigned URL 생성 기능 제공
 */
@Service
@RequiredArgsConstructor
public class S3Service {

	private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);

	private final S3Client s3Client;
	private final S3Presigner s3Presigner;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;

	/**
	 * S3에 파일 업로드
	 * @param file 업로드할 파일
	 * @return String - S3에 저장된 파일의 키(경로)
	 * @throws RuntimeException 파일 업로드 실패 시
	 */
	public String upload(MultipartFile file) {
		try {
			String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucket)
				.key(key)
				.build();

			s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

			return key;
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 실패", e);
		}
	}

	/**
	 * Presigned URL 생성
	 * @param key S3 파일 키(경로)
	 * @return URL - 7일간 유효한 다운로드 URL
	 */
	public URL getDownloadUrl(String key) {
		GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
			.signatureDuration(PRESIGNED_URL_EXPIRATION)
			.getObjectRequest(req -> req.bucket(bucket).key(key))
			.build();

		PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
		return presignedRequest.url();
	}
}