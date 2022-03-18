package com.jay2u8809.codesamples.individual.study.junit.study;

import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class JUnitStudyServiceTest {

    @Mock
    JUnitMemberService mockMemberService;

    @Mock
    JUnitStudyRepository mockRepository;

    /**
     * Mockito.mock() 메소드를 이용하는 방법
     * 어노테이션 @ExtendWith(MockitoExtension.class) 과 무관하다.
     */
    @DisplayName("Mock test - mockito.mock()")
    @Test
    void create_study_service_1() {
        // Mock
        JUnitMemberService memberService = mock(JUnitMemberService.class);
        JUnitStudyRepository repository = mock(JUnitStudyRepository.class);

        JUnitStudyService service = new JUnitStudyService(memberService, repository);

        assertNotNull(service);
    }

    /**
     * Mock 어노테이션 (@Mock) 을 사용하는 방법
     * 반드시 @ExtendWith(MockitoExtension.class) 선언이 필요하다.
     */
    @DisplayName("Mock test - @Mock")
    @Test
    void create_study_service_2() {
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockRepository);

        assertNotNull(service);
    }

    /**
     * Mock 어노테이션 (@Mock) 을 파라미터에서 사용하는 방법
     * 반드시 @ExtendWith(MockitoExtension.class) 선언이 필요하다.
     */
    @DisplayName("Mock test - @Mock param")
    @Test
    void create_study_service_3(@Mock JUnitMemberService memberService, @Mock JUnitStudyRepository repository) {
        JUnitStudyService service = new JUnitStudyService(memberService, repository);

        assertNotNull(service);
    }

}