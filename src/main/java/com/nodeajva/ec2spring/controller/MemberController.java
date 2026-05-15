package com.nodeajva.ec2spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodeajva.ec2spring.dto.MemberRequst;
import com.nodeajva.ec2spring.dto.MemberResponse;
import com.nodeajva.ec2spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	public MemberResponse save(@RequestBody MemberRequst request) {
		log.info("[API - LOG] POST /api/members - 요청: {}", request);

		MemberResponse response = memberService.save(request);

		log.info("[API - LOG] POST /api/members - 응답: {}", response);
		return response;

	}

	@GetMapping("/{id}")
	public MemberResponse findId(@PathVariable Long id) {

		log.info("[API - LOG] GET /api/members/{} - 조회 시작", id);
		MemberResponse response = memberService.findById(id);
		log.info("[API - LOG] GET /api/members/{} - 조회 완료", id);
		return response;
	}
}
