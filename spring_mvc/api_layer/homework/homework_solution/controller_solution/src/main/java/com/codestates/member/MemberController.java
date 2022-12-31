package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>Controller 실습 과제용 Solution 코드 포함</h3>
 *
 * MemberController는 실습 과제에 대한 두 개의 Solution 메서드를 포함하고 있습니다.<p>
 * MemberController는 의존하는 서비스 계층의 클래스는 존재하지 않으며, 따라서 구체적인 비즈니스 로직을 포함하고 있지 않습니다.<p>
 * 클라이언트 측에서는 메모리(Map member)에 저장되어 있는 회원 정보를 수정 및 삭제할 수 있습니다.
 *
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html" target="_blank">@RestController</a>* @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html">@RequestMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/annotation/Validated.html" target="_blank">@Validated</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html" target="_blank">@PostMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PatchMapping.html" target="_blank">@PatchMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html" target="_blank">@GetMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/DeleteMapping.html" target="_blank">@DeleteMapping</a>
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/annotation/PostConstruct.html" target="_blank">@PostConstruct</a>
 * @author  황정식
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    /**
     * MemberController가 의존 객체를 DI 받을 준비가 완료되면 호출됩니다.
     * <p>
     * DI 받을 객체가 없더라도 호출됩니다.
     * <P>
     * 여기서는 실습에 사용할 회원 정보를 Map에 추가하고 있습니다.
     */
    @PostConstruct
    public void init() {
        Map<String, Object> member = new HashMap<>();
        long memberId = 1L;
        member.put("memberId", memberId);
        member.put("email", "hgd@gmail.com");
        member.put("name", "홍길동");
        member.put("phone", "010-1234-5678");

        members.put(memberId, member);
    }

    /**
     * 회원 정보 수정을 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <ul>
     *     <li>memberId에 해당하는 회원 정보가 Map member에 존재하지 않으면 404 Not Found HTTP Status를 전송합니다.</li>
     *     <li>memberId에 해당하는 회원 정보가 Map member에 존재한다면 커피 정보를 수정합니다.</li>
     * </ul>
     * @param memberId  URL Path Variable에 매핑되는 회원 식별자.
     *                  <p>
     *                  회원 정보 수정 대상이 된다.
     * @param phone 수정하고자 하는 회원의 휴대폰 번호
     * @return  수정된 회원 정보인 Map member를 포함한 ResponseEntity
     */
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestParam String phone) {
        // No need Business logic

        Map<String, Object> member = members.get(memberId);

        if(member == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            member.put("phone", phone);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * 회원 정보 삭제를 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     *
     * @param memberId  URL Path Variable에 매핑되는 회원 식별자.
     *                  <p>
     *                  삭제할 대상이 된다.
     * @return  response body를 포함하지 않는 ResponseEntity.
     *          <p>
     *          회원 정보를 삭제할 경우, 삭제되어 존재하지 않으므로 204 No Content를 지정한다.
     */
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        if(members.containsKey(memberId))
            members.remove(memberId);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 회원 정보가 삭제되어 존재하지 않으므로 204 No Content를 지정한다.
    }
}
