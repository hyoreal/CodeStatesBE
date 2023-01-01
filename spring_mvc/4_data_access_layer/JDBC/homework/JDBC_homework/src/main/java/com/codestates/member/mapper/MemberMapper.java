package com.codestates.member.mapper;

import com.codestates.member.dto.MemberPaginationResponseDto;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    default MemberPaginationResponseDto memberToMemberPaginationResponseDto(Page<Member> members) {

        List<MemberResponseDto> data =
                members.getContent()
                        .stream()
                        .map(this::memberToMemberResponseDto)
                        .collect(Collectors.toList());

        MemberPaginationResponseDto.PageInfo pageInfo = new MemberPaginationResponseDto.PageInfo();

        pageInfo.setPage(members.getPageable().getPageNumber() + 1);
        pageInfo.setSize(members.getPageable().getPageSize());
        pageInfo.setTotalElements((int) members.getTotalElements());
        pageInfo.setTotalPages(members.getTotalPages());

        return new MemberPaginationResponseDto(data, pageInfo);
    }
}
