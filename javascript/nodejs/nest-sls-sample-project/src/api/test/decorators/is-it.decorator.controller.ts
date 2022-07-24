import { Body, Controller, Post } from '@nestjs/common';
import { IsEmailRequestDto } from './dto/is-it/is-email.request.dto';
import { IsOptionalRequestDto } from './dto/is-it/is-optional.request.dto';
import { IsDefinedRequestDto } from './dto/is-it/is-defined.request.dto';
import { ApiTags } from '@nestjs/swagger';

const TAG = 'IS-IT-DECORATORS_TEST_CONTROLLER';

@ApiTags('open/decorators')
@Controller('/v1/is-it-decorators')
export class IsItDecoratorController {
  constructor() {}

  @Post('/validate/email')
  public validateEmailDecorators(@Body() params: IsEmailRequestDto): string {
    console.log(TAG, 'email', params.email);
    console.assert(!!params.email, "params's length : %s", params.email.length);
    return params.email;
  }

  @Post('/validate/optional')
  public validateOptionalDecorators(@Body() params: IsOptionalRequestDto): any {
    console.log(TAG, 'optional', params.optional);
    console.log(TAG, 'optionalObj', params.optionalObj);
    return params;
  }

  @Post('/validate/defined')
  public validateDefinedDecorators(@Body() params: IsDefinedRequestDto): any {
    console.log(TAG, 'defined', params.defined);
    console.log(TAG, 'definedObj', params.definedObj);
    return params;
  }
}
