import {
  Injectable,
  InternalServerErrorException,
  NotFoundException,
} from '@nestjs/common';
import { Member } from '../../entities/member/member';
import { InjectRepository } from '@nestjs/typeorm';
import {
  MemberJoinRequestDto,
  saveMember,
} from './dto/member.join.request.dto';
import { MemberStatus } from '../../common/code/member-status';
import { MemberRepository } from './member.repository';
import { getConnection, Connection, getManager, QueryRunner } from 'typeorm';
import { EntityManager } from 'typeorm/entity-manager/EntityManager';
import { MemberInterface } from '../../db/common/domain/member/member.interface';

const TAG = 'MEMBER_SQL_SERVICE';

@Injectable()
export class MemberSqlService implements MemberInterface {
  constructor(
    // @InjectRepository(Member) private em: MemberRepository,
    // private readonly em: Connection,
    // private readonly em: EntityManager,
  ) {
    // this.em = getConnection();
    // this.em = getManager();
  }

  /**
   * Register Member
   * @param param
   */
  async create(param: MemberJoinRequestDto): Promise<string> {
    // 0. check param
    if (!param)
      return null;
    // 1. check exist
    const isExist: boolean = await this.isExistById(param.memberId);
    if (isExist)
      throw new NotFoundException(`Already Exist Member ID:  ${param.memberId}`);

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();
    await em.startTransaction();

    return em.manager
      .save(Member, saveMember(param))
      .then(async (data) => {
        await em.commitTransaction();
        return String(data.memberSn);
      }).catch(async (err) => {
        await em.rollbackTransaction();
        console.error(TAG, `Fail to register member`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  /**
   * Get One Member Info By MemberSn (All Info)
   * @param memberSn
   */
  async get(memberSn: any): Promise<Member> {
    if (!memberSn)
      return null;

    const sn = Number(memberSn);

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager
      .findOne(Member, sn)
      .then((data) => {
        return data;
      }).catch((err) => {
        console.error(TAG, `Fail to fetch member data`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  /**
   * Get All Members
   * @param param
   */
  async getAll(param?: any): Promise<Member[] | null> {
    const query = 'SELECT * FROM dev_schema.MEMBER';

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager
      .query(query)
      .then((data) => {
        return data;
      }).catch((err) => {
        console.error(TAG, `Fail to fetch member data`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  /*
   * Get One Member Info By User ID (All Info)
   * @param memberId
   */
  async getById(memberId: string): Promise<Member | null> {
    if (!memberId)
      return null;

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager
      .findOne(Member, {
        where: {
          memberId: memberId,
        },
      }).catch((err) => {
        console.error(TAG, `Fail to fetch member data by memberId`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  async getByEmail(email: string): Promise<Member[] | null> {
    if (!email)
      return null;

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager
      .find(Member, {
        select: ['memberSn', 'memberId'],
        where: {
          emailAddress: email,
        },
        order: {
          memberSn: 'ASC',
        },
      }).catch((err) => {
        console.error(TAG, `Fail to fetch member data by member email address`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  async getByNickName(nickName: string): Promise<Member[] | null> {
    if (!nickName)
      return null;

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager.createQueryBuilder()
      .select('member')
      .from(Member, 'member')
      .where('member.nickName like :nickName', {
        nickName: nickName,
      }).orderBy('member.memberSn', 'DESC')
      .getMany()
      .catch((err) => {
        console.error(TAG, `Fail to fetch member data by member nickname`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  /**
   * Get All Members Info
   */
  async getNormalMembers(): Promise<Member[] | null> {
    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager
      .find(Member, {
        select: ['memberId', 'memberSn'],
        where: {
          memberStatus: MemberStatus.Normal,
        },
        order: {
          memberSn: 'ASC',
        },
      }).catch((err) => {
        console.error(TAG, `Fail to fetch member data by member nickname`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  /**
   * Update Member Info
   * @param param
   * @param table
   */
  async update(param: MemberJoinRequestDto, table?: string): Promise<Member | null> {
    if (!param)
      return null;

    const member: Member = await this.get(param.memberSn);
    if (!member)
      throw new NotFoundException(`Not Found Member Info : ${param.memberSn}`);

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();
    await em.startTransaction();

    try {
      const saved = await em.manager.save(Member, saveMember(param));
      await em.commitTransaction();
      return saved;
    } catch (err) {
      await em.rollbackTransaction();
      console.error(TAG, `Fail to update member data`);
      throw new InternalServerErrorException(err.message);
    } finally {
      await em.release();
    }
  }

  /**
   * Delete Member Info By memberSn
   * @param memberSn
   */
  async delete(memberSn: bigint): Promise<boolean | null> {
    if (!memberSn)
      return false;

    const isExist = await this.isExistBySn(memberSn);
    if (!isExist)
      throw new NotFoundException(`Not Found Member Info : ${memberSn}`);

    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();
    await em.startTransaction();

    return em.manager
      .delete(Member, {
        memberSn: memberSn,
      }).then(async () => {
        await em.commitTransaction();
        return true;
      }).catch(async (err) => {
        await em.rollbackTransaction();
        console.error(TAG, `Fail to delete member data`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  async isExist(param: any): Promise<boolean> {
    const em: QueryRunner = getConnection().createQueryRunner();
    await em.connect();

    return em.manager
      .count(Member, {
        where: param,
      }).then((data) => {
        console.log(TAG, `Exist Member Count : ${data}`);
        return data > 0;
      }).catch((err) => {
        console.error(TAG, `Fail to check exist member data`);
        throw new InternalServerErrorException(err.message);
      }).finally(async () => {
        await em.release();
      });
  }

  // ==== private ====

  /**
   * Check Exist Member Info By memberSn
   * @param memberSn
   */
  private async isExistBySn(memberSn: bigint): Promise<boolean> {
    // 0. check param
    if (!memberSn)
      return false;
    // 1. make query
    const sn = Number(memberSn);
    const param: any = {
      memberSn: sn,
    };
    // 2. check exist
    return this.isExist(param);
  }

  /**
   * Check Exist Member Info By memberId
   * @param memberId
   */
  private async isExistById(memberId: string): Promise<boolean> {
    // 0. check param
    if (!memberId)
      return false;
    // 1. make query
    const param: any = {
      memberId: memberId,
    };
    // 2. check exist
    return this.isExist(param);
  }
}
