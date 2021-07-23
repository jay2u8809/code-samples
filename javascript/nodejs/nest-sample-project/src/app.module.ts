import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { OcrModule } from './ocr/ocr.module';
import { QrcodeModule } from './qrcode/qrcode.module';

@Module({
  imports: [OcrModule, QrcodeModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
