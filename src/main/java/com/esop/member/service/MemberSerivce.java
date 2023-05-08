package com.esop.member.service;

import com.esop.member.dto.MemberDTO;
import com.esop.member.entity.MemberEntity;
import com.esop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSerivce {  //컨트롤러에서 DTO를 가지고 서비스에서 저장을 함, 서비스에서 entity로 변환 후 리포지토리에서 저장함
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        //repository의 save메서드 호출(entity 객체를 넘겨줘야 함)
        //1. dto -> entity 변환
        //2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        //1. 회원이 입력한 이메일로 DB에서 조회 함
        //2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get(); //entity객체 가지고 옴
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //비밀번호 일치
                //entity -> dto 변환
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
