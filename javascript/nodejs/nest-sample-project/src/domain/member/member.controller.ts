import {Body, Controller, ForbiddenException, NotFoundException, Post} from '@nestjs/common';
import {MemberService} from "./member.service";
import {MemberJoinDto} from "./dto/member.join.dto";
import {isEmpty} from "../../common/common.utils";

@Controller('members')
export class MemberController {
  constructor(private readonly memberService: MemberService) {
  }

  @Post('join')
  async joinMember(@Body() data: MemberJoinDto): Promise<string> {
    // const member = this.memberService.findByEmail(data.email);
    // if (!isEmpty(member)) {
    //   throw new NotFoundException();
    // }

    const result = await this.memberService.joinMember(data);
    if (result) {
      return 'ok';
    } else {
      throw new ForbiddenException();
    }
  }
}
