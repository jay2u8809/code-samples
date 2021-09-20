import { Member } from 'src/entities/member/member';
import { DbBaseInterface } from '../../db.base.interface';

export interface MemberInterface extends DbBaseInterface {
  getById(memberId: string): Promise<Member | null>;

  getByEmail(email: string): Promise<Member[] | null>;

  getByNickName(nickName: string): Promise<Member[] | null>;

  getNormalMembers(): Promise<Member[] | null>;

  isExist(param: any): Promise<boolean>;
}
