import { Body, Controller, Param, Post, Query } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { QrcodeRequestDto } from './dto/qrcode-request.dto';
import { QrcodeService } from './qrcode.service';

const TAG = 'QrcodeController';

@ApiTags('open/qrcode')
@Controller('/v1/qrcode')
export class QrcodeController {
  constructor(private readonly qrcodeService: QrcodeService) {}

  @Post('/generate')
  public generateQrCode(@Body() params: QrcodeRequestDto): string {
    console.log(TAG, 'req-params', JSON.stringify(params));
    return this.qrcodeService.generateQrCodeByImageUri(params.url, params.size);
  }
}
