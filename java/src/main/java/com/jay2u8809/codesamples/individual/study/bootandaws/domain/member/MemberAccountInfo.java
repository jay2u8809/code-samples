package com.jay2u8809.codesamples.individual.study.bootandaws.domain.member;

import com.jay2u8809.codesamples.common.code.MemberStatus;
import com.jay2u8809.codesamples.common.code.YN;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 회원 계정 정보
 * 会員のアカウント情報
 */

@Getter
@Setter
@Embeddable
@SuppressWarnings("serial")
//@JsonIdentityInfo(generator = JsonGenerator.class)
public class MemberAccountInfo implements Serializable {

    /**
     * 회원 상태 : 정상, 휴면, 탈퇴
     * 会員状態：通常、休眠、退会
     */
    @Column(name = "member_status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.Normal;

    /**
     * 별명
     * ニックネム
     */
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    /**
     * 마지막 로그인 날짜
     * 最後のログイン日付
     */
    @Column(name = "final_login_dt")
    private LocalDateTime finalLoginDt;

    /**
     * 회원가입일
     * 会員登録日付
     */
    @Column(name = "signup_dt", nullable = false)
    private LocalDateTime signupDt;

    /**
     * 계정 잠김 여부
     * アカウントロック可否
     */
    @Column(name = "account_lock_yn", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private YN accountLockYn = YN.N;

    /**
     * 계정 잠김 적용 날짜
     * アカウントロック適用日付
     */
    @Column(name = "account_lock_dt")
    private LocalDateTime accountLockDt;

    /**
     * 휴면 계정 적용 날찌
     * 休眠アカウント適用日付
     */
    @Column(name = "account_rest_dt")
    private LocalDateTime accountRestDt;

    /**
     * 계정 탈퇴 날짜
     * アカウント退会日付
     */
    @Column(name = "closed_account_dt")
    private LocalDateTime closedAccountDt;

    /**
     * 계정 탈퇴 이유
     * アカウント退会理由
     */
    @Column(name = "closed_account_reason", length = 200)
    private String closedAccountReason;

    /**
     * 비밀번호 재설정 요청 여부
     * パスワード再設定要求可否
     */
    @Column(name = "pw_reset_request_yn", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private YN pwResetRequestYn = YN.N;

    /**
     * 비밀번호 인증키
     * パスワード認証キー
     */
    @Column(name = "pw_verify_key", nullable = false, length = 200)
    private String pwVerifyKey;

    /**
     * 비밀번호 인증키 전송일자
     * パスワード認証キー伝送日付
     */
    @Column(name = "pw_verify_key_send_dt")
    private LocalDateTime pwVerifyKeySendDt;

    /**
     * 비밀번호 인증키 만료 예정 일자
     * パスワード認証キー満了予定日付
     */
    @Column(name = "pw_verify_expect_dt")
    private LocalDateTime pwVerifyExpectDt;

    /**
     * 비밀번호 인증 완료 일자
     * パスワード認証完了日付
     */
    @Column(name = "pw_verify_complete_dt")
    private LocalDateTime pwVerifyCompleteDt;

    /**
     * 마지막 비밀번호 변경 날짜
     * 最後のパスワード変更日付
     */
    @Column(name = "final_pw_change_dt")
    private LocalDateTime finalPwChangeDt;

    /**
     * 비밀번호 인증 실패 횟수
     * パスワード認証失敗回数
     */
    @Column(name = "pw_verify_failure_cnt", nullable = false, precision = 10)
    private Integer pwVerifyFailureCnt = Integer.valueOf("0");

    /**
     * 메일 수신 금지 여부
     * メール受信禁止可否
     */
    @Column(name = "mail_prohibit_yn", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private YN mailProhibitYn = YN.N;
}
