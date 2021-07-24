import {ApiProperty} from "@nestjs/swagger";
import {IsEmail, IsNotEmpty, IsPhoneNumber, IsString} from "class-validator";

export class MemberJoinDto {

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
  public nickname: string;

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
