import { Column, Entity, Index, PrimaryGeneratedColumn } from 'typeorm';
import { BaseEntity } from '../base.entity';
import { YN } from '../../common/code/YN';
import { MemberStatus } from '../../common/code/MemberStatus';
import { Gender } from '../../common/code/Gender';

@Index('member_pkey', ['memberSn'], { unique: true })
@Entity('member', { schema: 'dev_schema' })
export class Member extends BaseEntity {
  /**
   * 회원 일련번호
   * 会員一連番号
   */
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'member_sn' })
  memberSn: bigint;

  /**
   * uuid
   */
  id: string;

  /**
   * 회원 ID
   * 会員ID
   */
  @Column('character varying', { name: 'member_id', length: 300 })
  memberId: string;

  /**
   * 비밀번호
   * パスワード
   */
  @Column('character varying', { name: 'member_pw', length: 200 })
  memberPw: string;

  /**
   * 회원명1
   * 会員名1
   */
  @Column('character varying', { name: 'name_1', nullable: true, length: 100 })
  name_1: string | null;

  /**
   * 회원명2
   * 会員名2
   */
  @Column('character varying', { name: 'name_2', nullable: true, length: 100 })
  name_2: string | null;

  /**
   * 회원명3
   * 会員名3
   */
  @Column('character varying', { name: 'name_3', nullable: true, length: 100 })
  name_3: string | null;

  /**
   * 회원명4
   * 会員名4
   */
  @Column('character varying', { name: 'name_4', nullable: true, length: 100 })
  name_4: string | null;

  /**
   * 회원 전화번호(휴대전화)
   * 会員電話番号（携帯）
   */
  @Column('character varying', {
    name: 'country_no_1',
    nullable: true,
    length: 10,
  })
  countryNo_1: string | null;

  /**
   * 회원 전화번호(휴대전화)
   * 会員電話番号（携帯）
   */
  @Column('character varying', {
    name: 'phone_no_1',
    nullable: true,
    length: 20,
  })
  phoneNo_1: string | null;

  /**
   * 회원 전화번호(유선전화)
   * 会員電話番高（電話）
   */
  @Column('character varying', {
    name: 'country_no_2',
    nullable: true,
    length: 10,
  })
  countryNo_2: string | null;

  /**
   * 회원 전화번호(유선전화)
   * 会員電話番高（電話）
   */
  @Column('character varying', {
    name: 'phone_no_2',
    nullable: true,
    length: 20,
  })
  phoneNo_2: string | null;

  /**
   * 회원 주소
   * 会員住所
   */
  @Column('character varying', {
    name: 'country_code',
    nullable: true,
    length: 30,
  })
  countryCode: string | null;

  /**
   * 회원 주소
   * 会員住所
   */
  @Column('character varying', { name: 'zip_code', nullable: true, length: 20 })
  zipCode: string | null;

  /**
   * 회원 주소
   * 会員住所
   */
  @Column('character varying', {
    name: 'address_1',
    nullable: true,
    length: 200,
  })
  address_1: string | null;

  /**
   * 회원 주소
   * 会員住所
   */
  @Column('character varying', {
    name: 'address_2',
    nullable: true,
    length: 200,
  })
  address_2: string | null;

  /**
   * 회원 주소
   * 会員住所
   */
  @Column('character varying', {
    name: 'address_3',
    nullable: true,
    length: 200,
  })
  address_3: string | null;

  /**
   * 회원 주소
   * 会員住所
   */
  @Column('character varying', {
    name: 'address_4',
    nullable: true,
    length: 200,
  })
  address_4: string | null;

  /**
   * E메일 주소
   * メールアドレス
   */
  @Column('character varying', { name: 'email_address', length: 200 })
  emailAddress: string;

  /**
   * 생일(년도)
   * 誕生日（年度）
   */
  @Column('character varying', {
    name: 'birthday_year',
    nullable: true,
    length: 4,
  })
  birthdayYear: string | null;

  /**
   * 생일(월)
   * 誕生日（月）
   */
  @Column('character varying', {
    name: 'birthday_month',
    nullable: true,
    length: 2,
  })
  birthdayMonth: string | null;

  /**
   * 생일(일)
   * 誕生日（日）
   */
  @Column('character varying', {
    name: 'birthday_day',
    nullable: true,
    length: 2,
  })
  birthdayDay: string | null;

  /**
   * 성별
   * 性別
   */
  @Column('character varying', {
    name: 'gender_code',
    length: 30,
    default: () => "'Nothing'",
  })
  genderCode: Gender;

  /**
   * 회원 상태 : 정상, 휴면, 탈퇴
   * 会員状態：通常、休眠、退会
   */
  @Column('character varying', {
    name: 'member_status',
    length: 30,
    default: () => "'Normal'",
  })
  memberStatus: MemberStatus;

  /**
   * 별명
   * ニックネム
   */
  @Column('character varying', { name: 'nickname', length: 50 })
  nickname: string;

  /**
   * 마지막 로그인 날짜
   * 最後のログイン日付
   */
  @Column('timestamp with time zone', {
    name: 'final_login_dt',
    nullable: true,
  })
  finalLoginDt: Date | null;

  /**
   * 회원가입일
   * 会員登録日付
   */
  @Column('timestamp with time zone', { name: 'signup_dt' })
  signupDt: Date;

  /**
   * 계정 잠김 여부
   * アカウントロック可否
   */
  @Column('character varying', {
    name: 'account_lock_yn',
    length: 1,
    default: () => "'N'",
  })
  accountLockYn: YN;

  /**
   * 계정 잠김 적용 날짜
   * アカウントロック適用日付
   */
  @Column('timestamp with time zone', {
    name: 'account_lock_dt',
    nullable: true,
  })
  accountLockDt: Date | null;

  /**
   * 휴면 계정 적용 날찌
   * 休眠アカウント適用日付
   */
  @Column('timestamp with time zone', {
    name: 'account_rest_dt',
    nullable: true,
  })
  accountRestDt: Date | null;

  /**
   * 계정 탈퇴 날짜
   * アカウント退会日付
   */
  @Column('timestamp with time zone', {
    name: 'closed_account_dt',
    nullable: true,
  })
  closedAccountDt: Date | null;

  /**
   * 계정 탈퇴 이유
   * アカウント退会理由
   */
  @Column('character varying', {
    name: 'closed_account_reason',
    nullable: true,
    length: 200,
  })
  closedAccountReason: string | null;

  /**
   * 비밀번호 재설정 요청 여부
   * パスワード再設定要求可否
   */
  @Column('character varying', {
    name: 'pw_reset_request_yn',
    length: 1,
    default: () => "'N'",
  })
  pwResetRequestYn: YN;

  /**
   * 비밀번호 인증키
   * パスワード認証キー
   */
  @Column('character varying', { name: 'pw_verify_key', length: 200 })
  pwVerifyKey: string;

  /**
   * 비밀번호 인증키 전송일자
   * パスワード認証キー伝送日付
   */
  @Column('timestamp with time zone', {
    name: 'pw_verify_key_send_dt',
    nullable: true,
  })
  pwVerifyKeySendDt: Date | null;

  /**
   * 비밀번호 인증키 만료 예정 일자
   * パスワード認証キー満了予定日付
   */
  @Column('timestamp with time zone', {
    name: 'pw_verify_expect_dt',
    nullable: true,
  })
  pwVerifyExpectDt: Date | null;

  /**
   * 비밀번호 인증 완료 일자
   * パスワード認証完了日付
   */
  @Column('timestamp with time zone', {
    name: 'pw_verify_complete_dt',
    nullable: true,
  })
  pwVerifyCompleteDt: Date | null;

  /**
   * 마지막 비밀번호 변경 날짜
   * 最後のパスワード変更日付
   */
  @Column('timestamp with time zone', {
    name: 'final_pw_change_dt',
    nullable: true,
  })
  finalPwChangeDt: Date | null;

  /**
   * 비밀번호 인증 실패 횟수
   * パスワード認証失敗回数
   */
  @Column('numeric', {
    name: 'pw_verify_failure_cnt',
    precision: 10,
    scale: 0,
    default: () => '0',
  })
  pwVerifyFailureCnt: string;

  /**
   * 메일 수신 금지 여부
   * メール受信禁止可否
   */
  @Column('character varying', {
    name: 'mail_prohibit_yn',
    length: 1,
    default: () => "'N'",
  })
  mailProhibitYn: YN;
}

/**
 * 회원 기본 정보 설정
 * 会員のデフォルト値設定
 * @param member
 */
export const setMemberDefaultFields = (member: Member): void => {
  if (!(member && member instanceof Member)) {
    return;
  }
  member.signupDt = new Date(new Date().toISOString());
};
