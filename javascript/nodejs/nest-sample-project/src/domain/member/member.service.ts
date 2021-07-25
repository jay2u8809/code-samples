import {Injectable} from '@nestjs/common';
import {Member} from "../../entities/member/member";
import {InjectRepository} from "@nestjs/typeorm";
import {Repository} from "typeorm";
import {MemberJoinDto, saveMember} from "./dto/member.join.dto";
import {isEmpty} from "../../common/common.utils";

@Injectable()
export class MemberService {
  constructor(
    @InjectRepository(Member) private memberRepository: Repository<Member>
  ) {}

  /**
   * Get One Member Info By User ID (All Info)
   * @param memberId
   */
  findById(memberId: string) : Promise<Member> {

    console.log(`Find By Member Id ${memberId}`);
    const result: Promise<Member>
      = this.memberRepository
            .findOne({
              where: {memberId}
            });
    return result;
  }

  /**
   * Register Member
   * @param memberJoinDto
   */
   async saveMember(memberJoinDto: MemberJoinDto): Promise<bigint> {
    const memberId = memberJoinDto.memberId;
    const existId
      = await this.memberRepository
                  .findOne({
                    select: ['memberId'],
                    where: { memberId }
                  });
    if (!isEmpty(existId)) {
      return null;
    }

    const savedMember
      = await this.memberRepository
                  .save(saveMember(memberJoinDto));
    return savedMember.memberSn;
  }
}
