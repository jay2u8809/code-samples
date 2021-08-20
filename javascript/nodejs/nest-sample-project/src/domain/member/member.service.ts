import { Injectable } from '@nestjs/common';
import { Member } from '../../entities/member/member';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import {
  MemberJoinRequestDto,
  saveMember,
} from './dto/member.join.request.dto';
import { MemberStatus } from '../../common/code/MemberStatus';

@Injectable()
export class MemberService {
  constructor(
    @InjectRepository(Member) private memberRepository: Repository<Member>,
  ) {}

  /**
   * Get One Member Info By Member Sn (All Info)
   * @param memberSn
   */
  findMemberBySn(memberSn: bigint): Promise<Member> {
    return this.memberRepository.findOne({
      where: { memberSn },
    });
    this.memberRepository.findOneOrFail();
  }

  /**
   * Get One Member Info By User ID (All Info)
   * @param memberId
   */
  findMemberById(memberId: string): Promise<Member> {
    const result: Promise<Member> = this.memberRepository.findOne({
      where: { memberId },
    });

    // TODO then 구문 Test 필요
    return result.then((value) => {
      if (!value) {
        return null;
      }
    });
  }

  /**
   * Get All Members Info
   */
  findAllMembers(): Promise<Member[]> {
    this.memberRepository
      .count({
        where: { memberStatus: MemberStatus.Normal },
      })
      .then((value) => console.log(`CNT : ${value}`));

    return this.memberRepository.find({
      select: ['memberId', 'memberSn'],
      where: { memberStatus: MemberStatus.Normal },
      order: { memberSn: 'ASC' },
    });
  }

  /**
   * Register Member
   * @param memberJoinDto
   */
  async saveMember(joinRequestDto: MemberJoinRequestDto): Promise<bigint> {
    const savedMember = await this.memberRepository.save(
      saveMember(joinRequestDto),
    );
    return savedMember.memberSn;
  }

  /**
   * Check Exist Member Info
   * @param memberId
   */
  checkExistMemberById(memberId: string): Promise<boolean> {
    console.log(`Check Exist Member Info By User ID : ${memberId}`);

    const cnt: Promise<number> = this.memberRepository.count({
      select: ['memberId'],
      where: { memberId },
    });

    return cnt.then((value) => value <= 0);
  }
}
