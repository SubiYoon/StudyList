package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findAll();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findAll();
        List<MemberDto> list = findMembers.stream().map(m -> new MemberDto(m.getName())).collect(Collectors.toList());

        return new Result(list.size(), list);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    /**
     * 엔터티 자체에 데이터를 담는 api - 비추천
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long memberId = memberService.join(member);

        return new CreateMemberResponse(memberId);
    }

    /**
     * 별도의 DTO를 생성하여 만든 api - 추천
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setAddress(new Address(request.getCity(), request.getStreet(), request.getZipcode()));
        Long memberId = memberService.join(member);

        return new CreateMemberResponse(memberId);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());

        return new UpdateMemberResponse(id, request.getName());
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
        private String city;
        private String street;
        private String zipcode;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberRequest {
        @NotEmpty
        private String name;
    }
}
