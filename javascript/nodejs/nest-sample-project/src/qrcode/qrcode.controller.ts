import {Controller, Get, Query} from '@nestjs/common';
import {QrcodeService} from "./qrcode.service";

@Controller('qrcode')
export class QrcodeController {
  constructor(private readonly qrcodeService: QrcodeService) {}

  @Get('generate')
  public generateQrCode(@Query('path') urlPath: string, @Query('size') size: number): string {

    return this.qrcodeService.generateQrCodeByImageUri(urlPath, size);
  }
}
