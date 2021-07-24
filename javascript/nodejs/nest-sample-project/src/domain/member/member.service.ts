import { Injectable } from '@nestjs/common';
import {Member} from "../../entities/member/member";
import {InjectRepository} from "@nestjs/typeorm";
import {Repository} from "typeorm";
import bcrypt from 'bcrypt';
import {isEmpty} from "../../common/common.utils";
import {MemberJoinDto} from "./dto/member.join.dto";

@Injectable()
export class MemberService {
  constructor(
    @InjectRepository(Member) private memberRepository: Repository<Member>
  ) {}

  async findByEmail(email: string) {
    // this.memberRepository.createQueryBuilder('member')
    //   .where()
    //   .
    return this.memberRepository.findOne({
      where: { email },
      select: ['memberId', 'emailAddress'],
    });
  }

  async joinMember(joinMemberInfo: MemberJoinDto): Promise<Member> {
    // const hashedPassword = await bcrypt.hash(password, 12);
    // const member = await this.memberRepository.findOne({ where: { email } });
    // if (!isEmpty(member)) {
    //   return null;
    // }

    const member: Member = new Member();
    member.memberId = joinMemberInfo.memberId;
    member.memberPassword = joinMemberInfo.memberPassword;
    member.emailAddress = joinMemberInfo.email;
    member.nickname = joinMemberInfo.nickname;
    member.memberSignupDt = new Date();
    member.passwordVerifKey = '';
    member.memberStatusCode = 'Normal';
    member.createdDt = new Date();
    member.createdBy = 'admin';
    member.updatedDt = new Date();
    member.updatedBy = 'admin';

    const returned = await this.memberRepository.save(member);
    if (!isEmpty(returned)) {
      console.log(`SAVED MEMBER SN : ${returned.memberSn}`);
    }
    return returned;
  }
}
