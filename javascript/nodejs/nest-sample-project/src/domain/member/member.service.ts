import {Injectable} from '@nestjs/common';
import {Member} from "../../entities/member/member";
import {InjectRepository} from "@nestjs/typeorm";
import {Repository} from "typeorm";
import {MemberJoinDto, saveMember} from "./dto/member.join.dto";

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
    // const member = await this.memberRepository.findOne({ where: { email } });
    // if (!isEmpty(member)) {
    //   return null;
    // }

    const savedMember = await this.memberRepository.save(saveMember(memberJoinDto));
    return savedMember.memberSn;
  }
}
