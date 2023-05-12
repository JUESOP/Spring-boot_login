package com.esop.member.service;

import com.esop.member.dto.MemberDTO;
import com.esop.member.entity.MemberEntity;
import com.esop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor 
public class MemberSerivce {  //컨트롤러에서 DTO를 가지고 서비스에서 저장을 함, 서비스에서 entity로 변환 후 리포지토리에서 저장함
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO); //1. dto -> entity 변환
        memberRepository.save(memberEntity);  //repository의 save메서드 호출(entity 객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail()); //1. 회원이 입력한 이메일로 DB에서 조회 함
        if (byMember.isPresent()) {
            MemberEntity memberEntity = byMember.get(); //entity객체 가지고 옴, optional 처리가 되어서 get으로 한번 까야함
             if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) { //2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
                //비밀번호 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity); //entity -> dto 변환
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        //list entity를 list dto로 변환
        List<MemberDTO> memberDTOList = new ArrayList<>(); //담아갈 객체 만들어줌
        for (MemberEntity memberEntity : memberEntityList) { //memberEntityList 있는 데이터들을 for문을 돌려 하나하나 memberEntity 변수 여기에 넣어줌
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity)); //memberEntity를 dto로 변환 후 빈객체에  넣어줌
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }

    }
}
