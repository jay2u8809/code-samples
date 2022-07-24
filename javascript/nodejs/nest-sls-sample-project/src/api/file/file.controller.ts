import {
  Body,
  Controller,
  HttpStatus,
  Post,
  UploadedFile,
  UseInterceptors,
} from '@nestjs/common';
import { CsvService } from './csv/csv.service';
import { DataType } from '../../common/code/data-type';
import { FileInterceptor } from '@nestjs/platform-express';

const TAG = 'FileController';

@Controller('/v1/file')
export class FileController {
  constructor(private readonly csvService: CsvService) {}

  @Post('/convert/json-to-csv/')
  public async convertJsonToCsv(@Body() data: any): Promise<any> {
    console.assert(!!data, `${TAG}, json-is-empty`);
    return this.csvService.convertToCsv(data, DataType.JSON);
  }

  @Post('/upload/csv')
  @UseInterceptors(FileInterceptor('file'))
  public async uploadCsv(
    @UploadedFile() file: Express.Multer.File,
  ): Promise<HttpStatus> {
    console.log(TAG, 'upload-csv-file-name', file?.originalname);
    const result = await this.csvService.saveFile(file);
    return result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
