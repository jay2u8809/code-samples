import { Injectable, NotFoundException } from '@nestjs/common';
import { compareHash } from '../../common/common.utils';
import { MemberSqlService } from '../member/member.sql.service';
import { Member } from '../../entities/member/member';

const TAG = 'AUTH_SERVICE';

@Injectable()
export class AuthService {
  constructor(private readonly memberService: MemberSqlService) {
  }

  async authMember(memberId: string, pw: string): Promise<boolean> {
    // 0. check param
    if (!(memberId && pw))
      return false;
    // 1. get member data
    const member: Member = await this.memberService.getById(memberId);
    if (!member)
      throw new NotFoundException(`Member Data is not founded: ${memberId}`);
    // 2. compare hash
    const result: boolean = compareHash(pw, member.pwVerifyKey);
    console.log(TAG, `Result: ${result}`);
    return result;
  }
}
