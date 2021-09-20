import { EntityRepository, Repository } from 'typeorm';
import { Member } from '../../entities/member/member';

@EntityRepository(Member)
export class MemberRepository extends Repository<Member> {}
