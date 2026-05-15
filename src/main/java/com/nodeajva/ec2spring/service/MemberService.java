package com.nodeajva.ec2spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nodeajva.ec2spring.domain.Member;
import com.nodeajva.ec2spring.dto.MemberRequst;
import com.nodeajva.ec2spring.dto.MemberResponse;
import com.nodeajva.ec2spring.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

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

	public MemberResponse findById(Long id) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("조회할 대상이 없습니다"));

		return MemberResponse.from(member);
	}
}
