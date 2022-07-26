import { Body, Controller, Post } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ContainRequestDto } from './dto/string-decorator/contain.request.dto';

const TAG = 'STRING-DECORATORS_TEST_CONTROLLER';

@ApiTags('open/decorators')
@Controller('/v1/string-decorators')
export class StringDecoratorsController {
  constructor() {}

  @Post('/validate/contain')
  public validateContainDecorators(@Body() params: ContainRequestDto): any {
    console.log(TAG, 'contain', params.containStr);
    console.log(TAG, 'not-contain', params.notContainStr);
    return params;
  }
}
