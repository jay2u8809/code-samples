import {Controller, Post, UploadedFile, UseInterceptors} from '@nestjs/common';
import {TesseractService} from "./tesseract/tesseract.service";
import {FileInterceptor} from "@nestjs/platform-express";
import {isEmpty} from "../../common/common.utils";

@Controller('ocr')
export class OcrController {
  constructor(private readonly tesseractService: TesseractService) {}

  @Post('tess/detect')
  @UseInterceptors(FileInterceptor('file'))
  async getOcrString(@UploadedFile() file: Express.Multer.File): Promise<string> {

    if (isEmpty(file) || isEmpty(file.buffer)) {
      return null;
    }

    return await this.tesseractService.detectTextFromImageProcess(file.buffer);
  }
}
