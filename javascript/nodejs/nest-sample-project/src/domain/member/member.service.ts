import {Injectable} from '@nestjs/common';
import {Member} from "../../entities/member/member";
import {InjectRepository} from "@nestjs/typeorm";
import {Repository} from "typeorm";
import {isEmpty} from "../../common/common.utils";
import {MemberJoinDto} from "./dto/member.join.dto";
import {MemberStatus} from "../../common/code/MemberStatus";

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

  async saveMember(memberJoinDto: MemberJoinDto): Promise<bigint> {
    // const hashedPassword = await bcrypt.hash(password, 12);
    // const member = await this.memberRepository.findOne({ where: { email } });
    // if (!isEmpty(member)) {
    //   return null;
    // }

    const member: Member = new Member();
    member.memberId = memberJoinDto.memberId;
    member.memberPw = memberJoinDto.memberPw;
    member.emailAddress = memberJoinDto.memberEmail;
    member.nickname = memberJoinDto.nickname;
    member.signupDt = new Date();
    member.pwVerifyKey = '';
    member.memberStatus = MemberStatus.Normal;
    member.createdDt = new Date();
    member.createdBy = 'admin';
    member.modifiedDt = new Date();
    member.modifiedBy = 'admin';

    const savedMember = await this.memberRepository.save(member);
    if (!isEmpty(savedMember)) {
      console.log(`SAVED MEMBER SN : ${savedMember.memberSn}`);
    }
    return savedMember.memberSn;
  }
}
