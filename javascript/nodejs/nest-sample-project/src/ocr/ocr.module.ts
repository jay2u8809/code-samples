import { Module } from '@nestjs/common';
import { TesseractService } from './tesseract/tesseract.service';
import {OcrController} from "./ocr.controller";

@Module({
  controllers: [OcrController],
  providers: [TesseractService],
  imports: []
})
export class OcrModule {}
