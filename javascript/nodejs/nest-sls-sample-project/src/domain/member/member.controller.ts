import {
  BadRequestException,
  Body,
  Controller,
  Delete,
  Get,
  HttpStatus, InternalServerErrorException,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { MemberJoinRequestDto } from './dto/member.join.request.dto';
import { Member } from '../../entities/member/member';
import { ApiOperation } from '@nestjs/swagger';
import { MemberNosqlService } from './member.nosql.service';
import { MemberSqlService } from './member.sql.service';

const TAG = 'MEMBER_CONTROLLER';

@Controller('/api/v1/individual/member')
export class MemberController {
  constructor(private readonly memberService: MemberNosqlService) {}

  @ApiOperation({ summary: 'Register One Member Info' })
  @Post('/register/')
  async registerMember(@Body() data: MemberJoinRequestDto): Promise<any> {
    if (!data) {
      throw new BadRequestException(TAG, `Register New Member Info Is Empty`);
    }
    const result: string = await this.memberService.create(data);
    console.log(TAG, `Success Create Member: ${result}`);
    return HttpStatus.OK;
  }

  @ApiOperation({ summary: 'Get One Member Info By primary key' })
  @Get('/get/:pkey')
  async getMember(@Param('pkey') primaryKey: any): Promise<Member> {
    if (!primaryKey) {
      throw new BadRequestException(TAG, `Get Member Info primaryKey: ${primaryKey}`);
    }
    return this.memberService.get(primaryKey);
  }

  @ApiOperation({ summary: 'Get All Members Info' })
  @Get('/all/')
  async getAllMembers(): Promise<Member[]> {
    console.log(TAG, `Get All Members`);
    return this.memberService.getAll();
  }

  @ApiOperation({ summary: 'Get One Member Info By memberId' })
  @Get('/id/:memberId')
  async getMemberById(@Param('memberId') memberId: string): Promise<Member> {
    if (!memberId) {
      throw new BadRequestException(TAG, `Get Member Info memberId: ${memberId}`);
    }
    return this.memberService.getById(memberId);
  }

  @ApiOperation({ summary: 'Get One Member Info By member email' })
  @Get('/email/:email')
  async getMemberByEmail(@Param('email') email: string): Promise<Member[]> {
    if (!email) {
      throw new BadRequestException(TAG, `Get Member Info memberId By email: ${email}`);
    }
    return this.memberService.getByEmail(email);
  }

  @ApiOperation({ summary: 'Get One Member Info By member nickname' })
  @Get('/nickName/:nickName')
  async getMemberByNickName(@Param('nickName') nickName: string): Promise<Member[]> {
    if (!nickName) {
      throw new BadRequestException(TAG, `Get Member Info memberId : ${nickName}`);
    }
    return this.memberService.getByNickName(nickName);
  }

  @ApiOperation({ summary: 'Get Normal Status Members Info' })
  @Get('/all/normal/')
  async getNormalMembers(): Promise<Member[]> {
    console.log(TAG, `Get Normal Status Members Info`);
    return this.memberService.getNormalMembers();
  }

  @ApiOperation({ summary: 'Update Members Info' })
  @Put('/updated/:pkey')
  async updatedMember(
    @Param('pkey') primaryKey: any,
    @Body() data: MemberJoinRequestDto,
  ): Promise<HttpStatus> {
    if (!primaryKey) {
      throw new BadRequestException(TAG, `Update Members Info primaryKey : ${primaryKey}`);
    }
    return (await this.memberService.update(data))
      ? HttpStatus.OK
      : HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @ApiOperation({ summary: 'Delete One Member Info By primary key' })
  @Delete('/delete/:pkey')
  async deleteMember(@Param('pkey') primaryKey: any): Promise<boolean> {
    if (!primaryKey) {
      throw new BadRequestException(TAG, `Delete Member Info primaryKey : ${primaryKey}`);
    }
    return this.memberService.delete(primaryKey);
  }
}
