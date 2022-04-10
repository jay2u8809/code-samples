import { ApiProperty } from '@nestjs/swagger';
import {
  IsEmail,
  IsNotEmpty,
  IsOptional,
  IsPhoneNumber,
  IsString,
} from 'class-validator';
import {
  Member,
  setMemberDefaultFields,
} from '../../../entities/member/member';
import { setBaseDefaultFields } from '../../../entities/base.entity';
import { plainToInstance } from 'class-transformer';
import { v4 as uuidv4 } from 'uuid';
import configuration from '../../../config/configuration';
import { generateHash } from '../../../common/common.utils';

export class MemberJoinRequestDto {
  public memberSn: bigint;

  public id: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'Member Id',
  })
  public memberId: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'password',
  })
  public memberPw: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'member Name1',
  })
  public memberName1: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'member Name2',
  })
  public memberName2: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'member Name3',
  })
  public memberName3: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'member Name4',
  })
  public memberName4: string;

  @IsEmail()
  @IsNotEmpty()
  @ApiProperty({
    example: 'sample@email.com',
    description: 'Email',
  })
  public emailAddress: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'NickName',
  })
  public nickName: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'Zip Code',
  })
  public zipCode: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'address1',
  })
  public address_1: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'address2',
  })
  public address_2: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'address3',
  })
  public address_3: string;

  @IsOptional()
  @IsString()
  @ApiProperty({
    example: '',
    description: 'address4',
  })
  public address_4: string;

  // @IsPhoneNumber()
  @ApiProperty({
    example: '',
    description: 'phoneNo',
  })
  public phoneNo: string;
}

/**
 * Generate saving member data
 * @param joinRequestDto
 */
export const saveMember = (joinRequestDto: MemberJoinRequestDto): Member => {
  // TODO password encryption

  const member: Member = plainToInstance(Member, {
    ...joinRequestDto,
    id: configuration().db.useSql ? null : uuidv4(),
    name_1: joinRequestDto.memberName1,
    name_2: joinRequestDto.memberName2,
    name_3: joinRequestDto.memberName3,
    name_4: joinRequestDto.memberName4,
    phoneNo_1: joinRequestDto.phoneNo,
    pwVerifyKey: generateHash(joinRequestDto.memberPw),
  });

  setMemberDefaultFields(member);
  setBaseDefaultFields(member, member.memberId);

  return member;
};
