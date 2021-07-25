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

  /**
   * Get One Member Info By User ID (All Info)
   * @param memberId
   */
  findById(memberId: string) : Promise<Member> {

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

    const savedMember
      = await this.memberRepository
                  .save(saveMember(memberJoinDto));
    return savedMember.memberSn;
  }

  /**
   * Check Exist Member Info
   * @param memberId
   */
  checkExistMemberById(memberId: string): Promise<boolean> {

    console.log(`Check Exist Member Info By User ID : ${memberId}`);

    const cnt: Promise<number>
      = this.memberRepository
            .count({
              select: ['memberId'],
              where: { memberId }
            });

    return cnt.then(value => value <= 0);
  }
}
