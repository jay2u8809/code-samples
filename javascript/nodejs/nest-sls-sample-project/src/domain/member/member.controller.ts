import {
  Body,
  Controller,
  Delete,
  Get,
  HttpStatus,
  Param,
  Patch,
  Post,
  Put,
} from '@nestjs/common';
import { MemberSqlService } from './member.sql.service';
import { MemberJoinRequestDto } from './dto/member.join.request.dto';
import { Member } from '../../entities/member/member';
import { ApiOperation } from '@nestjs/swagger';
import { MemberNosqlService } from './member.nosql.service';

const TAG = 'MEMBER_CONTROLLER';

@Controller('/api/v1/individual/member')
export class MemberController {
  constructor(private readonly memberService: MemberNosqlService) {}

  @ApiOperation({ summary: 'Register One Member Info' })
  @Post('/register/')
  async registerMember(
    @Body() data: MemberJoinRequestDto,
  ): Promise<HttpStatus> {
    if (!data) {
      console.log(TAG, `REGISTER NEW MEMBER INFO IS EMPTY`);
      return HttpStatus.BAD_REQUEST;
    }
    const result: bigint | string = await this.memberService.create(data);
    return result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @ApiOperation({ summary: 'Get One Member Info By primary key' })
  @Get('/get/:pkey')
  async getMember(
    @Param('pkey') primaryKey: any,
  ): Promise<Member | null> {
    console.log(TAG, `Get Member Info primaryKey : ${primaryKey}`);
    return await this.memberService.get(primaryKey);
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
  @Put('/updated/:pkey')
  async updatedMember(
    @Param('pkey') primaryKey: any,
    @Body() data: MemberJoinRequestDto,
  ): Promise<HttpStatus> {
    console.log(TAG, `Update Members Info primaryKey : ${primaryKey}`);
    return (await this.memberService.update(data))
      ? HttpStatus.OK
      : HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @ApiOperation({ summary: 'Delete One Member Info By primary key' })
  @Delete('/delete/:pkey')
  async deleteMember(
    @Param('pkey') primaryKey: any,
  ): Promise<boolean> {
    console.log(TAG, `Delete Member Info primaryKey : ${primaryKey}`);
    return await this.memberService.delete(primaryKey);
  }
}
