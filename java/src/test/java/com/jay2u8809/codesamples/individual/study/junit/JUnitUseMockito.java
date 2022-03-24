package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.code.MemberStatus;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitMember;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;
import com.jay2u8809.codesamples.individual.study.junit.member.InvalidJUnitMemberException;
import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberNotFoundException;
import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberService;
import com.jay2u8809.codesamples.individual.study.junit.study.JUnitStudyRepository;
import com.jay2u8809.codesamples.individual.study.junit.study.JUnitStudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * Docs: https://javadoc.io/doc/org.mockito
 */
public class JUnitUseMockito {

    private JUnitMemberService mockMemberService;

    private JUnitStudyRepository mockStudyRepository;

    @BeforeEach
    void before_each() {
        // set mock
        this.mockMemberService = Mockito.mock(JUnitMemberService.class);
        this.mockStudyRepository = Mockito.mock(JUnitStudyRepository.class);
    }

    /**
     * return type 이 primitive 인 경우, mock 객체는 0 or false 로 반환
     */
    @DisplayName("Stubbing Test: Primitive Type1")
    @Test
    void mock_test_stubbing_primitive_1() {
        int cnt = this.mockMemberService.countDormancyMember(MemberStatus.Dormancy);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, cnt),
                () -> Assertions.assertNotEquals(1, cnt),
                () -> Assertions.assertNotEquals(-1, cnt)
        );
    }

    /**
     * return type 이 primitive 인 경우, mock 객체는 0 or false 로 반환
     */
    @DisplayName("Stubbing Test: Primitive Type2")
    @Test
    void mock_test_stubbing_primitive_2() {
        String uuid = UUID.randomUUID().toString();
        boolean result = this.mockMemberService.isExist(uuid);
        Assertions.assertFalse(result);
    }

    /**
     * return type 이 String 인 경우, mock 객체는 null 로 반환
     */
    @DisplayName("Stubbing Test: String")
    @Test
    void mock_test_stubbing_string() {
        String uuid = UUID.randomUUID().toString();
        String result = this.mockMemberService.getMemberSnById(uuid);
        Assertions.assertAll(
                () -> Assertions.assertNull(result),
                () -> Assertions.assertThrows(NullPointerException.class, () -> result.length())
        );
    }

    /**
     * return type 이 Optional 인 경우, mock 객체는 value 가 null 인 객체로 반환
     */
    @DisplayName("Stubbing Test: Optional")
    @Test
    void mock_test_stubbing_optional() {
        String email = "email@email.net";
        Optional<JUnitMember> result = this.mockMemberService.findByEmail(email);
        Assertions.assertAll(
                () -> Assertions.assertTrue(result.isEmpty()),
                () -> Assertions.assertThrows(NoSuchElementException.class, () -> result.get())
        );
    }

    /**
     * return type 이 Object 인 경우, mock 객체는 null 로 반환
     */
    @DisplayName("Stubbing Test: Object")
    @Test
    void mock_test_stubbing_object() {
        String uuid = UUID.randomUUID().toString();
        JUnitMember result = this.mockMemberService.findById(uuid, MemberStatus.Dormancy);
        Assertions.assertNull(result);
    }

    /**
     * return type 이 void 인 경우, mock 객체는 어떤 예외도 던지지 않음
     */
    @DisplayName("Stubbing Test: void")
    @Test
    void mock_test_stubbing_void() {
        String uuid = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> this.mockMemberService.checkStatus(uuid), () -> "Doesn't throw an exception");
    }

    /**
     * return type 이 Collections 인 경우, mock 객체는 빈 Collection (empty) 로 반환
     */
    @DisplayName("Stubbing Test: Collection, List")
    @Test
    void mock_test_stubbing_collections() {
        List<JUnitMember> results = this.mockMemberService.fetchNormalMembers();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(results),
                () -> Assertions.assertEquals(0, results.size())
        );
    }

    /**
     * 특정 조건에서 특정한 값을 return 하도록 한다.
     */
    @DisplayName("Stubbing Test: when1")
    @Test
    void mock_test_stubbing_when() {
        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        // Mock condition setting
        // 특정한 email 값인 경우에는 ↑ 의 member 객체를 return 한다.
        Mockito.when(this.mockMemberService.findByEmail("test1234@email.com"))
                .thenReturn(Optional.of(member));

        // member 객체 return
        Assertions.assertEquals(member.getEmail(), this.mockMemberService.findByEmail("test1234@email.com").get().getEmail());
        // member 객체를 return 하지 않기 때문에 빈 optional 객체 return
        Assertions.assertTrue(this.mockMemberService.findByEmail("test4321@email.org").isEmpty());
    }

    /**
     * 특정 조건에서 특정한 값을 return 하도록 한다.
     */
    @DisplayName("Stubbing Test: when argument matcher")
    @Test
    void mock_test_stubbing_when_argument_matcher() {
        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        // Mock condition setting
        // parameter 에 어떤 값이 들어가도 ↑ 의 member 객체를 return 한다.
        Mockito.when(this.mockMemberService.findByEmail(Mockito.any()))
                .thenReturn(Optional.of(member));

        // member 객체 return
        Assertions.assertEquals(member.getEmail(), this.mockMemberService.findByEmail("test1234@email.com").get().getEmail());
        // member 객체 return, parameter 가 달라져도 같은 객체 return
        Assertions.assertEquals(member.getEmail(), this.mockMemberService.findByEmail("test4321@email.org").get().getEmail());
    }

    /**
     * 특정 조건에서 특정한 exception 을 던지도록 한다.
     */
    @DisplayName("Stubbing Test: when exception")
    @Test
    void mock_test_stubbing_when_exception() {
        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        // Mock condition setting
        // 특정한 email 값인 경우에 특정한 예외를 던지게 한다.
        Mockito.doThrow(new JUnitMemberNotFoundException())
                .when(this.mockMemberService).findByEmail("test1234@email.com");

        Assertions.assertThrows(JUnitMemberNotFoundException.class, () -> {
            String email = "test1234@email.com";
            this.mockMemberService.findByEmail(email);
        });
    }

    /**
     * 특정 조건에서 연속하여 테스트한다.
     */
    @DisplayName("Stubbing Test: when serial tests")
    @Test
    void mock_test_stubbing_when_series() {
        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        // Mock condition setting
        // 특정한 email 값인 경우에, ↑ 의 member 객체를 return, exception 발생, empty 객체 return 을 연속적으로 한다.
        Mockito.when(this.mockMemberService.findByEmail("test1234@email.com"))
                .thenReturn(Optional.of(member))
                .thenThrow(JUnitMemberNotFoundException.class)
                .thenReturn(Optional.empty());

        // return member
        Assertions.assertEquals(member.getEmail(), this.mockMemberService.findByEmail("test1234@email.com").get().getEmail());
        // throw exception
        Assertions.assertThrows(JUnitMemberNotFoundException.class, () -> {
            String email = "test1234@email.com";
            this.mockMemberService.findByEmail(email);
        });
        // return empty
        Assertions.assertTrue(this.mockMemberService.findByEmail("test1234@email.com").isEmpty());
    }

    /**
     * Mock 객체 확인
     * 메소드의 실행 횟수 확인
     */
    @DisplayName("Stubbing Test: verify")
    @Test
    void mock_test_stubbing_verify() throws InvalidJUnitMemberException {
        // Given
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockStudyRepository);
        assertNotNull(service);

        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        JUnitStudy study = new JUnitStudy(10, null);

        when(this.mockMemberService.findByMemberId("test1234"))
                .thenReturn(Optional.of(member));
        when(this.mockStudyRepository.save(study))
                .thenReturn(study);

        // When
        service.createNewStudy("test1234", study);

        // Then: verify
        // notify() 가 1번 호출 되었는가 체크
        Mockito.verify(this.mockMemberService, times(1)).notify(study);
        // validate() 가 한 번도 호출 되지 않았는가 체크
        Mockito.verify(this.mockMemberService, never()).validate("test1234");
    }

    /**
     * Mock 객체 확인
     * 메소드의 동작 순서 확인
     */
    @DisplayName("Stubbing Test: verify order")
    @Test
    void mock_test_stubbing_verify_order() {
        // Given
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockStudyRepository);
        assertNotNull(service);

        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        JUnitStudy study = new JUnitStudy(10, null);

        when(this.mockMemberService.findByMemberId("test1234"))
                .thenReturn(Optional.of(member));
        when(this.mockStudyRepository.save(study))
                .thenReturn(study);

        // When
        service.createNewStudy("test1234", study);

        // Then: verify
        // notify() 가 호출 된 다음, saveNotifyHistory() 가 호출되었는가 순서 체크
        InOrder order = Mockito.inOrder(this.mockMemberService);
        order.verify(this.mockMemberService).notify(study);
        order.verify(this.mockMemberService).saveNotifyHistory(study);
    }

    /**
     * BDD(Behavior Driven Development) API
     * Given, When, Then
     */
    @DisplayName("Stubbing Test: verify order")
    @Test
    void mock_test_stubbing_bdd_api() throws InvalidJUnitMemberException {
        // Given
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockStudyRepository);
        assertNotNull(service);

        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        JUnitStudy study = new JUnitStudy(10, null);

        BDDMockito.given(this.mockMemberService.findByMemberId("test1234"))
                .willReturn(Optional.of(member));
        given(this.mockStudyRepository.save(study))
                .willReturn(study);

        // When
        service.createNewStudy("test1234", study);

        // Then: verify
        // notify() 가 1번 호출 되었는가 체크
        BDDMockito.then(this.mockMemberService).should(times(1)).notify(study);
        // validate() 가 한 번도 호출 되지 않았는가 체크
        then(this.mockMemberService).should(never()).validate("test1234");
        // order
        InOrder order = BDDMockito.inOrder(this.mockMemberService);
        order.verify(this.mockMemberService).notify(study);
        order.verify(this.mockMemberService).saveNotifyHistory(study);
    }
}
