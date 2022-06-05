import { ApiProperty } from "@nestjs/swagger";
import { IsNotEmpty, IsNumber, IsOptional, IsString, IsUrl } from "class-validator";

export class QrcodeRequestDto {
  @ApiProperty({
    description: 'url'
  })
  @IsNotEmpty()
  @IsUrl()
  @IsString()
  url: string;

  @ApiProperty({
    description: 'size'
  })
  @IsNotEmpty()
  @IsOptional()
  @IsNumber()
  size: number;
}