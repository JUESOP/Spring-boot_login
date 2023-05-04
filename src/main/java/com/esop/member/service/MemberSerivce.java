package com.esop.member.service;

import com.esop.member.dto.MemberDTO;
import com.esop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSerivce {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
    }
}
