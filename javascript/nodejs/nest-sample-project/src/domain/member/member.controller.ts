import {Body, Controller, Get, NotFoundException, Param, Post} from '@nestjs/common';
import {MemberService} from "./member.service";
import {MemberJoinRequestDto} from "./dto/member.join.request.dto";
import {Member} from "../../entities/member/member";
import {ApiOperation} from "@nestjs/swagger";

@Controller('/api/v1/individual/member')
export class MemberController {
  constructor(private readonly memberService: MemberService) {
  }

  @ApiOperation({ summary: 'Get All Members Info'})
  @Get('/all')
  async getAllMembers() : Promise<void | Member[]>{
    return await this.memberService.findAllMembers();
  }

  @ApiOperation({ summary: 'Get One Member Info'})
  @Get('/:memberId')
  async getMember(@Param('memberId') memId) : Promise<void | Member>{
    console.log(`Get Member Info memberId : ${memId}`);
    return await this.memberService.findMemberById(memId);
  }

  @ApiOperation({ summary: 'Register One Member Info'})
  @Post('/register/')
  async registerMember(@Body() data: MemberJoinRequestDto): Promise<string> {

    if (!data) {
      console.log(`REGISTER NEW MEMBER INFO IS EMPTY`);
      return `Fail`;
    }

    // Check Exist Member Info
    const isExist
      = await this.memberService
                  .checkExistMemberById(data.memberId);
    if (!isExist) {
      throw new NotFoundException(`Already Exist Member ID :  ${data.memberId}`);
    }

    console.log(`REGISTER NEW MEMBER ID : ${data.memberId}`);
    // Save New Member Info
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
