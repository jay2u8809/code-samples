import { Module } from '@nestjs/common';
import { OcrController } from './ocr/ocr.controller';
import { QrcodeController } from './qrcode/qrcode.controller';
import { QrcodeService } from './qrcode/qrcode.service';
import { TesseractService } from './ocr/tesseract/tesseract.service';
import { TestController } from './test/test.controller';
import { TestService } from './test/test.service';

@Module({
  imports: [],
  controllers: [QrcodeController, OcrController],
  providers: [QrcodeService, TesseractService],
    TestController,
    TestService
})
export class ApiModule {}
