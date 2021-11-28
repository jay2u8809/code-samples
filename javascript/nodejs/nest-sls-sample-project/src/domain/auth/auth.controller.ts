import { BadRequestException, Body, Controller, HttpStatus, Post } from '@nestjs/common';
import { AuthService } from './auth.service';
import { ApiOperation } from '@nestjs/swagger';
import { ParamDto } from '../common/dto/param.dto';

const TAG = 'AUTH_CONTROLLER';

@Controller('/api/v1/auth')
export class AuthController {
  constructor(private readonly auth: AuthService) {}

  @ApiOperation({ summary: 'Auth Member' })
  @Post('/member/')
  async authMember(@Body() data: ParamDto): Promise<any> {
    if (!(data.id && data.pw))
      throw new BadRequestException(TAG, `Id or Pw Is Empty`);

    const result: boolean = await this.auth.authMember(data.id, data.pw);
    console.log(TAG, `Success Auth Member: ${result}`);
    return result ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
  }
}
