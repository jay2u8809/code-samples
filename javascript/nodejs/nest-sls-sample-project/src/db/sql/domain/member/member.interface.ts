import { Member } from 'src/entities/member/member';
import { DatabaseInterface } from '../../database.interface';

export interface MemberInterface extends DatabaseInterface {
  getById(memberId: string): Promise<Member | null>;

  getByEmail(email: string): Promise<Member[] | null>;

  getByNickName(nickName: string): Promise<Member[] | null>;

  getNormalMembers(): Promise<Member[] | null>;

  isExistBySn(memberSn: bigint): Promise<boolean>;

  isExistById(memberId: string): Promise<boolean>;
}
