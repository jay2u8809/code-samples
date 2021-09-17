import { Module } from '@nestjs/common';
import { OcrController } from './ocr/ocr.controller';
import { QrcodeController } from './qrcode/qrcode.controller';
import { QrcodeService } from './qrcode/qrcode.service';
import { TesseractService } from './ocr/tesseract/tesseract.service';

@Module({
  imports: [],
  controllers: [QrcodeController, OcrController],
  providers: [QrcodeService, TesseractService],
})
export class ApiModule {}
