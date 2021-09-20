import { Injectable, NotFoundException } from '@nestjs/common';
import { Member } from '../../entities/member/member';
import { InjectRepository } from '@nestjs/typeorm';
import {
  MemberJoinRequestDto,
  saveMember,
} from './dto/member.join.request.dto';
import { MemberStatus } from '../../common/code/MemberStatus';
import { MemberRepository } from './member.repository';
import { MemberInterface } from 'src/db/sql/domain/member/member.interface';
import { getConnection, Connection, getManager } from 'typeorm';
import { EntityManager } from 'typeorm/entity-manager/EntityManager';

const TAG = 'MEMBER_SERVICE';

@Injectable()
export class MemberService implements MemberInterface {
  private readonly conn: Connection;
  private readonly em: EntityManager;

  constructor(
    @InjectRepository(Member) private memberRepository: MemberRepository,
  ) {
    this.conn = getConnection();
    this.em = getManager();
  }

  /**
   * Register Member
   * @param param
   */
  async create(param: MemberJoinRequestDto): Promise<bigint | null> {
    if (!param) return null;

    const isExist: Promise<boolean> = this.isExistById(param.memberId);
    if (!isExist) {
      throw new NotFoundException(
        `Already Exist Member ID :  ${param.memberId}`,
      );
    }

    return await this.memberRepository
      .save(saveMember(param))
      .then((data) => {
        return data.memberSn;
      }).catch((err) => {
        console.log(TAG, `Fail to regiser member: ${JSON.stringify(err)}`);
        return null;
      });
  }

  /**
   * Get One Member Info By MemberSn (All Info)
   * @param memberSn
   */
  async get(memberSn: bigint): Promise<Member> {
    if (!memberSn) return null;

    const sn = Number(memberSn);

    return await this.memberRepository.findOne(sn)
      .catch((err) => {
      console.log(TAG, `Fail to fetch member data : ${JSON.stringify(err)}`);
      return null;
    });
  }

  /**
   * Get All Members
   * @param param
   */
  async getAll(param?: any): Promise<Member[] | null> {
    const query = 'SELECT * FROM dev_schema.MEMBER';
    return await this.memberRepository.query(query)
      .catch((err) => {
      console.log(TAG, `Fail to fetch member data : ${JSON.stringify(err)}`);
      return null;
    });
  }

  /*
   * Get One Member Info By User ID (All Info)
   * @param memberId
   */
  async getById(memberId: string): Promise<Member | null> {
    if (!memberId) return null;

    return await this.memberRepository
      .findOne({
        where: {
          memberId: memberId,
        },
      }).catch((err) => {
        console.log(TAG, `Fail to fetch member data by memberId: ${JSON.stringify(err)}`);
        return null;
      });
  }

  async getByEmail(email: string): Promise<Member[] | null> {
    if (!email) return null;

    return await this.memberRepository
      .find({
        select: ['memberSn', 'memberId'],
        where: {
          emailAddress: email,
        },
        order: {
          memberSn: 'ASC',
        },
      }).catch((err) => {
        console.log(TAG, `Fail to fetch member data by member email address: ${JSON.stringify(err)}`);
        return null;
      });
  }

  async getByNickName(nickName: string): Promise<Member[] | null> {
    if (!nickName) return null;

    return await this.memberRepository
      .createQueryBuilder()
      .select('member')
      .from(Member, 'member')
      .where('member.nickName like :nickName', {
        nickName: nickName,
      })
      .orderBy('member.memberSn', 'DESC')
      .getMany()
      .catch((err) => {
        console.log(TAG, `Fail to fetch member data by member nickname: ${JSON.stringify(err,)}`);
        return null;
      });
  }

  /**
   * Get All Members Info
   */
  async getNormalMembers(): Promise<Member[] | null> {
    return await this.memberRepository
      .find({
        select: ['memberId', 'memberSn'],
        where: {
          memberStatus: MemberStatus.Normal,
        },
        order: {
          memberSn: 'ASC',
        },
      }).catch((err) => {
        console.log(TAG, `Fail to fetch member data by member nickname: ${JSON.stringify(err)}`);
        return null;
      });
  }

  /**
   * Update Member Info
   * @param param
   * @param table
   */
  async update(
    param: MemberJoinRequestDto,
    table?: string,
  ): Promise<Member | null> {
    if (!param) return null;

    const member: Member = await this.get(param.memberSn);
    if (!member)
      throw new NotFoundException(`Not Found Member Info : ${param.memberSn}`);

    return await this.memberRepository
      .save(saveMember(param))
      .then((data) => {
        return data;
      }).catch((err) => {
        console.log(TAG, `Fail to update member data : ${JSON.stringify(err)}`);
        return null;
      });
  }

  /**
   * Delete Member Info By memberSn
   * @param memberSn
   */
  async delete(memberSn: bigint): Promise<boolean | null> {
    if (!memberSn) return false;

    const isExist = await this.isExistBySn(memberSn);
    if (!isExist)
      throw new NotFoundException(`Not Found Member Info : ${memberSn}`);

    return await this.memberRepository
      .delete({
        memberSn: memberSn,
      })
      .then(() => {
        return true;
      }).catch((err) => {
        console.log(TAG, `Fail to delete member data: ${JSON.stringify(err)}`);
        return false;
      });
  }

  /**
   * Check Exist Member Info By memberSn
   * @param memberSn
   */
  async isExistBySn(memberSn: bigint): Promise<boolean> {
    if (!memberSn) return false;

    const sn: number = Number(memberSn);
    const param: any = {
      memberSn: sn,
    };

    return await this.isExist(param);
  }

  /**
   * Check Exist Member Info By memberId
   * @param memberId
   */
  async isExistById(memberId: string): Promise<boolean> {
    if (!memberId) return false;

    const param: any = {
      memberId: memberId,
    };

    return await this.isExist(param);
  }

  // ==== private ====
  private async isExist(param: any): Promise<boolean> {
    return await this.memberRepository
      .count({
        where: param,
      })
      .then((data) => {
        console.log(TAG, `Exist Member Count : ${data}`);
        return data > 0;
      }).catch((err) => {
        console.log(TAG, `Fail to check exist member data : ${JSON.stringify(err)}`);
        return null;
      });
  }
}
