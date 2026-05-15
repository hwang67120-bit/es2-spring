package com.nodeajva.ec2spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nodeajva.ec2spring.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> id(Long id);
}
