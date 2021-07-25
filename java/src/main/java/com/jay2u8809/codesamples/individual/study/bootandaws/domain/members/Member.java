package com.jay2u8809.codesamples.individual.study.bootandaws.domain.members;

import com.jay2u8809.codesamples.common.BaseEntity;
import com.jay2u8809.codesamples.common.code.Gender;
import com.jay2u8809.codesamples.common.dto.EmbeddedAddress;
import com.jay2u8809.codesamples.common.dto.EmbeddedName;
import com.jay2u8809.codesamples.common.dto.EmbeddedTel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name="member")
@Entity
public class Member extends BaseEntity {

    /**
     * 회원 일련번호
     * 会員一連番号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_sn", nullable = false, precision = 15, scale = 0)
    private Long memberSn;

    /**
     * 회원 ID
     * 会員ID
     */
    @Column(name = "member_id", nullable = false, length = 100)
    private String memberId;

    /**
     * 비밀번호
     * パスワード
     */
    @Column(name = "member_pw", nullable = false, length = 200)
    private String memberPw;

    /**
     * 회원명
     * 会員名
     */
    @Embedded
    private EmbeddedName memberName = new EmbeddedName();

    /**
     * 회원 전화번호(휴대전화)
     * 会員電話番号（携帯）
     */
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "countryNo", column = @Column(name = "country_no_1")), @AttributeOverride(name = "phoneNo", column = @Column(name = "phone_no_1")) })
    private EmbeddedTel phoneNo1 = new EmbeddedTel();

    /**
     * 회원 전화번호(유선전화)
     * 会員電話番高（電話）
     */
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "countryNo", column = @Column(name = "country_no_2")), @AttributeOverride(name = "phoneNo", column = @Column(name = "phone_no_2")) })
    private EmbeddedTel phoneNo2 = new EmbeddedTel();

    /**
     * 회원 주소
     * 会員住所
     */
    @Embedded
    private EmbeddedAddress memberAddress = new EmbeddedAddress();

    /**
     * E메일 주소
     * メールアドレス
     */
    @Column(name = "email_address", nullable = false, length = 200)
    private String emailAddress;

    /**
     * 생일(년도)
     * 誕生日（年度）
     */
    @Column(name = "birthday_year", length = 4)
    private String birthdayYear;

    /**
     * 생일(월)
     * 誕生日（月）
     */
    @Column(name = "birthday_month", length = 2)
    private String birthdayMonth;

    /**
     * 생일(일)
     * 誕生日（日）
     */
    @Column(name = "birthday_day", length = 2)
    private String birthdayDay;

    /**
     * 성별
     * 性別
     */
    @Column(name = "gender_code", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Gender genderCode = Gender.Nothing;

    /**
     * 회원 계정 정보
     * 会員のアカウント情報
     */
    @Embedded
    private MemberAccountInfo memberAccountInfo = new MemberAccountInfo();
}
