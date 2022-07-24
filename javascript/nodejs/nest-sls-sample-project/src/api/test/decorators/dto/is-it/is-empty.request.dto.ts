import { DecoratorsRequestDto } from '../decorators.request.dto';
import { IsEmpty, IsNotEmpty } from 'class-validator';

export class IsEmptyRequestDto extends DecoratorsRequestDto {
  @IsEmpty()
  empty: string;

  @IsEmpty()
  emptyArr: any[];

  @IsNotEmpty()
  notEmpty: string;

  @IsNotEmpty()
  notEmptyArr: any[];
}
