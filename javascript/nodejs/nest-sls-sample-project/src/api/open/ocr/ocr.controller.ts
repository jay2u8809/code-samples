import {
  Controller,
  Param,
  Post,
  UploadedFile,
  UseInterceptors,
} from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { FileInterceptor } from '@nestjs/platform-express';
import { TesseractService } from './tesseract/tesseract.service';
import { LangType } from '../../../common/code/lang-type';

@ApiTags('open/ocr')
@Controller('/v1/ocr')
export class OcrController {
  constructor(private readonly tesseractService: TesseractService) {}

  @Post('/tess/detect/:language')
  @UseInterceptors(FileInterceptor('file'))
  public async getOcrString(
    @UploadedFile() file: Express.Multer.File,
    @Param('language') lang: LangType,
  ): Promise<string> {
    if (!file) {
      return;
    }
    return this.tesseractService.detectTextFromFile(file.buffer, lang);
  }
}
