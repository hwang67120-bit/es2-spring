package com.nodeajva.ec2spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

/**
 * S3 Presigner 설정 클래스
 * Presigned URL 생성을 위한 S3Presigner 빈을 제공합니다.
 */
@Configuration
public class S3Config {
	/* application.yml에서 AWS 리전 읽기 (기본값: ap-northeast-2) */
	@Value("${spring.cloud.aws.region.static:ap-northeast-2}")
	private String region;

	/**
	 * S3 Presigner 빈 생성
	 * @return S3Presigner - Presigned URL 생성 도구
	 */
	@Bean
	public S3Presigner s3Presigner() {
		return S3Presigner.builder()
			.region(Region.of(region))
			.build();
	}
}