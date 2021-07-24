import {ApiProperty} from "@nestjs/swagger";
import {IsEmail, IsNotEmpty, IsString} from "class-validator";

export class MemberJoinDto {

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'Member Id',
  })
  public memberId: string;

  @IsEmail()
  @ApiProperty({
    example: 'sample@email.com',
    description: 'Email',
  })
  public email: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'NickName',
  })
  public nickname: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'password',
  })
  public memberPassword: string;
}
