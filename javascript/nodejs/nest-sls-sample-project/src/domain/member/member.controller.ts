import { Body, Controller, Delete, Get, HttpStatus, Param, Patch, Post, Put } from '@nestjs/common';
import { MemberService } from './member.service';
import { MemberJoinRequestDto } from './dto/member.join.request.dto';
import { Member } from '../../entities/member/member';
import { ApiOperation } from '@nestjs/swagger';

const TAG = 'MEMBER_CONTROLLER';

@Controller('/api/v1/individual/member')
export class MemberController {
  constructor(private readonly memberService: MemberService) {}

  @ApiOperation({ summary: 'Register One Member Info' })
  @Post('/register/')
  async registerMember(
    @Body() data: MemberJoinRequestDto,
  ): Promise<HttpStatus> {
    if (!data) {
      console.log(TAG, `REGISTER NEW MEMBER INFO IS EMPTY`);
      return HttpStatus.BAD_REQUEST;
    }
    const result: bigint = await this.memberService.create(data);
    return result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @ApiOperation({ summary: 'Get One Member Info By memberSn' })
  @Get('/get/:memberSn')
  async getMemberBySn(
    @Param('memberSn') memberSn: bigint,
  ): Promise<Member | null> {
    console.log(TAG, `Get Member Info memberSn : ${memberSn}`);
    return await this.memberService.get(memberSn);
  }

  @ApiOperation({ summary: 'Get All Members Info' })
  @Get('/all/')
  async getAllMembers(): Promise<Member[] | null> {
    console.log(TAG, `Get All Members`);
    return await this.memberService.getAll();
  }

  @ApiOperation({ summary: 'Get One Member Info By memberId' })
  @Get('/id/:memberId')
  async getMemberById(
    @Param('memberId') memberId: string,
  ): Promise<Member | null> {
    console.log(TAG, `Get Member Info memberId : ${memberId}`);
    return await this.memberService.getById(memberId);
  }

  @ApiOperation({ summary: 'Get One Member Info By member email' })
  @Get('/email/:email')
  async getMemberByEmail(
    @Param('email') email: string,
  ): Promise<Member[] | null> {
    console.log(TAG, `Get Member Info memberId By email: ${email}`);
    return await this.memberService.getByEmail(email);
  }

  @ApiOperation({ summary: 'Get One Member Info By member nickname' })
  @Get('/nickName/:nickName')
  async getMemberByNickName(
    @Param('nickName') nickName: string,
  ): Promise<Member[] | null> {
    console.log(TAG, `Get Member Info memberId : ${nickName}`);
    return await this.memberService.getByNickName(nickName);
  }

  @ApiOperation({ summary: 'Get Normal Status Members Info' })
  @Get('/all/normal/')
  async getNormalMembers(): Promise<Member[] | null> {
    console.log(TAG, `Get Normal Status Members Info`);
    return await this.memberService.getNormalMembers();
  }

  @ApiOperation({ summary: 'Update Members Info' })
  @Put('/updated/:memberSn')
  async updatedMember(
    @Param('memberSn') memberSn: bigint,
    @Body() data: MemberJoinRequestDto,
  ): Promise<HttpStatus> {
    console.log(TAG, `Update Members Info memberSn : ${memberSn}`);
    return (await this.memberService.update(data))
      ? HttpStatus.OK
      : HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @ApiOperation({ summary: 'Delete One Member Info By memberSn' })
  @Delete('/delete/:memberSn')
  async deleteMemberBySn(
    @Param('memberSn') memberSn: bigint,
  ): Promise<boolean> {
    console.log(TAG, `Delete Member Info memberSn : ${memberSn}`);
    return await this.memberService.delete(memberSn);
  }
}
