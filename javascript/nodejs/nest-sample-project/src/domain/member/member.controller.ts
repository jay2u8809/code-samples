import {Body, Controller, ForbiddenException, HttpStatus, NotFoundException, Post} from '@nestjs/common';
import {MemberService} from "./member.service";
import {MemberJoinDto} from "./dto/member.join.dto";

@Controller('/api/v1/individual/member')
export class MemberController {
  constructor(private readonly memberService: MemberService) {
  }

  @Post('/register/')
  async registerMember(@Body() data: MemberJoinDto): Promise<string> {
    // const member = this.memberService.findByEmail(data.email);
    // if (!isEmpty(member)) {
    //   throw new NotFoundException();
    // }

    if (!data) {
      console.log(`REGISTER NEW MEMBER INFO IS EMPTY`);
      return `Fail`;
    }

    console.log(`REGISTER NEW MEMBER ID : ${data.memberId}`);
    const savedMemberSn = await this.memberService.saveMember(data);

    if (!savedMemberSn) {
      console.log(`Fail to REGISTER NEW MEMBER ID : ${data.memberId}`);
      return `Fail`;
    }

    return `Success MemberSn : ${savedMemberSn}`;
  }
}
