import {Body, Controller, ForbiddenException, Get, HttpStatus, NotFoundException, Param, Post} from '@nestjs/common';
import {MemberService} from "./member.service";
import {MemberJoinDto} from "./dto/member.join.dto";
import {isEmpty} from "../../common/common.utils";
import {Member} from "../../entities/member/member";
import {ApiOperation} from "@nestjs/swagger";

@Controller('/api/v1/individual/member')
export class MemberController {
  constructor(private readonly memberService: MemberService) {
  }

  @ApiOperation({ summary: 'Get One Member Info'})
  @Get('/:memberId')
  async getMember(@Param('memberId') memId) : Promise<void | Member>{
    console.log(`Get Member Info memberId : ${memId}`);
    return await this.memberService.findById(memId);
  }

  @ApiOperation({ summary: 'Register One Member Info'})
  @Post('/register/')
  async registerMember(@Body() data: MemberJoinDto): Promise<string> {

    if (!data) {
      console.log(`REGISTER NEW MEMBER INFO IS EMPTY`);
      return `Fail`;
    }

    const member
      = await this.memberService
                  .findById(data.memberId);
    if (!isEmpty(member)) {
      throw new NotFoundException(`Already Exist Member ID :  ${data.memberId}`);
    }

    console.log(`REGISTER NEW MEMBER ID : ${data.memberId}`);
    const savedMemberSn
      = await this.memberService
                  .saveMember(data);

    if (!savedMemberSn) {
      console.log(`Fail to REGISTER NEW MEMBER ID : ${data.memberId}`);
      return `Fail`;
    }

    return `Success MemberSn : ${savedMemberSn}`;
  }
}
