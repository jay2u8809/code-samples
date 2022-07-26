import { IsEmail } from 'class-validator';
import { DecoratorsRequestDto } from '../decorators.request.dto';

export class IsEmailRequestDto extends DecoratorsRequestDto {
  @IsEmail()
  email: string;
}
