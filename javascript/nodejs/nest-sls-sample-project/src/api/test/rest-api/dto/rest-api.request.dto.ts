import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty, IsOptional, IsString } from 'class-validator';

export class RestApiRequestDto {
  @ApiProperty({
    description: 'name1 field',
  })
  @IsNotEmpty()
  @IsString()
  public name1: string;

  @ApiProperty({
    description: 'name2 field',
    required: false,
  })
  @IsNotEmpty()
  @IsString()
  public name2: string;

  @ApiProperty({
    description: 'name3 field',
  })
  @IsNotEmpty()
  @IsOptional()
  @IsString()
  public name3: string;
}
