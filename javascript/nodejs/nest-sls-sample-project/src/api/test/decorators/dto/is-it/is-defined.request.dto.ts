import { DecoratorsRequestDto } from '../decorators.request.dto';
import { IsDefined } from 'class-validator';

export class IsDefinedRequestDto extends DecoratorsRequestDto {
  @IsDefined()
  defined: string;

  @IsDefined()
  definedObj: any;
}
