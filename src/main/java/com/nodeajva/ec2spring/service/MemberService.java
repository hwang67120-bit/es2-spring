package com.nodeajva.ec2spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nodeajva.ec2spring.domain.Member;
import com.nodeajva.ec2spring.dto.MemberRequst;
import com.nodeajva.ec2spring.dto.MemberResponse;
import com.nodeajva.ec2spring.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * 회원 관리 서비스
 * 회원 생성 및 조회 비즈니스 로직 처리
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원 생성
	 * @param requst 회원 생성 요청 (이름, MBTI, 나이)
	 * @return MemberResponse - 생성된 회원 정보
	 */
	@Transactional
	public MemberResponse save(MemberRequst requst) {

		Member member = Member.builder()
			.name(requst.name())
			.mbti(requst.mbti())
			.age(requst.age())
			.build();

		Member saved = memberRepository.save(member);

		return MemberResponse.from(saved);

	}

	/**
	 * 회원 조회
	 * @param id 조회할 회원 ID
	 * @return MemberResponse - 조회된 회원 정보
	 * @throws RuntimeException 회원이 존재하지 않을 경우
	 */
	public MemberResponse findById(Long id) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("조회할 대상이 없습니다"));

		return MemberResponse.from(member);
	}
}
