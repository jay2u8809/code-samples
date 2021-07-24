import { Column, Entity, Index, PrimaryGeneratedColumn } from "typeorm";
import {BaseEntity} from "../base.entity";
import {YN} from "../../common/code/YN";
import {MemberStatus} from "../../common/code/MemberStatus";
import {Gender} from "../../common/code/Gender";

@Index("member_pkey", ["memberSn"], { unique: true })
@Entity("member", { schema: "dev_schema" })
export class Member extends BaseEntity {

  @PrimaryGeneratedColumn({ type: "bigint", name: "member_sn" })
  memberSn: bigint;

  @Column("character varying", { name: "member_id", length: 300 })
  memberId: string;

  @Column("character varying", { name: "member_pw", length: 200 })
  memberPw: string;

  @Column("character varying", { name: "name_1", nullable: true, length: 100 })
  name_1: string | null;

  @Column("character varying", { name: "name_2", nullable: true, length: 100 })
  name_2: string | null;

  @Column("character varying", { name: "name_3", nullable: true, length: 100 })
  name_3: string | null;

  @Column("character varying", { name: "name_4", nullable: true, length: 100 })
  name_4: string | null;

  @Column("character varying", {
    name: "country_no_1",
    nullable: true,
    length: 10,
  })
  countryNo_1: string | null;

  @Column("character varying", {
    name: "phone_no_1",
    nullable: true,
    length: 20,
  })
  phoneNo_1: string | null;

  @Column("character varying", {
    name: "country_no_2",
    nullable: true,
    length: 10,
  })
  countryNo_2: string | null;

  @Column("character varying", {
    name: "phone_no_2",
    nullable: true,
    length: 20,
  })
  phoneNo_2: string | null;

  @Column("character varying", {
    name: "country_code",
    nullable: true,
    length: 30,
  })
  countryCode: string | null;

  @Column("character varying", { name: "zip_code", nullable: true, length: 20 })
  zipCode: string | null;

  @Column("character varying", {
    name: "address_1",
    nullable: true,
    length: 200,
  })
  address_1: string | null;

  @Column("character varying", {
    name: "address_2",
    nullable: true,
    length: 200,
  })
  address_2: string | null;

  @Column("character varying", {
    name: "address_3",
    nullable: true,
    length: 200,
  })
  address_3: string | null;

  @Column("character varying", {
    name: "address_4",
    nullable: true,
    length: 200,
  })
  address_4: string | null;

  @Column("character varying", { name: "email_address", length: 200 })
  emailAddress: string;

  @Column("character varying", {
    name: "birthday_year",
    nullable: true,
    length: 4,
  })
  birthdayYear: string | null;

  @Column("character varying", {
    name: "birthday_month",
    nullable: true,
    length: 2,
  })
  birthdayMonth: string | null;

  @Column("character varying", {
    name: "birthday_day",
    nullable: true,
    length: 2,
  })
  birthdayDay: string | null;

  @Column("character varying", {
    name: "gender_code",
    length: 30,
    default: () => "'Nothing'",
  })
  genderCode: Gender;

  @Column("character varying", {
    name: "member_status",
    length: 30,
    default: () => "'Normal'",
  })
  memberStatus: MemberStatus;

  @Column("character varying", { name: "nickname", length: 50 })
  nickname: string;

  @Column("timestamp with time zone", {
    name: "final_login_dt",
    nullable: true,
  })
  finalLoginDt: Date | null;

  @Column("timestamp with time zone", { name: "signup_dt" })
  signupDt: Date;

  @Column("character varying", {
    name: "account_lock_yn",
    length: 1,
    default: () => "'N'",
  })
  accountLockYn: YN;

  @Column("timestamp with time zone", {
    name: "account_lock_dt",
    nullable: true,
  })
  accountLockDt: Date | null;

  @Column("timestamp with time zone", {
    name: "account_rest_dt",
    nullable: true,
  })
  accountRestDt: Date | null;

  @Column("timestamp with time zone", {
    name: "closed_account_dt",
    nullable: true,
  })
  closedAccountDt: Date | null;

  @Column("character varying", {
    name: "closed_account_reason",
    nullable: true,
    length: 200,
  })
  closedAccountReason: string | null;

  @Column("character varying", {
    name: "pw_reset_request_yn",
    length: 1,
    default: () => "'N'",
  })
  pwResetRequestYn: YN;

  @Column("character varying", { name: "pw_verify_key", length: 200 })
  pwVerifyKey: string;

  @Column("timestamp with time zone", {
    name: "pw_verify_key_send_dt",
    nullable: true,
  })
  pwVerifyKeySendDt: Date | null;

  @Column("timestamp with time zone", {
    name: "pw_verify_expect_dt",
    nullable: true,
  })
  pwVerifyExpectDt: Date | null;

  @Column("timestamp with time zone", {
    name: "pw_verify_complete_dt",
    nullable: true,
  })
  pwVerifyCompleteDt: Date | null;

  @Column("timestamp with time zone", {
    name: "final_pw_change_dt",
    nullable: true,
  })
  finalPwChangeDt: Date | null;

  @Column("numeric", {
    name: "pw_verify_failure_cnt",
    precision: 10,
    scale: 0,
    default: () => "0",
  })
  pwVerifyFailureCnt: string;

  @Column("character varying", {
    name: "mail_prohibit_yn",
    length: 1,
    default: () => "'N'",
  })
  mailProhibitYn: YN;
}
