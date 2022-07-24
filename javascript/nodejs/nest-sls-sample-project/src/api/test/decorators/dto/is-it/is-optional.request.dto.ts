import { IsOptional } from 'class-validator';
import { DecoratorsRequestDto } from '../decorators.request.dto';

export class IsOptionalRequestDto extends DecoratorsRequestDto {
  @IsOptional()
  optional: string;

  @IsOptional()
  optionalObj: any;
}
