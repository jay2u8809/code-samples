import { DecoratorsRequestDto } from '../decorators.request.dto';
import { Contains, NotContains } from 'class-validator';

export class ContainRequestDto extends DecoratorsRequestDto {
  @Contains('test')
  containStr: string;

  @NotContains('test')
  notContainStr: string;
}
