package com.codestates.member.mapper;

import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.PageInfo;
import com.codestates.dto.SingleResponseDto;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    default SingleResponseDto memberToSingleResponseDto(Member member) {
        return SingleResponseDto.builder().data(member).build();
    }

    default MultiResponseDto membersToMultiResponseDto(Page page) {
        return new MultiResponseDto(page.getContent(),
                                    new PageInfo(page.getNumber() + 1,
                                            page.getSize(),
                                            page.getTotalElements(),
                                            page.getTotalPages()));
    }
}
