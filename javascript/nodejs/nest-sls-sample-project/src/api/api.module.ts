import { Module } from '@nestjs/common';
import { OcrController } from './ocr/ocr.controller';
import { QrcodeController } from './qrcode/qrcode.controller';
import { QrcodeService } from './qrcode/qrcode.service';
import { TesseractService } from './ocr/tesseract/tesseract.service';
import { TestController } from './test/test.controller';
import { FileController } from './file/file.controller';
import { CsvService } from './file/csv/csv.service';
import { TestService } from './test/test.service';
import { DynamodbService } from '../db/nosql/dynamodb/dynamodb.service';

@Module({
  imports: [],
  controllers: [
    QrcodeController,
    OcrController,
    FileController,
    TestController,
  ],
  providers: [
    QrcodeService,
    CsvService,
    TesseractService,
    TestService,
    DynamodbService,
  ],
})
export class ApiModule {}
