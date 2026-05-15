package com.nodeajva.ec2spring.dto;

import com.nodeajva.ec2spring.domain.Member;

public record MemberResponse(

	Long id,
	String name,
	String mbti,
	Integer age
) {

	public static MemberResponse from(Member member) {
		return new MemberResponse(
			member.getId(),
			member.getName(),
			member.getMbti(),
			member.getAge()
		);
	}
}
