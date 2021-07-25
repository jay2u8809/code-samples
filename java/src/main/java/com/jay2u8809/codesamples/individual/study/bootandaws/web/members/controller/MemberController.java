package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.controller;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.individual.study.bootandaws.service.members.MemberService;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/individual/member")
public class MemberController extends CommonExtends {

    private final MemberService memberService;

    private final MemberJoinRequestDto.Validator savedRequestDtoValidator;

    /**
     * register member data
     * @param req
     * @return
     */
    @PostMapping(value = "/register/check/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkRegisterMemberInfo(HttpServletRequest req, @Valid @RequestBody MemberJoinRequestDto joinRequestDto, BindingResult bindingResult) {

        // TODO

        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/register/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> registerMember(HttpServletRequest req, @RequestBody MemberJoinRequestDto joinRequestDto) {

        if (joinRequestDto == null) {
            logger.error("REGISTER NEW MEMBER INFO IS EMPTY");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("DATA is Empty");
        }

        logger.debug("REGISTER NEW MEMBER ID : {}", joinRequestDto.getMemberId());

        return ResponseEntity.ok().body(memberService.saveMember(joinRequestDto));
    }

    /**
     * Signup Form Validation
     * @return
     */
    private Map<String, Object> memberDataValidate(MemberJoinRequestDto joinRequestDto, BindingResult bindingResult) {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        this.validate(joinRequestDto, bindingResult, savedRequestDtoValidator);
        if (bindingResult.hasErrors()) {
            super.bindErrorResult(bindingResult, result);
        }

        return result;
    }


    /**
     * Validate.
     * @param target the target
     * @param bindingResult the binding result
     * @param validators the _validators
     * @return the binding result
     */
    private BindingResult validate(MemberJoinRequestDto target, BindingResult bindingResult, Validator... validators) {

        for (Validator validator : validators) {
            validator.validate(target, bindingResult);
        }

        return bindingResult;
    }
}
