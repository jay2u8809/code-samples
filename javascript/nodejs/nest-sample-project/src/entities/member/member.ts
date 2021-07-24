import { Column, Entity, Index, PrimaryGeneratedColumn } from "typeorm";
import {BaseEntity} from "../base.entity";

@Index("member_pkey", ["memberSn"], { unique: true })
@Entity("member", { schema: "dev_schema" })
export class Member extends BaseEntity {
  @PrimaryGeneratedColumn({ type: "bigint", name: "member_sn" })
  memberSn: string;

  @Column("character varying", { name: "member_id", length: 300 })
  memberId: string;

  @Column("character varying", { name: "member_password", length: 200 })
  memberPassword: string;

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

  @Column("character varying", { name: "email_address", length: 200 })
  emailAddress: string;

  @Column("timestamp with time zone", { name: "member_signup_dt" })
  memberSignupDt: Date;

  @Column("character varying", {
    name: "password_reset_request_yn",
    length: 1,
    default: () => "'N'",
  })
  passwordResetRequestYn: string;

  @Column("character varying", {
    name: "password_verif_media_code",
    nullable: true,
    length: 30,
  })
  passwordVerifMediaCode: string | null;

  @Column("character varying", { name: "password_verif_key", length: 200 })
  passwordVerifKey: string;

  @Column("timestamp with time zone", {
    name: "password_verif_send_dt",
    nullable: true,
  })
  passwordVerifSendDt: Date | null;

  @Column("timestamp with time zone", {
    name: "password_verif_complete_exp_dt",
    nullable: true,
  })
  passwordVerifCompleteExpDt: Date | null;

  @Column("timestamp with time zone", {
    name: "password_verif_complete_dt",
    nullable: true,
  })
  passwordVerifCompleteDt: Date | null;

  @Column("timestamp with time zone", {
    name: "final_password_change_dt",
    nullable: true,
  })
  finalPasswordChangeDt: Date | null;

  @Column("character varying", {
    name: "member_status_code",
    length: 30,
    default: () => "'Normal'",
  })
  memberStatusCode: string;

  @Column("timestamp with time zone", {
    name: "final_login_dt",
    nullable: true,
  })
  finalLoginDt: Date | null;

  @Column("numeric", {
    name: "password_verif_failure_cnt",
    precision: 10,
    scale: 0,
    default: () => "0",
  })
  passwordVerifFailureCnt: string;

  @Column("character varying", {
    name: "ac_lock_yn",
    length: 1,
    default: () => "'N'",
  })
  acLockYn: string;

  @Column("timestamp with time zone", { name: "ac_lock_dt", nullable: true })
  acLockDt: Date | null;

  @Column("character varying", { name: "nickname", nullable: true, length: 50 })
  nickname: string | null;

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

  @Column("character varying", { name: "dob_year", nullable: true, length: 4 })
  dobYear: string | null;

  @Column("character varying", { name: "dob_month", nullable: true, length: 2 })
  dobMonth: string | null;

  @Column("character varying", { name: "dob_day", nullable: true, length: 2 })
  dobDay: string | null;

  @Column("character varying", {
    name: "gender_code",
    length: 30,
    default: () => "'NoResponse'",
  })
  genderCode: string;

  @Column("character varying", {
    name: "mail_prohibit_yn",
    length: 1,
    default: () => "'N'",
  })
  mailProhibitYn: string;

  @Column("character varying", {
    name: "admail_prohibit_yn",
    length: 1,
    default: () => "'N'",
  })
  admailProhibitYn: string;

  @Column("character varying", {
    name: "sms_prohibit_yn",
    length: 1,
    default: () => "'N'",
  })
  smsProhibitYn: string;

  @Column("timestamp with time zone", {
    name: "dm_prohibit_update_dm",
    nullable: true,
  })
  dmProhibitUpdateDm: Date | null;
}
