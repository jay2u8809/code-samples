package com.jay2u8809.codesamples.individual.study.junit.study;

import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitMember;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;
import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JUnitStudyServiceTest {

    @Mock
    JUnitMemberService mockMemberService;

    @Mock
    JUnitStudyRepository mockStudyRepository;

    /**
     * Mockito.mock() 메소드를 이용하는 방법
     * 어노테이션 @ExtendWith(MockitoExtension.class) 과 무관하다.
     */
    @DisplayName("Mock test - mockito.mock()")
    @Test
    void create_study_service_mock_method() {
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
    void create_study_service_mock_annotations() {
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockStudyRepository);

        assertNotNull(service);
    }

    /**
     * Mock 어노테이션 (@Mock) 을 파라미터에서 사용하는 방법
     * 반드시 @ExtendWith(MockitoExtension.class) 선언이 필요하다.
     */
    @DisplayName("Mock test - @Mock param")
    @Test
    void create_study_service_mock_param(@Mock JUnitMemberService memberService, @Mock JUnitStudyRepository repository) {
        JUnitStudyService service = new JUnitStudyService(memberService, repository);

        assertNotNull(service);
    }

    @DisplayName("Mock test - Homework1")
    @Test
    void create_study_service_homework_1() {
        // Given
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockStudyRepository);
        assertNotNull(service);

        JUnitMember member = new JUnitMember();
        member.setMemberId("test1234");
        member.setEmail("test1234@email.com");

        JUnitStudy study = new JUnitStudy(10, null);

        // TODO stubbing: memberService.findByMemberId(), ↑ 의 member 객체를 return
        when(this.mockMemberService.findByMemberId("test1234"))
                .thenReturn(Optional.of(member));
        // TODO stubbing: repository.save(), ↑ 의 study 객체를 return
        when(this.mockStudyRepository.save(study))
                .thenReturn(study);

        // When
        service.createNewStudy("test1234", study);

        // Then
        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }

    @DisplayName("Mock test - Homework2")
    @Test
    void create_study_service_homework_2() {
        // Given
        JUnitStudyService service = new JUnitStudyService(this.mockMemberService, this.mockStudyRepository);
        JUnitStudy study = new JUnitStudy(10, "JAVA Study");

        // TODO stubbing: repository.save(), ↑ 의 study 객체를 return
        given(this.mockStudyRepository.save(study))
                .willReturn(study);

        // When
        service.openStudy(study);

        // Then
        // TODO stubbing: ↑ 의 study 객체의 isOpen 이 true 인지 체크
        assertTrue(study.isOpen());
        // TODO stubbing: ↑ 의 study 객체의 openedDateTime 이 null 이 아닌지 체크
        assertNotNull(study.getOpenedDateTime());
        System.out.println(study.getOpenedDateTime());
        // TODO stubbing: memberService.notify() 가 호출되었는지 체크
        then(this.mockMemberService).should().notify(study);
    }
}