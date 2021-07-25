import {ApiProperty} from "@nestjs/swagger";
import {IsEmail, IsNotEmpty, IsPhoneNumber, IsString} from "class-validator";
import {Member, setMemberDefaultFields} from "../../../entities/member/member";
import {setBaseDefaultFields} from "../../../entities/base.entity";

export class MemberJoinRequestDto {

  public memberSn: bigint;

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

  @IsString()
  @ApiProperty({
    example: '',
    description: 'member Name3',
  })
  public memberName3: string;

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
  public memberEmail: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'NickName',
  })
  public nickName: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'Zip Code',
  })
  public zipCode: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'address1',
  })
  public address1: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'address2',
  })
  public address2: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'address3',
  })
  public address3: string;

  @IsString()
  @ApiProperty({
    example: '',
    description: 'address4',
  })
  public address4: string;

  @IsPhoneNumber()
  @ApiProperty({
    example: '',
    description: 'phoneNo',
  })
  public phoneNo: string;
}

/**
 * 저장할 회원의 데이터 생성
 * 保存させる会員のデータ生成
 * @param memberJoinDto
 */
export function saveMember(joinRequestDto: MemberJoinRequestDto): Member {

  const member: Member = new Member();

  // Clone
  Object.assign(member, joinRequestDto);

  // const hashedPassword = await bcrypt.hash(password, 12);

  member.name_1 = joinRequestDto.memberName1;
  member.name_2 = joinRequestDto.memberName2;
  member.name_3 = joinRequestDto.memberName3;
  member.name_4 = joinRequestDto.memberName4;
  member.emailAddress = joinRequestDto.memberEmail;

  member.nickname = joinRequestDto.nickName;
  member.zipCode = joinRequestDto.zipCode;
  member.address_1 = joinRequestDto.address1;
  member.address_2 = joinRequestDto.address2;
  member.address_3 = joinRequestDto.address3;
  member.address_4 = joinRequestDto.address4;
  member.phoneNo_1 = joinRequestDto.phoneNo;

  // MemberEntity Default Value Setting
  setMemberDefaultFields(member);
  // BaseEntity Default Value Setting
  setBaseDefaultFields(member);

  return member;
}
