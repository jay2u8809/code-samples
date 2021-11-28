import { IsNotEmpty, IsOptional, IsString } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class ParamDto {
  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    example: '',
    description: 'id',
  })
  id: string;

  @IsString()
  @IsOptional()
  @ApiProperty({
    example: '',
    description: 'member pw',
  })
  pw?: string;
}
